package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.Identifiable;

import java.util.List;

/**
 * A TestItem Identifiable for testing components generic to Identifiable types. Avoids the need
 * to test against a concrete Identifiable.
 */
public class TestItem implements Identifiable {

    private final long id;

    public TestItem(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    public static <T extends List> T populateList(T items, long count) {
        for (int i = 0; i < count; i++) {
            // add 100 just so ids are clearly distinct from position
            items.add(new TestItem(100 + i));
        }
        return items;
    }
}
