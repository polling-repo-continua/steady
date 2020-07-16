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
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import com.sap.psr.vulas.kb.exception.ValidationException;

/**
 * help command
 */
public class Help implements Command {

  @Override
  public void run(HashMap<String, Object> args) {
    // Showing help of import command
    Command command = new Import();
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("java -jar <jar> <options>", command.getOptions());
  }

  @Override
  public Options getOptions() {
    return new Options();
  }

  @Override
  public void validate(HashMap<String, Object> args) throws ValidationException {
    // Nothing to validate as help does not have any args
  }

  @Override
  public String getCommandName() {
    return "help";
  }
}
