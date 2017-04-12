/*
 * Copyright (C) 2015 Twitter, Inc.
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
 *
 */

package com.twitter.sdk.android.tweetui;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class FilterValues {

    @SerializedName("keywords")
    public final List<String> keywords;

    @SerializedName("hashtags")
    public final List<String> hashtags;

    @SerializedName("handles")
    public final List<String> handles;

    @SerializedName("urls")
    public final List<String> urls;

    public FilterValues(List<String> keywords, List<String> hashtags,
                        List<String> handles, List<String> urls) {
        this.keywords = getSafeList(keywords);
        this.hashtags = getSafeList(hashtags);
        this.handles = getSafeList(handles);
        this.urls = getSafeList(urls);
    }

    private <T> List<T> getSafeList(List<T> filters) {
        // Entities may be null if Gson does not find object to parse. When that happens, make sure
        // to return an empty list.
        if (filters == null) {
            return Collections.EMPTY_LIST;
        } else {
            return Collections.unmodifiableList(filters);
        }
    }
}
