package com.sap.psr.vulas.backend.repo;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.sap.psr.vulas.backend.model.Application;
import com.sap.psr.vulas.backend.model.GoalExecution;
import com.sap.psr.vulas.backend.model.Property;
import com.sap.psr.vulas.backend.util.ReferenceUpdater;
import com.sap.psr.vulas.shared.enums.GoalType;
import com.sap.psr.vulas.shared.enums.PropertySource;
import com.sap.psr.vulas.shared.enums.Scope;

public class GoalExecutionRepositoryImpl implements GoalExecutionRepositoryCustom {

	private static Logger log = LoggerFactory.getLogger(GoalExecutionRepositoryImpl.class);
	
	@Autowired
	GoalExecutionRepository gexeRepository;

	@Autowired
	ReferenceUpdater refUpdater;

	//@CacheEvict(value="gexe", key="#_app")
	@Override
	public GoalExecution customSave(Application _app, GoalExecution _provided_gexe) {
		GoalExecution managed_gexe = null;

		// Update refs to independent entities
		_provided_gexe.setApp(_app);
		_provided_gexe.setConfiguration(refUpdater.saveNestedProperties(_provided_gexe.getConfiguration()));
		_provided_gexe.setSystemInfo(refUpdater.saveNestedProperties(_provided_gexe.getSystemInfo()));

		// Save
		try {
			managed_gexe = this.gexeRepository.save(_provided_gexe);
		} catch (Exception e) {
			throw new PersistenceException("Error while saving goal execution [" + _provided_gexe + "]: " + e.getMessage());
		}

		return managed_gexe;
	}

	//@Cacheable(value="gexe", key="#_app", sync=true) //, unless="#result.isEmpty()") // Note that in Spring proxy mode (default), only external calls will be cached. Calls within the class (this....) will not.
	@Override
	public GoalExecution findLatestGoalExecution(Application _app, GoalType _type) {
		Long id = null;
		if(_type!=null)
			id = this.gexeRepository.findLatestForApp(_app.getId(), _type.toString());
		else
			id = this.gexeRepository.findLatestForApp(_app.getId());
		if(id!=null)
			return this.gexeRepository.findOne(id);
		else
			return null;
	}
}