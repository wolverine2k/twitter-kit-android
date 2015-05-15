package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;

import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class BaseTimelineTest extends TwitterAndroidTestCase {
    private static final String ILLEGAL_TWEET_UI_MESSAGE = "TweetUi instance must not be null";
    private static final Long TEST_ID = 200L;
    private static final String TEST_SCRIBE_SECTION = "test";
    private static final String REQUIRED_SDK_IMPRESSION_CLIENT = "android";
    private static final String REQUIRED_SDK_IMPRESSION_PAGE = "timeline";
    private static final String REQUIRED_SDK_IMPRESSION_COMPONENT = "initial";
    private static final String REQUIRED_SDK_IMPRESSION_ELEMENT = "";
    private static final String REQUIRED_TFW_CLIENT = "tfw";
    private static final String REQUIRED_TFW_PAGE = "android";
    private static final String REQUIRED_TFW_SECTION = "timeline";
    private static final String REQUIRED_TFW_ELEMENT = "initial";
    private static final String REQUIRED_IMPRESSION_ACTION = "impression";

    public void testConstructor() {
        final TweetUi tweetUi = mock(TweetUi.class);
        final TestBaseTimeline baseTimeline = new TestBaseTimeline(tweetUi);
        assertEquals(tweetUi, baseTimeline.tweetUi);
    }

    public void testConstructor_nullTweetUi() {
        try {
            new TestBaseTimeline(null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(ILLEGAL_TWEET_UI_MESSAGE, e.getMessage());
        }
    }

    public void testConstructor_scribesImpression() {
        final TweetUi tweetUi = mock(TestTweetUi.class);
        final ArgumentCaptor<EventNamespace> sdkNamespaceCaptor
                = ArgumentCaptor.forClass(EventNamespace.class);
        final ArgumentCaptor<EventNamespace> tfwNamespaceCaptor
                = ArgumentCaptor.forClass(EventNamespace.class);
        new TestBaseTimeline(tweetUi);
        verify(tweetUi).scribe(sdkNamespaceCaptor.capture(), tfwNamespaceCaptor.capture());

        final EventNamespace sdkNs = sdkNamespaceCaptor.getValue();

        assertEquals(REQUIRED_SDK_IMPRESSION_CLIENT, sdkNs.client);
        assertEquals(REQUIRED_SDK_IMPRESSION_PAGE, sdkNs.page);
        assertEquals(TEST_SCRIBE_SECTION, sdkNs.section);
        assertEquals(REQUIRED_SDK_IMPRESSION_COMPONENT, sdkNs.component);
        assertEquals(REQUIRED_SDK_IMPRESSION_ELEMENT, sdkNs.element);
        assertEquals(REQUIRED_IMPRESSION_ACTION, sdkNs.action);

        final EventNamespace tfwNs = tfwNamespaceCaptor.getValue();

        assertEquals(REQUIRED_TFW_CLIENT, tfwNs.client);
        assertEquals(REQUIRED_TFW_PAGE, tfwNs.page);
        assertEquals(REQUIRED_TFW_SECTION, tfwNs.section);
        assertEquals(TEST_SCRIBE_SECTION, tfwNs.component);
        assertEquals(REQUIRED_TFW_ELEMENT, tfwNs.element);
        assertEquals(REQUIRED_IMPRESSION_ACTION, tfwNs.action);
    }

    public void testDecrementMaxId_positive() {
        final Long correctedId = BaseTimeline.decrementMaxId(TEST_ID);
        assertEquals((Long) (TEST_ID - 1L), correctedId);
    }

    public void testDecrementMaxId_nullId() {
        final Long correctedId = BaseTimeline.decrementMaxId(null);
        assertNull(correctedId);
    }

    /* Extends abstract BaseTimeline for testing */
    public class TestBaseTimeline extends BaseTimeline {
        TestBaseTimeline(TweetUi tweetUi) {
            super(tweetUi);
        }

        @Override
        String getTimelineType() {
            return TEST_SCRIBE_SECTION;
        }
    }
}
