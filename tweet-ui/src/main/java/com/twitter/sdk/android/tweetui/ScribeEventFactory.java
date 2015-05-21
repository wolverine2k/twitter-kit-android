package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.ScribeEvent;
import com.twitter.sdk.android.core.internal.scribe.SyndicatedSdkImpressionEvent;

class ScribeEventFactory {
    static ScribeEvent newScribeEvent(EventNamespace ns, long timestamp, String language,
            String advertisingId) {
        switch (ns.client) {
            case SyndicationClientEvent.CLIENT_NAME:
                return new SyndicationClientEvent(ns, timestamp, language, advertisingId);
            default:
                return new SyndicatedSdkImpressionEvent(ns, timestamp, language, advertisingId);
        }
    }
}
