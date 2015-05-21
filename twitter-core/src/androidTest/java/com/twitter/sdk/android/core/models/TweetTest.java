package com.twitter.sdk.android.core.models;

import io.fabric.sdk.android.services.common.CommonUtils;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;

public class TweetTest extends TwitterAndroidTestCase {

    private static final String EXPECTED_CREATED_AT = "Wed Jun 06 20:07:10 +0000 2012";
    private static final long EXPECTED_ID = 210462857140252672L;
    private static final String EXPECTED_TEXT = "Along with our new #Twitterbird, we've also updated our Display Guidelines: https://t.co/Ed4omjYs  ^JC";

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new Gson();
    }

    // TODO: Add tests for serialization if these models will be used for serialization.

    public void testDeserialization() throws IOException {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(
                    getContext().getAssets().open("model_tweet.json")));
            final Tweet tweet = gson.fromJson(reader, Tweet.class);
            // We simply assert that we parsed it successfully and rely on our other unit tests to
            // verify parsing of the individual objects.
            assertEquals(EXPECTED_CREATED_AT, tweet.createdAt);
            assertNotNull(tweet.entities);
            assertEquals(EXPECTED_ID, tweet.id);
            assertEquals(EXPECTED_ID, tweet.getId());
            assertEquals(EXPECTED_TEXT, tweet.text);
            assertNotNull(tweet.user);
        } finally {
            CommonUtils.closeQuietly(reader);
        }
    }

    // TODO: Add unit tests for auto-generated Tweet.Builder?
}
