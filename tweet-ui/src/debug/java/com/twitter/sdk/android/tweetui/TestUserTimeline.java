package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.models.Tweet;

public class TestUserTimeline extends UserTimeline {

    TestUserTimeline(TweetUi tweetUi, Long userId, String screenName, Integer count,
                     Boolean excludeReplies, Boolean includeRetweets) {
        super(tweetUi, userId, screenName, count, excludeReplies, includeRetweets);
    }

    @Override
    public void addRequest(Callback<TwitterApiClient> cb) {
        super.addRequest(cb);
    }

    @Override
    public Callback<TwitterApiClient> createUserTimelineRequest(Long sinceId, Long maxId,
            Callback<TimelineResult<Tweet>> cb) {
        return super.createUserTimelineRequest(sinceId, maxId, cb);
    }
}
