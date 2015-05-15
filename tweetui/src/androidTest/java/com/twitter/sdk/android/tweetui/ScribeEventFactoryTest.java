package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.ScribeEvent;
import com.twitter.sdk.android.core.internal.scribe.SyndicatedSdkImpressionEvent;

public class ScribeEventFactoryTest extends TwitterAndroidTestCase {
    private static long ANY_TIMESTAMP = 0;
    private static String ANY_ADVERTISING_ID = "id";
    private static String ANY_LANGUAGE = "lang";

    private static final String TFW_CLIENT_NAME = "tfw";
    private static final String SDK_CLIENT_NAME = "android";
    private static final String OTHER_CLIENT_NAME = "other";

    public void testNewScribeEvent_tfwEvent() {
        final EventNamespace ns
                = new EventNamespace.Builder().setClient(TFW_CLIENT_NAME).builder();
        final ScribeEvent event = ScribeEventFactory.newScribeEvent(ns, ANY_TIMESTAMP,
                ANY_LANGUAGE, ANY_ADVERTISING_ID);
        assertEquals(SyndicationClientEvent.class, event.getClass());
    }

    public void testNewScribeEvent_sdkEvent() {
        final EventNamespace ns
                = new EventNamespace.Builder().setClient(SDK_CLIENT_NAME).builder();
        final ScribeEvent event = ScribeEventFactory.newScribeEvent(ns, ANY_TIMESTAMP,
                ANY_LANGUAGE, ANY_ADVERTISING_ID);
        assertEquals(SyndicatedSdkImpressionEvent.class, event.getClass());
    }

    public void testNewScribeEvent_otherEvent() {
        final EventNamespace ns
                = new EventNamespace.Builder().setClient(OTHER_CLIENT_NAME).builder();
        final ScribeEvent event = ScribeEventFactory.newScribeEvent(ns, ANY_TIMESTAMP,
                ANY_LANGUAGE, ANY_ADVERTISING_ID);
        assertEquals(SyndicatedSdkImpressionEvent.class, event.getClass());
    }
}
