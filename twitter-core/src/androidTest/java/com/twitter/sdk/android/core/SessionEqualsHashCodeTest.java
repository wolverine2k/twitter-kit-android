package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;

import junitx.extensions.EqualsHashCodeTestCase;

public class SessionEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public SessionEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return new Session<OAuth2Token>(new OAuth2Token("tokenType", "accessToken"), 1L);
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new Session<OAuth2Token>(
                new OAuth2Token("differentTokenType", "differentAccessToken"), 2L);
    }
}
