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
package com.sap.psr.vulas.kb.exception;

/**
 * Command Line parser exception
 */
public class CommandLineParserException extends Exception {
  private static final long serialVersionUID = -4923677968243017658L;

  public CommandLineParserException(String message) {
    super(message);
  }

  public CommandLineParserException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public CommandLineParserException(Throwable throwable) {
    super(throwable);
  }
}
