package com.digits.sdk.android;

import com.twitter.sdk.android.core.internal.TwitterApi;

public class DigitsApi extends TwitterApi {
    public static final String BASE_HOST = "api.digits.com";
    public static final String BASE_HOST_URL = "https://" + BASE_HOST;

    public DigitsApi() {
        super(BASE_HOST_URL);
    }
}
