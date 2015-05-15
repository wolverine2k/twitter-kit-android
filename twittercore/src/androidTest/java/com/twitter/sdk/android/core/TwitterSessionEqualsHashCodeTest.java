package com.twitter.sdk.android.core;

import junitx.extensions.EqualsHashCodeTestCase;

public class TwitterSessionEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public TwitterSessionEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return new TwitterSession(new TwitterAuthToken("token", "secret"), 1L, "userName");
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new TwitterSession(new TwitterAuthToken("differentToken", "differentSecret"), 2L,
                "differentUserName");
    }
}
