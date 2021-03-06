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
package com.ruesga.rview.adapters;

import android.content.Context;
import android.text.TextUtils;

import com.ruesga.rview.gerrit.GerritApi;
import com.ruesga.rview.gerrit.model.BranchInfo;
import com.ruesga.rview.misc.ModelHelper;
import com.ruesga.rview.preferences.Constants;

import java.util.ArrayList;
import java.util.List;

public class BranchAdapter extends FilterableAdapter {

    private String mProjectId;
    private final String mBranch;

    public BranchAdapter(Context context) {
        this(context, null, null);
    }

    public BranchAdapter(Context context, String projectId) {
        this(context, projectId, null);
    }

    public BranchAdapter(Context context, String projectId, String branch) {
        super(context);
        mProjectId = projectId;
        mBranch = branch;
    }

    public void setProjectId(String mProjectId) {
        this.mProjectId = mProjectId;
    }

    @Override
    @SuppressWarnings({"ConstantConditions", "Convert2streamapi"})
    public List<CharSequence> getResults(CharSequence constraint) {
        if (TextUtils.isEmpty(mProjectId)) {
            return new ArrayList<>();
        }

        final GerritApi api = ModelHelper.getGerritApi(getContext());
        List<BranchInfo> branches = api.getProjectBranches(
                mProjectId, null, null, null, null).blockingFirst();
        List<CharSequence> results = new ArrayList<>(branches.size());
        for (BranchInfo branch : branches) {
            if (branch.ref.startsWith(Constants.REF_HEADS)) {
                String v = branch.ref.substring(Constants.REF_HEADS.length());

                // Exclude current branch
                if (mBranch == null || !mBranch.equals(v)) {
                    results.add(v);
                }
            }
        }
        return results;
    }
}
