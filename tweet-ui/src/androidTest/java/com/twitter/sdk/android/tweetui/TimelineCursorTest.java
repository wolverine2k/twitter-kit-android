package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import java.util.ArrayList;
import java.util.List;

public class TimelineCursorTest extends TwitterAndroidTestCase {
    private static final Long TEST_MAX_POSITION = 200L;
    private static final Long TEST_MIN_POSITION = 100L;

    public void testConstructor() {
        final TimelineCursor cursor = new TimelineCursor(TEST_MIN_POSITION, TEST_MAX_POSITION);
        assertEquals(TEST_MIN_POSITION, cursor.minPosition);
        assertEquals(TEST_MAX_POSITION, cursor.maxPosition);
    }

    public void testConstructor_withList() {
        final List<TestItem> testItems = new ArrayList<>();
        testItems.add(new TestItem(TEST_MAX_POSITION));
        testItems.add(new TestItem(TEST_MIN_POSITION));
        final TimelineCursor cursor = new TimelineCursor(testItems);
        assertEquals(TEST_MIN_POSITION, cursor.minPosition);
        assertEquals(TEST_MAX_POSITION, cursor.maxPosition);
    }
}
