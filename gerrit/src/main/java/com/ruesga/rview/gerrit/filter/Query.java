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
package com.ruesga.rview.gerrit.filter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

public abstract class Query {

    private SimpleDateFormat mTimeFormatter;

    private final ArrayList<String> mQueries = new ArrayList<>();

    ArrayList<String> queries() {
        return mQueries;
    }

    void add(String query) {
        mQueries.add(query);
    }

    void remove(String query) {
        mQueries.remove(query);
    }

    void clear() {
        mQueries.clear();
    }

    static String sanitizeValue(String val) {
        return "\"" + val.trim().replace("\"", "\"\"") + "\"";
    }

    // NOTE: This reference is not thread-safe so caller must ensure reference synchronization
    // it this reference is used from difference threads
    SimpleDateFormat getTimeFormatter() {
        if (mTimeFormatter == null) {
            mTimeFormatter = new SimpleDateFormat("'\"'yyyy-MM-dd HH:mm:ss.SSS Z'\"'", Locale.US);
            mTimeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return mTimeFormatter;
    }

    @Override
    public abstract String toString();
}
