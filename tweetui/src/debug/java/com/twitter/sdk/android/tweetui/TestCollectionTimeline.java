package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.models.Tweet;

public class TestCollectionTimeline extends CollectionTimeline {

    TestCollectionTimeline(TweetUi tweetUi, Long collectionId, Integer count) {
        super(tweetUi, collectionId, count);
    }

    @Override
    public void addRequest(Callback<TwitterApiClient> cb) {
        super.addRequest(cb);
    }

    @Override
    public Callback<TwitterApiClient> createCollectionRequest(Long minPosition, Long maxPosition,
            Callback<TimelineResult<Tweet>> cb) {
        return super.createCollectionRequest(minPosition, maxPosition, cb);
    }
}
