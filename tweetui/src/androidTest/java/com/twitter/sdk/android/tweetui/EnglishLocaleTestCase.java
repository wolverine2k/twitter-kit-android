package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import java.util.Locale;

/**
 * Use this test case if you are making assertions about English specific format strings that will
 * break if the locale changes.
 */
public class EnglishLocaleTestCase extends TwitterAndroidTestCase {
    private Locale defaultLocale;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        defaultLocale = TestUtils.setLocale(getContext(), Locale.ENGLISH);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        TestUtils.setLocale(getContext(), defaultLocale);
    }
}
