/*
 * Copyright 2017 Makoto Consulting Group, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.makotojava.learn.blockchain.chaincode;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;

/**
 * Abstract base class for all Chaincode in this project.
 * 
 * @author jsperry
 *
 */
public abstract class AbstractChaincode extends ChaincodeBase {

  private static final Log log = LogFactory.getLog(AbstractChaincode.class);

  public static final String INIT = "init";
  public static final String QUERY = "query";

  /**
   * Handles initializing the chaincode.
   * 
   * @param stub
   *          The ChaincodeStub used to communicate with the Fabric
   * 
   * @param args
   *          The arguments to pass to the function (init).
   * 
   * @return String - return value. May or may not be passed back to the caller.
   */
  protected abstract String handleInit(ChaincodeStub stub, String[] args);

  /**
   * Handles querying the chaincode.
   * 
   * @param args
   *          The arguments to pass to the function to invoke.
   * 
   * @return String - return value. May or may not be passed back to the caller.
   */
  protected abstract String handleQuery(ChaincodeStub stub, String[] args);

  /**
   * Handles querying the chaincode.
   * 
   * @param function
   *          The function to invoke.
   * 
   * @param args
   *          The arguments to pass to the function to invoke.
   * 
   * @return String - return value. May or may not be passed back to the caller.
   */
  protected abstract String handleOther(ChaincodeStub stub, String function, String[] args);

  @Override
  public String run(ChaincodeStub stub, String function, String[] args) {
    String ret;
    log.info("Greetings from run(): function -> " + function + " | args -> " + Arrays.toString(args));
    switch (function) {
    //
    // init - initializes the chaincode. Specifics handled by subclass.
    case INIT:
      ret = handleInit(stub, args);
      break;
    //
    // query - queries the ledger. Specifics handled by subclass.
    case QUERY:
      ret = handleQuery(stub, args);
      //
      // Not sure what this is. Delegate to child class for this.
      /// This is added for consistency and extensibility.
    default:
      ret = handleOther(stub, function, args);
      break;
    }
    //
    // Return the String to the caller
    return ret;
  }

}
