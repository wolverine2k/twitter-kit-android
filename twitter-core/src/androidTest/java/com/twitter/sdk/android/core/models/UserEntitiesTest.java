package com.twitter.sdk.android.core.models;

import io.fabric.sdk.android.services.common.CommonUtils;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class UserEntitiesTest extends TwitterAndroidTestCase {

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
                    getContext().getAssets().open("model_userentities.json")));
            final UserEntities userEntities = gson.fromJson(reader, UserEntities.class);
            // We simply assert that we parsed it successfully and rely on our other unit tests to
            // verify parsing of the individual objects.
            assertNotNull(userEntities.url);
            assertFalse(userEntities.url.urls.isEmpty());

            assertNotNull(userEntities.description);
            assertEquals(Collections.EMPTY_LIST, userEntities.description.urls);
        } finally {
            CommonUtils.closeQuietly(reader);
        }
    }
}
