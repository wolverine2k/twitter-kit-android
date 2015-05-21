package com.twitter.sdk.android.core.internal.oauth;

import junitx.extensions.EqualsHashCodeTestCase;

public class GuestAuthTokenEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public GuestAuthTokenEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return new GuestAuthToken("tokenType", "accessToken", "guestToken");
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new GuestAuthToken("differentTokenType", "differentAccessToken",
                "differentGuestToken");
    }
}
