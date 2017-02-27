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
 * 
 * @author sperry
 *
 */
public class ChaincodeLog extends ChaincodeBase {

  private static final Log log = LogFactory.getLog(ChaincodeLog.class);

  public static final String CONTRACT_ID = "ChaincodeLogSmartContract";

  public static final String KEY_PREFIX = CONTRACT_ID + "-CMSC-";

  public static void main(String[] args) {
    new ChaincodeLog().start(args);
  }

  @Override
  public String getChaincodeID() {
    return CONTRACT_ID;
  }

  @Override
  public String run(ChaincodeStub stub, String function, String[] args) {
    String ret = CONTRACT_ID + ": OK";
    log.info("Greetings from " + CONTRACT_ID + " | function: " + function + " | args: " + Arrays.toString(args));
    switch (function) {
    case "init":
      ret = init(stub, function, args);
      break;
    case "log":
      ret = log(stub, function, args);
      break;
    case "query":
      ret = query(stub, function, args);
    }
    return ret;
  }

  @Override
  public String query(ChaincodeStub stub, String function, String[] args) {
    StringBuilder sb = new StringBuilder();
    int aa = 0;
    for (String key : args) {
      String logKey = KEY_PREFIX + key;
      if (aa++ > 0) {
        sb.append(",");
      }
      String value = stub.getState(logKey);
      log.info("*** Query: For key '" + logKey + ", value is '" + value + "' ***");
      sb.append(value);
    }
    return sb.toString();
  }

  /**
   * Initializes the chaincode by logging a message to the ledger.
   * 
   * @param stub
   *          The Chaincode stub that talks to the Fabric.
   * @param function
   *          The name of the function to invoke.
   * @param args
   *          The arguments to pass to the stub
   * @return String - a log message
   */
  private String init(ChaincodeStub stub, String function, String[] args) {
    String ret;
    //
    // Log the init to the ledger
    ret = log(stub, function, args);
    return ret;
  }

  /**
   * 
   * @param stub
   * @param function
   * @param args
   * @return
   */
  private String log(ChaincodeStub stub, String function, String[] args) {
    String ret = null;
    //
    // Store the log message
    String logKey = args[0];
    String logMessage = args[1];
    log.info("*** Storing log message (K,V) -> (" + logKey + "," + logMessage + ") ***");
    stub.putState(KEY_PREFIX, logMessage);
    //
    ret = logMessage;
    return ret;
  }

}
