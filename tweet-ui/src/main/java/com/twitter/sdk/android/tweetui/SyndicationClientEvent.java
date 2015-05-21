package com.twitter.sdk.android.tweetui;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.ScribeEvent;

/**
 * SyndicationClientEvent is nearly identical to SyndicatedSdkImpression. This class should be
 * considered the legacy event and will be eventually deprecated in favor of SyndicatedSdkImpression
 * events. Events of this type will end up in the /logs/tfw_client_event folder in hdfs.
 */
class SyndicationClientEvent extends ScribeEvent {
    static final String CLIENT_NAME = "tfw";

    private static final String SCRIBE_CATEGORY = "tfw_client_event";

    /**
     * The current language that the application is running in.
     * Optional field.
     */
    @SerializedName("language")
    final String language;

    /**
     * External Ids can contain other external ids (e.g. Facebook) but in our case we will only
     * scribe the advertising id.
     * Optional field.
     */
    @SerializedName("external_ids")
    final ExternalIds externalIds;

    SyndicationClientEvent(EventNamespace eventNamespace, long timestamp, String language,
                           String adId) {
        super(SCRIBE_CATEGORY, eventNamespace, timestamp);
        this.language = language;
        externalIds = new ExternalIds(adId);
    }

    class ExternalIds {
        /**
         * The advertising id.
         *
         * Alert! This serialized name may seem wrong, however...
         * see: source/science/src/thrift/com/twitter/clientapp/gen/client_app.thrift for number
         * this is parsed by marshalExternalIds in
         * source/science/src/scala/com/twitter/scribelib/ClientEventMarshaller.java
         *
         * Optional field.
         */
        @SerializedName("6")
        final String adId;

        ExternalIds(String adId) {
            this.adId = adId;
        }
    }
}
