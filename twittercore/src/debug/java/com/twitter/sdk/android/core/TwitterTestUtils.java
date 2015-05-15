package com.twitter.sdk.android.core;

import java.util.concurrent.ConcurrentHashMap;

public final class TwitterTestUtils {

    private TwitterTestUtils() {}

    public static TwitterCore createTwitter(TwitterAuthConfig authConfig,
                                     ConcurrentHashMap<Session, TwitterApiClient> clients) {
        return new TwitterCore(authConfig, clients);
    }
}
