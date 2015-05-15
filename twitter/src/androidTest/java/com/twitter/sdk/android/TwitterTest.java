package com.twitter.sdk.android;

import android.test.AndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;

public class TwitterTest extends AndroidTestCase {
    private static final int KIT_COUNT = 4;

    private Twitter twitter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        twitter = new Twitter(new TwitterAuthConfig("", ""));
    }

    public void testGetVersion() {
        assertEquals(BuildConfig.VERSION_NAME + "." + BuildConfig.BUILD_NUMBER,
                twitter.getVersion());
    }

    public void testTwitter() {
        assertNotNull(twitter.core);
    }

    public void testTweetUi() {
        assertNotNull(twitter.tweetUi);
    }

    public void testTweetComposer() {
        assertNotNull(twitter.tweetComposer);
    }

    public void testDigits() {
        assertNotNull(twitter.digits);
    }

    public void testGetKits_notNull() {
        assertNotNull(twitter.getKits());
    }

    public void testGetKits_length() {
        assertEquals(KIT_COUNT, twitter.getKits().size());
    }

    public void testGetKits_containsTwitter() {
        assertTrue(twitter.getKits().contains(twitter.core));
    }

    public void testGetKits_containsTweetUi() {
        assertTrue(twitter.getKits().contains(twitter.tweetUi));
    }

    public void testGetKits_containsTweetComposer() {
        assertTrue(twitter.getKits().contains(twitter.tweetComposer));
    }

    public void testGetKits_containsDigits() {
        assertTrue(twitter.getKits().contains(twitter.digits));
    }
}
