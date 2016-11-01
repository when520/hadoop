/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.lib.service.security;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.security.GroupMappingServiceProvider;
import org.apache.hadoop.test.HadoopUsersConfTestHelper;

public class DummyGroupMapping implements GroupMappingServiceProvider {

  @Override
  @SuppressWarnings("unchecked")
  public Set<String> getGroups(String user) throws IOException {
    Set<String> set = new HashSet<>();
    if (user.equals("root")) {
      set.add("admin");
    } else if (user.equals("nobody")) {
      set.add("nobody");
    } else {
      String[] groups = HadoopUsersConfTestHelper.getHadoopUserGroups(user);
      if (groups != null) {
        set.addAll(Arrays.asList(groups));
      }
    }
    return set;
  }

  @Override
  public void cacheGroupsRefresh() throws IOException {
  }

  @Override
  public void cacheGroupsAdd(List<String> groups) throws IOException {
  }
}
