package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.Map;

public interface AuthHeaders {
    static final String HEADER_AUTHORIZATION = "Authorization";

    Map<String, String> getAuthHeaders(TwitterAuthConfig authConfig, String method, String url,
            Map<String, String> postParams);
}
