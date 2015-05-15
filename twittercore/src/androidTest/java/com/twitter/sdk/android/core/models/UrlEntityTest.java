package com.twitter.sdk.android.core.models;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;

import java.io.IOException;

public class UrlEntityTest extends TwitterAndroidTestCase {

    private static final String TEST_JSON
            = "{\"indices\":[32,52], \"url\":\"http:\\/\\/t.co\\/IOwBrTZR\","
            + "\"display_url\":\"youtube.com\\/watch?v=oHg5SJ\\u2026\","
            + "\"expanded_url\":\"http:\\/\\/www.youtube.com\\/watch?v=oHg5SJYRHA0\"}";
    private static final int TEST_INDICES_START = 32;
    private static final int TEST_INDICES_END = 52;
    private static final String TEST_URL = "http://t.co/IOwBrTZR";
    private static final String TEST_DISPLAY_URL = "youtube.com/watch?v=oHg5SJ\u2026";
    private static final String TEST_EXPANDED_URL = "http://www.youtube.com/watch?v=oHg5SJYRHA0";

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new Gson();
    }

    // TODO: Add tests for serialization if this model will be used for serialization.

    public void testDeserialization() throws IOException {
        final UrlEntity entity = gson.fromJson(TEST_JSON, UrlEntity.class);
        assertEquals(TEST_INDICES_START, entity.getStart());
        assertEquals(TEST_INDICES_END, entity.getEnd());
        assertEquals(TEST_URL, entity.url);
        assertEquals(TEST_DISPLAY_URL, entity.displayUrl);
        assertEquals(TEST_EXPANDED_URL, entity.expandedUrl);
    }
}
