package com.twitter.sdk.android.core.models;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;

import java.io.IOException;

public class CoordinatesTest extends TwitterAndroidTestCase {

    private static final String TEST_JSON = "{\n"
            + "    \"coordinates\":\n"
            + "    [\n"
            + "        -75.14310264,\n"
            + "        40.05701649\n"
            + "    ],\n"
            + "    \"type\":\"Point\"\n"
            + "}\n";
    private static final Double TEST_COORDINATES_LONGITUDE = Double.valueOf(-75.14310264);
    private static final Double TEST_COORDINATES_LATITUDE = Double.valueOf(40.05701649);
    private static final String TEST_TYPE = "Point";

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new Gson();
    }

    // TODO: Add tests for serialization if these models will be used for serialization.

    public void testDeserialization() throws IOException {
        final Coordinates coordinates = gson.fromJson(TEST_JSON, Coordinates.class);
        assertEquals(TEST_COORDINATES_LONGITUDE, coordinates.getLongitude());
        assertEquals(TEST_COORDINATES_LATITUDE, coordinates.getLatitude());
        assertEquals(TEST_TYPE, coordinates.type);
    }
}
