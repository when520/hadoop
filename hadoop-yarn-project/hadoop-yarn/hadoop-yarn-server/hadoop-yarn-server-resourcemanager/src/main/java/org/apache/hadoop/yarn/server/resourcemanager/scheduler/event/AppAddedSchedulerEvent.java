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

package org.apache.hadoop.yarn.server.resourcemanager.scheduler.event;

import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ReservationId;

public class AppAddedSchedulerEvent extends SchedulerEvent {

  private final ApplicationId applicationId;
  private final String queue;
  private final String user;
  private final String userPassword;
  private final ReservationId reservationID;
  private final boolean isAppRecovering;

  public AppAddedSchedulerEvent(
      ApplicationId applicationId, String queue, String user) {
    this(applicationId, queue, user, false, null);
  }

  public AppAddedSchedulerEvent(
          ApplicationId applicationId, String queue, String user, String userPassword) {
    this(applicationId, queue, user, userPassword, false, null);
  }

  @Deprecated
  public AppAddedSchedulerEvent(ApplicationId applicationId, String queue,
      String user, ReservationId reservationID) {
    this(applicationId, queue, user, false, reservationID);
  }

  @Deprecated
  public AppAddedSchedulerEvent(ApplicationId applicationId, String queue,
                                String user, boolean isAppRecovering, ReservationId reservationID) {
    this(applicationId, queue, user, null, isAppRecovering, reservationID);
  }

  public AppAddedSchedulerEvent(ApplicationId applicationId, String queue,
      String user, String userPassword, boolean isAppRecovering, ReservationId reservationID) {
    super(SchedulerEventType.APP_ADDED);
    this.applicationId = applicationId;
    this.queue = queue;
    this.user = user;
    this.userPassword = userPassword;
    this.reservationID = reservationID;
    this.isAppRecovering = isAppRecovering;
  }

  public ApplicationId getApplicationId() {
    return applicationId;
  }

  public String getQueue() {
    return queue;
  }

  public String getUser() {
    return user;
  }

  public String getUserPassword() {
    return this.userPassword;
  }

  public boolean getIsAppRecovering() {
    return isAppRecovering;
  }

  public ReservationId getReservationID() {
    return reservationID;
  }
}
