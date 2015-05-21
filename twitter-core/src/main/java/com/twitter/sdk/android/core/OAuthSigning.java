package com.twitter.sdk.android.core;

import io.fabric.sdk.android.services.network.HttpMethod;

import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aHeaders;

import java.util.Map;

/**
 * Provides convenience methods for generating OAuth Headers for Twitter
 **/
public class OAuthSigning {
    static final String VERIFY_CREDENTIALS_URL = TwitterApi.BASE_HOST_URL +
            "/1.1/account/verify_credentials.json";

    protected final TwitterAuthConfig authConfig;
    protected final TwitterAuthToken authToken;
    protected final OAuth1aHeaders oAuth1aHeaders;

    /**
     * Constructs OAuthSigning with TwitterAuthConfig and TwitterAuthToken
     *
     * @param authConfig The auth config.
     * @param authToken  The auth token to use to sign the request.
     */
    public OAuthSigning(TwitterAuthConfig authConfig, TwitterAuthToken authToken) {
        this(authConfig, authToken, new OAuth1aHeaders());
    }

    public OAuthSigning(TwitterAuthConfig authConfig, TwitterAuthToken authToken,
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
     * Returns OAuth Echo header using given parameters.
     *
     * OAuth Echo allows you to securely delegate an API request to a third party. For example,
     * you may wish to verify a users credentials from your backend (i.e. the third party). This
     * method provides the OAuth parameters required to make an authenticated request from your
     * backend.
     *
     * @param method     The HTTP method (GET, POST, PUT, DELETE, etc).
     * @param url        The url delegation should be sent to (e.g. https://api.twitter.com/1.1/account/verify_credentials.json).
     * @param postParams The post parameters.
     * @return A map of OAuth Echo headers
     * @see <a href="https://dev.twitter.com/oauth/echo">OAuth Echo</a>
     */
    public Map<String, String> getOAuthEchoHeaders(String method, String url,
            Map<String, String> postParams) {
        return oAuth1aHeaders.getOAuthEchoHeaders(authConfig, authToken, null, method, url,
                postParams);
    }

    /**
     * Returns OAuth Echo header for <a href="https://dev.twitter.com/rest/reference/get/account/verify_credentials">verify_credentials</a> endpoint.
     *
     * @return A map of OAuth Echo headers
     * @see {@link #getOAuthEchoHeaders(String, String, java.util.Map)}
     */
    public Map<String, String> getOAuthEchoHeadersForVerifyCredentials() {
        return oAuth1aHeaders.getOAuthEchoHeaders(authConfig, authToken, null,
                HttpMethod.GET.name(), VERIFY_CREDENTIALS_URL, null);
    }

}
