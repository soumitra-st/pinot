/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.common.restlet.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.pinot.spi.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TableSegmentValidationInfoTest {

  @Test
  public void testParsingFromJsonStringAllFields()
      throws JsonProcessingException {
    String tableSegmentValidationInfoString = "{\"valid\":false,\"invalidReason\":\"invalid\",\"maxEndTimeMs\":0}";
    TableSegmentValidationInfo tableSegmentValidationInfo =
        JsonUtils.stringToObject(tableSegmentValidationInfoString, TableSegmentValidationInfo.class);
    Assert.assertFalse(tableSegmentValidationInfo.isValid());
    Assert.assertEquals(tableSegmentValidationInfo.getInvalidReason(), "invalid");
    Assert.assertEquals(tableSegmentValidationInfo.getMaxEndTimeMs(), 0);
  }

  @Test
  public void testParsingFromJsonStringNoInvalidReason()
      throws JsonProcessingException {
    String tableSegmentValidationInfoString = "{\"valid\":true,\"maxEndTimeMs\":0}";
    TableSegmentValidationInfo tableSegmentValidationInfo =
        JsonUtils.stringToObject(tableSegmentValidationInfoString, TableSegmentValidationInfo.class);
    Assert.assertTrue(tableSegmentValidationInfo.isValid());
    Assert.assertNull(tableSegmentValidationInfo.getInvalidReason());
    Assert.assertEquals(tableSegmentValidationInfo.getMaxEndTimeMs(), 0);
  }
}
