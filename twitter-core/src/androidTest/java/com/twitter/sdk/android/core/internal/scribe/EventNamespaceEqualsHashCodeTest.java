package com.twitter.sdk.android.core.internal.scribe;

import junitx.extensions.EqualsHashCodeTestCase;

public class EventNamespaceEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public EventNamespaceEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() throws Exception {
        return new EventNamespace("client", "page", "section", "component", "element", "action");
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new EventNamespace("differentClient", "differentPage", "differentSection",
                "differentComponent", "differentElement", "differentAction");
    }
}
