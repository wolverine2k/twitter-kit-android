package com.twitter.sdk.android.core.identity;

import com.twitter.sdk.android.core.TwitterSession;

/**
 * Test class to allow mocking of ShareEmailClient.
 */
public class TestShareEmailClient extends ShareEmailClient {
    public TestShareEmailClient(TwitterSession session) {
        super(session);
    }
}
