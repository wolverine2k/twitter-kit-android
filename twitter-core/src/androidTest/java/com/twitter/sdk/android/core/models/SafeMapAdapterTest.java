package com.twitter.sdk.android.core.models;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Map;

public class SafeMapAdapterTest extends TwitterAndroidTestCase {
    private static final String TEST_JSON_MAP_EMPTY = "{\"map\":{}}";
    private static final String TEST_JSON_MAP_STRING_VALUES
            = "{\"map\": {\"k1\": \"v1\",\"k2\": \"v2\"}}";
    private static final String TEST_JSON_MAP_NUMBER_VALUES = "{\"map\": {\"k1\": 1,\"k2\": 2}}";

    private static final String TEST_ANY_STRING_KEY = "any key";
    private static final String TEST_ANY_STRING_VALUE = "any value";
    private static final int TEST_ANY_NUMBER = 100;

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new GsonBuilder().registerTypeAdapterFactory(new SafeMapAdapter()).create();
    }

    public void testDeserialization_emptyMapModel1() {
        final Model1 model = gson.fromJson(TEST_JSON_MAP_EMPTY, Model1.class);
        assertEquals(Collections.EMPTY_MAP, model.mapOfStrings);
    }

    public void testDeserialization_validMapModel1() {
        final Model1 model = gson.fromJson(TEST_JSON_MAP_STRING_VALUES, Model1.class);
        try {
            model.mapOfStrings.put(TEST_ANY_STRING_KEY, TEST_ANY_STRING_VALUE);
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    public void testDeserialization_emptyList() {
        final Model2 model = gson.fromJson(TEST_JSON_MAP_EMPTY, Model2.class);
        assertEquals(Collections.EMPTY_MAP, model.stringLongMap);
    }

    public void testDeserialization_validMapModel2() {
        final Model2 model = gson.fromJson(TEST_JSON_MAP_NUMBER_VALUES, Model2.class);
        try {
            model.stringLongMap.put(TEST_ANY_STRING_KEY, Long.valueOf(TEST_ANY_NUMBER));
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    private static class Model1 {

        @SerializedName("map")
        public final Map<String, String> mapOfStrings;

        // Not used in testing, but needed because of final.
        public Model1(Map<String, String> mapOfStrings) {
            this.mapOfStrings = mapOfStrings;
        }
    }

    private static class Model2 {

        @SerializedName("map")
        public final Map<String, Long> stringLongMap;

        // Not used in testing, but needed because of final.
        public Model2(Map<String, Long> stringLongMap) {
            this.stringLongMap = stringLongMap;
        }
    }

}
