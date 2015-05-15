package com.twitter.sdk.android.core;

import io.fabric.sdk.android.services.network.HttpMethod;

import com.twitter.sdk.android.core.internal.oauth.OAuth1aHeaders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OAuthSigningTests extends TwitterAndroidTestCase {
    private static final String ANY_AUTH_HEADER = "Digits Authority!";
    private static final String VERIFY_CREDENTIALS_URL = "http://digits.com";

    private TwitterAuthConfig authConfig;
    private TwitterAuthToken authToken;
    private OAuthSigning authSigning;
    private OAuth1aHeaders oAuthHeaders;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        oAuthHeaders = mock(OAuth1aHeaders.class);
        authConfig = new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET);
        authToken = new TwitterAuthToken(TOKEN, SECRET);
        authSigning = new OAuthSigning(authConfig, authToken, oAuthHeaders);

        when(oAuthHeaders.getAuthorizationHeader(authConfig, authToken, null, HttpMethod.GET.name(),
                OAuthSigning.VERIFY_CREDENTIALS_URL, null)).thenReturn(ANY_AUTH_HEADER);
    }

    public void testConstructor_nullAuthConfig() {
        try {
            new OAuthSigning(null, authToken);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("authConfig must not be null", e.getMessage());
        }
    }

    public void testConstructor_nullAuthToken() {
        try {
            new OAuthSigning(authConfig, null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("authToken must not be null", e.getMessage());
        }
    }

    public void testGetOAuthEchoHeaders() {
        authSigning.getOAuthEchoHeaders(HttpMethod.POST.name(),
                VERIFY_CREDENTIALS_URL, null);

        verify(oAuthHeaders).getOAuthEchoHeaders(authConfig, authToken, null,
                HttpMethod.POST.name(), VERIFY_CREDENTIALS_URL, null);
    }

    public void testGetOAuthEchoHeadersForVerifyCredentials() {
        authSigning.getOAuthEchoHeadersForVerifyCredentials();

        verify(oAuthHeaders).getOAuthEchoHeaders(authConfig, authToken, null, HttpMethod.GET.name(),
                OAuthSigning.VERIFY_CREDENTIALS_URL, null);
    }
}
