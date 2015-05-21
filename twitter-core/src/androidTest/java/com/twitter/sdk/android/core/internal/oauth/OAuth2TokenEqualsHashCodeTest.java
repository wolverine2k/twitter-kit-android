package com.twitter.sdk.android.core.internal.oauth;

import junitx.extensions.EqualsHashCodeTestCase;

public class OAuth2TokenEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public OAuth2TokenEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return new OAuth2Token("tokenType", "accessToken");
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new OAuth2Token("differrentTokenType", "differentAccessToken");
    }
}
