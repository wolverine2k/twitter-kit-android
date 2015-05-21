package com.digits.sdk.android;

class DigitsUserAgent {
    private final String digitsVersion;
    private final String androidVersion;

    DigitsUserAgent(String digitsVersion, String androidVersion) {
        this.digitsVersion = digitsVersion;
        this.androidVersion = androidVersion;

    }

    public String toString() {
        return "Digits/" + digitsVersion + " (Android " + androidVersion + ")";
    }
}
