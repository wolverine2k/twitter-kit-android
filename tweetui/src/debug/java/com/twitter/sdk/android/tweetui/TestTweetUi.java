package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.internal.scribe.EventNamespace;

public class TestTweetUi extends TweetUi {

    @Override
    public void scribe(EventNamespace... namespaces) {
        super.scribe(namespaces);
    }
}
