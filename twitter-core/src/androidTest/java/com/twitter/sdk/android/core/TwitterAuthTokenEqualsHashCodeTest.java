package com.twitter.sdk.android.core;

import junitx.extensions.EqualsHashCodeTestCase;

public class TwitterAuthTokenEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public TwitterAuthTokenEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return new TwitterAuthToken("token", "secret");
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new TwitterAuthToken("differentToken", "differentSecret");
    }
}
