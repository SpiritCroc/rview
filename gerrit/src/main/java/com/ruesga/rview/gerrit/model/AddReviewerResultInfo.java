/*
 * Copyright (C) 2016 Jorge Ruesga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ruesga.rview.gerrit.model;

import com.google.gson.annotations.SerializedName;

/**
 * @link "https://gerrit-review.googlesource.com/Documentation/rest-api-changes.html#add-reviewer-result"
 */
public class AddReviewerResultInfo extends AccountInfo {
    @SerializedName("input") public String input;
    @SerializedName("reviewers") public ReviewerInfo[] reviewers;
    @SerializedName("ccs") public ReviewerInfo[] ccs;
    @SerializedName("error") public String error;
    @SerializedName("confirm") public boolean confirm;
}

