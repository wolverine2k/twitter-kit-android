package com.twitter.sdk.android.core;

import android.app.Activity;

import io.fabric.sdk.android.FabricActivityTestCase;

public class TwitterActivityTestCase<T extends Activity> extends FabricActivityTestCase<T> {

    protected static final String CONSUMER_KEY = "testKey";
    protected static final String CONSUMER_SECRET = "testSecret";
    protected static final String TOKEN = "5ebe2294ecd0e0f08eab7690d2a6ee69";
    protected static final String SECRET = "94a08da1fecbb6e8b46990538c7b50b2";
    protected static final String USER = "rallat";
    protected static final long USER_ID = 15224484;
    protected static final String PHONE = "123456789";

    public TwitterActivityTestCase(Class<T> activityClass) {
        super(activityClass);
    }
}
