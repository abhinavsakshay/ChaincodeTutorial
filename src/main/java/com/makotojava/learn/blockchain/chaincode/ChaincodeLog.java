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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperledger.java.shim.ChaincodeStub;

/**
 * 
 * @author sperry
 *
 */
public class ChaincodeLog extends AbstractChaincode {

  private static final Log log = LogFactory.getLog(ChaincodeLog.class);

  public static final String CONTRACT_ID = "ChaincodeLogSmartContract";

  public static final String LOG = "log";

  public static final String KEY_PREFIX = CONTRACT_ID + "-SC-";

  /**
   * The driver method. Every chaincode program must have one.
   * This is invoked to start the chaincode running, and register
   * it with the Fabric.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new ChaincodeLog().start(args);
  }

  @Override
  public String getChaincodeID() {
    return null;
  }

  @Override
  protected String handleInit(ChaincodeStub stub, String[] args) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String handleQuery(ChaincodeStub stub, String[] args) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String handleOther(ChaincodeStub stub, String function, String[] args) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String query(ChaincodeStub arg0, String arg1, String[] arg2) {
    // TODO Auto-generated method stub
    return null;
  }

}
