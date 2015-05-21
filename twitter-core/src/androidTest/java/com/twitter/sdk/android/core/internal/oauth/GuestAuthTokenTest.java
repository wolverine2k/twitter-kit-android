package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import java.util.Map;

public class GuestAuthTokenTest extends TwitterAndroidTestCase {

    private static final int HEADERS_COUNT = 2;
    private static final String TOKEN_TYPE = "tokenType";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String GUEST_TOKEN = "guestToken";
    private static final long ONE_HOUR_AGE = System.currentTimeMillis() - (3600 * 1000);
    private static final long THREE_HOURS_AGO = System.currentTimeMillis() - (3600 * 3 * 1000);

    public void testGetAuthHeaders() {
        final GuestAuthToken token = new GuestAuthToken(TOKEN_TYPE, ACCESS_TOKEN, GUEST_TOKEN);
        final Map<String, String> headers = token.getAuthHeaders(null, null, null, null);
        assertEquals(HEADERS_COUNT, headers.size());
        assertEquals(OAuth2Service.getAuthorizationHeader(token),
                headers.get(GuestAuthToken.HEADER_AUTHORIZATION));
        assertEquals(GUEST_TOKEN, headers.get(GuestAuthToken.HEADER_GUEST_TOKEN));
    }

    public void testIsExpired_newToken() {
        final GuestAuthToken token = new GuestAuthToken(TOKEN_TYPE, ACCESS_TOKEN, GUEST_TOKEN);
        assertFalse(token.isExpired());
    }

    public void testIsExpired_oneHourOld() {
        final GuestAuthToken token = new GuestAuthToken(TOKEN_TYPE, ACCESS_TOKEN, GUEST_TOKEN,
                ONE_HOUR_AGE);
        assertFalse(token.isExpired());
    }

    public void testIsExpired_threeHoursOld() {
        final GuestAuthToken token = new GuestAuthToken(TOKEN_TYPE, ACCESS_TOKEN, GUEST_TOKEN,
                THREE_HOURS_AGO);
        assertTrue(token.isExpired());
    }

    public void testIsExpired_createdAtZero() {
        final GuestAuthToken token = new GuestAuthToken(TOKEN_TYPE, ACCESS_TOKEN, GUEST_TOKEN, 0);
        assertTrue(token.isExpired());
    }
}
