// Copyright (c) YugaByte, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.  You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software distributed under the License
// is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
// or implied.  See the License for the specific language governing permissions and limitations
// under the License.
//

package org.yb.pgsql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yb.util.YBTestRunnerNonTsanOnly;

import static org.yb.AssertionWrappers.*;

@RunWith(value=YBTestRunnerNonTsanOnly.class)
public class TestPgSortNumeric extends BasePgSortingOrder {
  private static final Logger LOG = LoggerFactory.getLogger(TestPgSortNumeric.class);

  // All typename MUST be in upper case for comparison purpose.
  static String testTypes[] = {
    "BIGINT",
    "INT",
    "SMALLINT"
  };

  static String[][] testValues = {
    // BIGINT
    { "9223372036854775807", "-9223372036854775808", "0", "1", "-1", "100", "-100" },

    // INT
    { "2147483647", "-2147483648", "0", "1", "-1", "100", "-100"},

    // SMALLINT
    { "32767", "-32768", "0", "1", "-1", "100", "-100" },
  };

  static String[][] testInvalidValues = {
    // BIGINT
    { "9223372036854775808", "-9223372036854775809", "NULL" },

    // INT
    { "2147483648", "-2147483649", "NULL" },

    // SMALLINT
    { "32768", "-32769", "NULL" }
  };

  // Testing sorting order for the listed numeric types.
  @Test
  public void testSortNumeric() throws Exception {
    RunTest(testTypes, testValues, testInvalidValues);
  }
}
