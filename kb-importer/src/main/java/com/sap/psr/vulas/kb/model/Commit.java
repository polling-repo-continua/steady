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
package com.sap.psr.vulas.kb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Commit information
 */
public class Commit {
  private String timestamp;
  @SerializedName("commit_id")
  private String commitId;
  private String branch;;
  @SerializedName("repository")
  private String repoUrl;
  private String directory;

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    if (timestamp == null)
      return;
    this.timestamp = timestamp.trim();
  }

  public String getCommitId() {
    return commitId;
  }

  public void setCommitId(String commitId) {
    if (commitId == null)
      return;
    this.commitId = commitId.trim();
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    if (branch == null)
      return;
    this.branch = branch.trim();
  }

  public String getRepoUrl() {
    return repoUrl;
  }

  public void setRepoUrl(String repoUrl) {
    if (repoUrl == null)
      return;
    this.repoUrl = repoUrl.trim();
  }

  public String getDirectory() {
    return directory;
  }

  public void setDirectory(String directory) {
    if (directory == null)
      return;
    this.directory = directory.trim();
  }

  @Override
  public String toString() {
    return "Commit [timestamp=" + timestamp + ", commitId=" + commitId + ", branch=" + branch
        + ", repoUrl=" + repoUrl + ", directory=" + directory + "]";
  }
}
