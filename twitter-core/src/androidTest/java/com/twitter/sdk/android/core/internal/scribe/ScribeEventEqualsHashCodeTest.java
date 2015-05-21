package com.twitter.sdk.android.core.internal.scribe;

import junitx.extensions.EqualsHashCodeTestCase;

public class ScribeEventEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public ScribeEventEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return new ScribeEvent("category",
                new EventNamespace("client", "page", "section", "component", "element", "action"),
                1L);
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new ScribeEvent("differentCategory",
                new EventNamespace("differentClient", "differentPage", "differentSection",
                        "differentComponent", "differentElement", "differentAction"), 2L);
    }
}
