package com.twitter.sdk.android.core.models;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;

import java.io.IOException;

public class MentionEntityTest extends TwitterAndroidTestCase {

    private static final String TEST_JSON = "{\"name\":\"Twitter API\","
            + "\"indices\":[4,15], \"screen_name\":\"twitterapi\","
            + "\"id\":6253282, \"id_str\":\"6253282\"}";
    private static final int TEST_INDICES_START = 4;
    private static final int TEST_INDICES_END = 15;
    private static final long TEST_ID = 6253282L;
    private static final String TEST_ID_STR = "6253282";
    private static final String TEST_NAME = "Twitter API";
    private static final String TEST_SCREEN_NAME = "twitterapi";

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new Gson();
    }

    // TODO: Add tests for serialization if this model will be used for serialization.

    public void testDeserialization() throws IOException {
        final MentionEntity entity = gson.fromJson(TEST_JSON, MentionEntity.class);
        assertEquals(TEST_INDICES_START, entity.getStart());
        assertEquals(TEST_INDICES_END, entity.getEnd());
        assertEquals(TEST_ID, entity.id);
        assertEquals(TEST_ID_STR, entity.idStr);
        assertEquals(TEST_NAME, entity.name);
        assertEquals(TEST_SCREEN_NAME, entity.screenName);
    }
}
