package com.digits.sdk.android;

import retrofit.RequestInterceptor;

class DigitsRequestInterceptor implements RequestInterceptor {
    static final String USER_AGENT_KEY = "User-Agent";
    private final DigitsUserAgent userAgent;


    public DigitsRequestInterceptor(DigitsUserAgent userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(USER_AGENT_KEY, userAgent.toString());
    }
}
