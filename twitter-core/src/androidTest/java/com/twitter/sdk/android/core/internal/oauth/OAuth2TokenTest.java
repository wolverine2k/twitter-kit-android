package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import java.util.Map;

public class OAuth2TokenTest extends TwitterAndroidTestCase {

    private static final int HEADERS_COUNT = 1;
    private static final String TOKEN_TYPE = "tokenType";
    private static final String ACCESS_TOKEN = "accessToken";

    public void testParcelable() {
        final OAuth2Token authToken = new OAuth2Token(TOKEN_TYPE, ACCESS_TOKEN);
        final Parcel parcel = Parcel.obtain();
        authToken.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final OAuth2Token parceledAuthToken
                = OAuth2Token.CREATOR.createFromParcel(parcel);
        assertEquals(authToken, parceledAuthToken);
    }

    public void testGetAuthHeaders() {
        final OAuth2Token token = new OAuth2Token(TOKEN_TYPE, ACCESS_TOKEN);
        final Map<String, String> headers = token.getAuthHeaders(null, null, null, null);
        assertEquals(HEADERS_COUNT, headers.size());
        assertEquals(OAuth2Service.getAuthorizationHeader(token),
                headers.get(GuestAuthToken.HEADER_AUTHORIZATION));
    }

    public void testIsExpired() {
        final OAuth2Token token = new OAuth2Token(TOKEN_TYPE, ACCESS_TOKEN);
        assertFalse(token.isExpired());
    }
}
