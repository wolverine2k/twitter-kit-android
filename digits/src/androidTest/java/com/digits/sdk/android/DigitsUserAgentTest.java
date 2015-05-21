package com.digits.sdk.android;

import io.fabric.sdk.android.FabricAndroidTestCase;

public class DigitsUserAgentTest extends FabricAndroidTestCase {
    private static final String ANY_ANDROID_VERSION = "5.0";
    private static final String ANY_KIT_VERSION = "1.3.0";

    public void testToString() throws Exception {
        final DigitsUserAgent userAgent = new DigitsUserAgent(ANY_KIT_VERSION, ANY_ANDROID_VERSION);
        assertEquals(userAgentString(ANY_KIT_VERSION, ANY_ANDROID_VERSION), userAgent.toString());
    }

    public void testToString_nullVersions() throws Exception {
        final DigitsUserAgent userAgent = new DigitsUserAgent(null, null);
        assertEquals(userAgentString(null, null), userAgent.toString());
    }


    protected String userAgentString(String digitsVersion, String androidVersion) {
        return "Digits/" + digitsVersion + " (Android " + androidVersion + ")";
    }
}
