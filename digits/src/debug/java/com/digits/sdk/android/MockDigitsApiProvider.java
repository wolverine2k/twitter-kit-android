package com.digits.sdk.android;

import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.concurrent.ExecutorService;

import javax.net.ssl.SSLSocketFactory;

public class MockDigitsApiProvider extends DigitsApiProvider {

    public MockDigitsApiProvider(DigitsSession session, TwitterAuthConfig authConfig,
            SSLSocketFactory sslFactory, ExecutorService executorService,
            DigitsUserAgent userAgent) {
        super(session, authConfig, sslFactory, executorService, userAgent);
    }

}
