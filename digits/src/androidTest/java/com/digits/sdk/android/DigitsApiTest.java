package com.digits.sdk.android;

public class DigitsApiTest extends DigitsAndroidTestCase {
    private static final String DIGITS_BASE_URL = "https://api.digits.com";

    public void testConstructor() throws Exception {
        final DigitsApi digitsApi = new DigitsApi();
        assertEquals(DIGITS_BASE_URL, DigitsApi.BASE_HOST_URL);
        assertEquals(DigitsApi.BASE_HOST_URL, digitsApi.getBaseHostUrl());
    }
}
