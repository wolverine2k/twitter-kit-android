/*
 * Copyright (C) 2015 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.twitter.sdk.android.core;

import android.os.Parcel;

import io.fabric.sdk.android.FabricAndroidTestCase;

public class TwitterAuthConfigTest extends FabricAndroidTestCase {
    private static final String NO_PARAM_ERROR_MSG =
            "TwitterAuthConfig must not be created with null consumer key or secret.";

    private TwitterAuthConfig authConfig;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        authConfig = new TwitterAuthConfig(TestFixtures.KEY, TestFixtures.SECRET);
    }

    public void testParcelable() {
        final Parcel parcel = Parcel.obtain();
        authConfig.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final TwitterAuthConfig parceledAuthConfig
                = TwitterAuthConfig.CREATOR.createFromParcel(parcel);
        assertEquals(TestFixtures.KEY, parceledAuthConfig.getConsumerKey());
        assertEquals(TestFixtures.SECRET, parceledAuthConfig.getConsumerSecret());
    }

    public void testGetRequestCode() {
        assertEquals(TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE, authConfig.getRequestCode());
    }

    public void testSanitizeAttribute_nullAttribute() {
        assertNull(TwitterAuthConfig.sanitizeAttribute(null));
    }

    public void testSanitizeAttribute_sanitizedString() {
        final String test = "test";
        assertEquals(test, TwitterAuthConfig.sanitizeAttribute(test));
    }

    public void testSanitizeAttribute_trailingWhitespace() {
        final String test = "test    ";
        assertEquals("test", TwitterAuthConfig.sanitizeAttribute(test));
    }

    public void testConstructor_nullKey() {
        try {
            new TwitterAuthConfig(null, "secret");
            fail();
        } catch (IllegalArgumentException ie) {
            assertEquals(NO_PARAM_ERROR_MSG, ie.getMessage());
        }
    }

    public void testConstructor_nullSecret() {
        try {
            new TwitterAuthConfig("key", null);
            fail();
        } catch (IllegalArgumentException ie) {
            assertEquals(NO_PARAM_ERROR_MSG, ie.getMessage());
        }
    }

    public void testConstructor_nullArguments() {
        try {
            new TwitterAuthConfig(null, null);
            fail();
        } catch (IllegalArgumentException ie) {
            assertEquals(NO_PARAM_ERROR_MSG, ie.getMessage());
        }
    }
}
