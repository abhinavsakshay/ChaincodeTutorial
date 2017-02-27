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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hyperledger.java.shim.ChaincodeStub;
import org.junit.Before;
import org.junit.Test;

public class ChaincodeLogTest {

  /**
   * Fixture - stubbed out ChaincodeStub. See what I did there? Huh?
   */
  private ChaincodeStub mockChaincodeStub;

  /**
   * Fixture - the class under test: ChaincodeLog
   */
  private ChaincodeLog classUnderTest;

  /**
   * Some constants for testing
   */
  private static final String TEST_KEY_1 = "TEST_KEY_1";
  private static final String TEST_KEY_2 = "TEST_KEY_2";
  private static final String TEST_KEY_3 = "TEST_KEY_3";
  private static final String TEST_KEY_4 = "TEST_KEY_4";
  private static final String TEST_KEY_5 = "TEST_KEY_5";

  private static final String TEST_LOG_MESSAGE_1 = "TEST_LOG_MESSAGE_1";
  private static final String TEST_LOG_MESSAGE_2 = "TEST_LOG_MESSAGE_2";
  private static final String TEST_LOG_MESSAGE_3 = "TEST_LOG_MESSAGE_3";
  private static final String TEST_LOG_MESSAGE_4 = "TEST_LOG_MESSAGE_4";
  private static final String TEST_LOG_MESSAGE_5 = "TEST_LOG_MESSAGE_5";

  @Before
  public void setUp() throws Exception {
    classUnderTest = new ChaincodeLog();
    mockChaincodeStub = mock(ChaincodeStub.class);

  }

  /**
   * Set up the Mocks. Its function is pretty much all there in the name.
   */
  private void setUpMocks() {
    //
    // Setup the Mocks
    when(mockChaincodeStub.getState(ChaincodeLog.KEY_PREFIX + TEST_KEY_1)).thenReturn(TEST_LOG_MESSAGE_1);
    when(mockChaincodeStub.getState(ChaincodeLog.KEY_PREFIX + TEST_KEY_2)).thenReturn(TEST_LOG_MESSAGE_2);
    when(mockChaincodeStub.getState(ChaincodeLog.KEY_PREFIX + TEST_KEY_3)).thenReturn(TEST_LOG_MESSAGE_3);
    when(mockChaincodeStub.getState(ChaincodeLog.KEY_PREFIX + TEST_KEY_4)).thenReturn(TEST_LOG_MESSAGE_4);
    when(mockChaincodeStub.getState(ChaincodeLog.KEY_PREFIX + TEST_KEY_5)).thenReturn(TEST_LOG_MESSAGE_5);
  }

  @Test
  public void testGetChaincodeID() {
    String expectedChaincodeID = ChaincodeLog.CONTRACT_ID;

    assertEquals(expectedChaincodeID, classUnderTest.getChaincodeID());
  }

  @Test
  public void testRunChaincodeStubStringStringArray() {
    setUpMocks();
    String logMessage = "This is a test";
    String logKey = TEST_KEY_1;
    //
    // do init
    String response = doInit(logKey, logMessage);
    assertEquals(logMessage, response);
    //
    // do log
    logKey = TEST_KEY_2;
    logMessage = TEST_LOG_MESSAGE_2;
    response = doLog(logKey, logMessage);
    assertEquals(logMessage, response);
    logKey = TEST_KEY_3;
    logMessage = TEST_LOG_MESSAGE_3;
    response = doLog(logKey, logMessage);
    assertEquals(logMessage, response);
    logKey = TEST_KEY_4;
    logMessage = TEST_LOG_MESSAGE_4;
    response = doLog(logKey, logMessage);
    assertEquals(logMessage, response);
    logKey = TEST_KEY_5;
    logMessage = TEST_LOG_MESSAGE_5;
    response = doLog(logKey, logMessage);
    assertEquals(logMessage, response);
  }

  @Test
  public void testQueryChaincodeStubStringStringArray() {
    setUpMocks();
    String[] args = {
        TEST_KEY_1
    };
    String function = "query";
    //
    // Invoke query function
    String response = classUnderTest.query(mockChaincodeStub, function, args);
    assertEquals(TEST_LOG_MESSAGE_1, response);
    //
    args = new String[] {
        TEST_KEY_2
    };
    response = classUnderTest.query(mockChaincodeStub, function, args);
    assertEquals(TEST_LOG_MESSAGE_2, response);
    //
    args = new String[] {
        TEST_KEY_3
    };
    response = classUnderTest.query(mockChaincodeStub, function, args);
    assertEquals(TEST_LOG_MESSAGE_3, response);
    //
    args = new String[] {
        TEST_KEY_4
    };
    response = classUnderTest.query(mockChaincodeStub, function, args);
    assertEquals(TEST_LOG_MESSAGE_4, response);
    //
    args = new String[] {
        TEST_KEY_5
    };
    response = classUnderTest.query(mockChaincodeStub, function, args);
    assertEquals(TEST_LOG_MESSAGE_5, response);
  }

  private String doInit(String logKey, String logMessage) {
    String ret;
    String[] args = {
        logKey, logMessage
    };
    //
    // Invoke init function
    ret = classUnderTest.run(mockChaincodeStub, "init", args);
    return ret;
  }

  private String doLog(String logKey, String logMessage) {
    String ret;
    String[] args = {
        logKey, logMessage
    };
    //
    // Invoke init function
    ret = classUnderTest.run(mockChaincodeStub, "log", args);
    return ret;
  }
}
