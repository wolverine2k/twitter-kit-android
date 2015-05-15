package com.twitter.sdk.android.core.services.params;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

public class GeocodeTest extends TwitterAndroidTestCase {

    public void testToString() {
        final Geocode geocode = new Geocode(1.0, 1.0, 1, Geocode.Distance.MILES);
        assertEquals("1.0,1.0,1mi", geocode.toString());
    }
}
