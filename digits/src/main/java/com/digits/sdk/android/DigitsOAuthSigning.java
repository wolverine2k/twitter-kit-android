package com.digits.sdk.android;

import io.fabric.sdk.android.services.network.HttpMethod;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aHeaders;

import java.util.Map;
/**
 * Provides helper methods to generate OAuth Headers for Digits
 **/
public class DigitsOAuthSigning {
    protected static final String VERIFY_CREDENTIALS_URL = DigitsApi.BASE_HOST_URL + "/1" +
            ".1/sdk/account.json";
    protected final TwitterAuthConfig authConfig;
    protected final TwitterAuthToken authToken;
    protected final OAuth1aHeaders oAuth1aHeaders;

    /**
     * Constructs OAuthSigning with TwitterAuthConfig and TwitterAuthToken
     *
     * @param authConfig The auth config.
     * @param authToken The auth token to use to sign the request.
     */
    public DigitsOAuthSigning(TwitterAuthConfig authConfig, TwitterAuthToken authToken) {
        this(authConfig, authToken, new OAuth1aHeaders());
    }

    DigitsOAuthSigning(TwitterAuthConfig authConfig, TwitterAuthToken authToken,
            OAuth1aHeaders oAuth1aHeaders) {
        if (authConfig == null) {
            throw new IllegalArgumentException("authConfig must not be null");
        }
        if (authToken == null) {
            throw new IllegalArgumentException("authToken must not be null");
        }

        this.authConfig = authConfig;
        this.authToken = authToken;
        this.oAuth1aHeaders = oAuth1aHeaders;
    }

    /**
     * Returns OAuth Echo header for <a href="https://api.digits.com/1.1/sdk/account.json</a>
     * endpoint.
     *
     * @return A map of OAuth Echo headers
     */
    public Map<String, String> getOAuthEchoHeadersForVerifyCredentials() {
        return oAuth1aHeaders.getOAuthEchoHeaders(authConfig, authToken, null,
                HttpMethod.GET.name(), VERIFY_CREDENTIALS_URL, null);
    }
}

