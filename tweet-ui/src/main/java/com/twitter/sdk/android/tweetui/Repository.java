package com.twitter.sdk.android.tweetui;

import android.os.Handler;

import java.util.concurrent.ExecutorService;

abstract class Repository {
    protected final TweetUi tweetUiKit;
    protected final ExecutorService executorService;
    protected final Handler mainHandler;
    protected final AuthRequestQueue queue;

    public Repository(TweetUi tweetUiKit, ExecutorService executorService,
                      Handler mainHandler, AuthRequestQueue queue) {

        this.tweetUiKit = tweetUiKit;
        this.executorService = executorService;
        this.mainHandler = mainHandler;
        this.queue = queue;
    }
}
