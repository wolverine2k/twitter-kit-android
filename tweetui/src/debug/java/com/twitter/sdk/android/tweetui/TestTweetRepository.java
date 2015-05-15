package com.twitter.sdk.android.tweetui;

import android.os.Handler;

import java.util.concurrent.ExecutorService;

public class TestTweetRepository extends TweetRepository {
    TestTweetRepository(TweetUi tweetUiKit, ExecutorService executorService,
                        Handler mainHandler, AuthRequestQueue queue) {
        super(tweetUiKit, executorService, mainHandler, queue);
    }
}
