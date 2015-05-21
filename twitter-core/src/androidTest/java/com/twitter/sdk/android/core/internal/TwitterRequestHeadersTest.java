package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.AuthToken;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class TwitterRequestHeadersTest extends TwitterAndroidTestCase {

    private static final String GET = "GET";
    private static final String TEST_URL = "http://testurl";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String GUEST_TOKEN = "guestToken";

    private final TwitterAuthConfig authConfig = new TwitterAuthConfig("consumerKey",
            "consumerSecret");
    private final TwitterAuthToken authToken = new TwitterAuthToken("token", "secret");
    private final TwitterSession session = new TwitterSession(authToken, USER_ID, USER);
    private final String userAgent = "TwitterRequestHeadersTest";

    public void testGetHeaders() {
        final TwitterRequestHeaders requestHeaders = new TwitterRequestHeaders(GET,
                TEST_URL, authConfig, session, userAgent, null);
        assertMinimumHeaders(requestHeaders.getHeaders());
    }

    private void assertMinimumHeaders(Map<String, String> headers) {
        assertNotNull(headers);
        assertNotNull(headers.get(AuthToken.HEADER_AUTHORIZATION));
        assertEquals(userAgent, headers.get(TwitterRequestHeaders.HEADER_USER_AGENT));
    }

    public void testGetHeaders_appAuthToken() {
        final AppSession sessionAppAuthToken = mock(AppSession.class);
         when(sessionAppAuthToken.getAuthToken()).thenReturn(new OAuth2Token(null, ACCESS_TOKEN));

        final TwitterRequestHeaders requestHeaders = new TwitterRequestHeaders(GET,
                TEST_URL, authConfig, sessionAppAuthToken, userAgent, null);
        final Map<String, String> headers = requestHeaders.getHeaders();
        assertMinimumHeaders(headers);
        assertEquals(OAuth2Service.getAuthorizationHeader(sessionAppAuthToken.getAuthToken()),
                headers.get(OAuth2Token.HEADER_AUTHORIZATION));
    }

    public void testGetHeaders_guestAuthToken() {
        final AppSession sessionGuestAuthToken = mock(AppSession.class);
        when(sessionGuestAuthToken.getAuthToken())
                .thenReturn(new GuestAuthToken(null, ACCESS_TOKEN, GUEST_TOKEN));
        final TwitterRequestHeaders requestHeaders = new TwitterRequestHeaders(GET,
                TEST_URL, authConfig, sessionGuestAuthToken, userAgent, null);
        final Map<String, String> headers = requestHeaders.getHeaders();
        assertMinimumHeaders(headers);
        assertEquals(OAuth2Service.getAuthorizationHeader(sessionGuestAuthToken.getAuthToken()),
                headers.get(GuestAuthToken.HEADER_AUTHORIZATION)
        );
        assertEquals(GUEST_TOKEN, headers.get(GuestAuthToken.HEADER_GUEST_TOKEN));
    }

    public void testGetHeaders_withNullSession()  {
        final TwitterRequestHeaders requestHeaders = new TwitterRequestHeaders(GET,
                TEST_URL, authConfig, null, userAgent, null);
        final Map<String, String> headerMap = requestHeaders.getHeaders();
        assertHeadersWhenNoAuth(headerMap);
    }

    private void assertHeadersWhenNoAuth(Map<String, String> headers) {
        assertEquals(1, headers.size());
        assertEquals(userAgent, headers.get(TwitterRequestHeaders.HEADER_USER_AGENT));
    }

    public void testGetHeaders_extraHeaders() {
        final String extraHeader = "Extra header";
        final String extraHeaderValue = "Extra header value";
        final TwitterRequestHeaders requestHeaders = new TwitterRequestHeaders(GET,
                TEST_URL, authConfig, session, userAgent, null) {
            @Override
            protected Map<String, String> getExtraHeaders() {
                final Map<String, String> extras = new HashMap<String, String>(1);
                extras.put(extraHeader, extraHeaderValue);
                return extras;
            }
        };
        final Map<String, String> headerMap = requestHeaders.getHeaders();
        assertMinimumHeaders(headerMap);
        assertTrue(headerMap.containsKey(extraHeader));
        assertEquals(extraHeaderValue, headerMap.get(extraHeader));
    }
}
