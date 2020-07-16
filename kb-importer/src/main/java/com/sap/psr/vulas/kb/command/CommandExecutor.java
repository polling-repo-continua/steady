/**
 * This file is part of Eclipse Steady.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.psr.vulas.kb.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sap.psr.vulas.kb.exception.CommandLineParserException;
import com.sap.psr.vulas.kb.exception.ValidationException;

/**
 * command executor
 */
public class CommandExecutor {

  private static final Logger log = LoggerFactory.getLogger(CommandExecutor.class);
  private Map<String, Command> commands = new HashMap<>();
  private static CommandExecutor commandExecutor;

  private CommandExecutor() {
    init();
  }

  public static synchronized CommandExecutor getInstance() {
    if (commandExecutor == null) {
      commandExecutor = new CommandExecutor();
    }
    return commandExecutor;
  }

  private void init() {
    ServiceLoader<Command> serviceProviders = ServiceLoader.load(Command.class);
    Iterator<Command> iterator = serviceProviders.iterator();
    while (iterator.hasNext()) {
      Command command = iterator.next();
      commands.put(command.getCommandName(), command);
    }
  }

  /**
   * command executor
   * 
   * @param _args a array of {java.lang.String}
   */
  public void execute(String _args[]) {
    if (_args.length == 0) {
      log.error("No arguments passed");
      return;
    }

    Command command = commands.get(_args[0]);

    // if unknown command name is passed then set default command as import
    if (command == null) {
      command = new Import();
    }

    Options commandOptions = command.getOptions();

    HashMap<String, Object> mapCommandOptionValues;
    try {
      mapCommandOptionValues = CommandParser.parse(_args, commandOptions);
    } catch (CommandLineParserException e) {
      log.error(e.getMessage());
      printHelp(commandOptions);
      return;
    }

    try {
      command.validate(mapCommandOptionValues);
    } catch (ValidationException e) {
      log.error(e.getMessage());
    }

    command.run(mapCommandOptionValues);
  }

  /**
   * Print command help
   * 
   * @param a {@link org.apache.commons.cli.Options}
   */
  private void printHelp(Options commandOptions) {
    //Showing import help
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("java -jar <jar> <options>", new Import().getOptions());
  }
}
