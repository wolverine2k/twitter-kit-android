package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.models.Tweet;

public class TestSearchTimeline extends SearchTimeline {

    TestSearchTimeline(TweetUi tweetUi, String query, String lang, Integer count) {
        super(tweetUi, query, lang, count);
    }

    @Override
    public void addRequest(Callback<TwitterApiClient> cb) {
        super.addRequest(cb);
    }

    @Override
    public Callback<TwitterApiClient> createSearchRequest(Long sinceId, Long maxId,
            Callback<TimelineResult<Tweet>> cb) {
        return super.createSearchRequest(sinceId, maxId, cb);
    }
}
