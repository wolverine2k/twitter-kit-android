package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Collection of relevant Tweets matching a specified query.
 */
public class Search {
    @SerializedName("statuses")
    public final List<Tweet> tweets;

    @SerializedName("search_metadata")
    public final SearchMetadata searchMetadata;

    public Search(List<Tweet> tweets, SearchMetadata searchMetadata) {
        this.tweets = tweets;
        this.searchMetadata = searchMetadata;
    }
}
