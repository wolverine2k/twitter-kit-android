package com.digits.sdk.android;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import static org.mockito.Mockito.verifyZeroInteractions;

public class DigitsAndroidTestCase extends TwitterAndroidTestCase {

    protected static final String RAW_PHONE = "+123456789";
    protected static final String PHONE = "123456789";
    protected static final String PHONE_NO_COUNTRY_CODE = "23456789";
    protected static final String PHONE_PLUS_SYMBOL_NO_COUNTRY_CODE = "23456789";
    protected static final String US_COUNTRY_CODE = "1";
    protected static final String US_ISO2 = "us";
    protected static final String US_ISO3 = "usa";

    protected void verifyNoInteractions(Object... objects) {
        for (Object object : objects) {
            verifyZeroInteractions(object);
        }
    }
}
