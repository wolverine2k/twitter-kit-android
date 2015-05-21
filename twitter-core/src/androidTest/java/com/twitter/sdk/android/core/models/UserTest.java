package com.twitter.sdk.android.core.models;

import io.fabric.sdk.android.services.common.CommonUtils;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;

public class UserTest extends TwitterAndroidTestCase {

    private static final long EXPECTED_ID = 795649L;
    private static final String EXPECTED_NAME = "Ryan Sarver";
    private static final String EXPECTED_SCREEN_NAME = "rsarver";
    private static final String EXPECTED_PROFILE_IMAGE_URL_HTTPS
            = "https://si0.twimg.com/profile_images/1777569006/image1327396628_normal.png";
    private static final boolean EXPECTED_VERIFIED = false;

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new Gson();
    }

    // TODO: Add tests for serialization if this model will be used for serialization.

    public void testDeserialization() throws IOException {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(
                    getContext().getAssets().open("model_user.json")));
            final User user = gson.fromJson(reader, User.class);
            // We simply assert that we parsed it successfully and rely on our other unit tests to
            // verify parsing of the individual objects.
            assertEquals(EXPECTED_ID, user.id);
            assertEquals(EXPECTED_ID, user.getId());
            assertEquals(EXPECTED_NAME, user.name);
            assertTrue(user.entities.url.urls.size() > 0);
            assertTrue(user.entities.description.urls.isEmpty());
            assertEquals(EXPECTED_SCREEN_NAME, user.screenName);
            assertEquals(EXPECTED_PROFILE_IMAGE_URL_HTTPS, user.profileImageUrlHttps);
            assertEquals(EXPECTED_VERIFIED, user.verified);
            assertNotNull(user.status);
        } finally {
            CommonUtils.closeQuietly(reader);
        }
    }

    // TODO: Add unit tests for auto-generated User.Builder?
}
