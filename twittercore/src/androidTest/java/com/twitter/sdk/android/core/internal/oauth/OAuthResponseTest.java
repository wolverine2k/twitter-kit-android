package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthToken;

public class OAuthResponseTest extends TwitterAndroidTestCase {

    public void testParcelable() {
        final TwitterAuthToken authToken = new TwitterAuthToken(TOKEN, SECRET);
        final OAuthResponse authResponse = new OAuthResponse(authToken, USER, USER_ID);
        final Parcel parcel = Parcel.obtain();
        authResponse.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final OAuthResponse parceledAuthResponse
                = OAuthResponse.CREATOR.createFromParcel(parcel);
        assertEquals(authResponse.authToken, parceledAuthResponse.authToken);
        assertEquals(USER, parceledAuthResponse.userName);
        assertEquals(USER_ID, parceledAuthResponse.userId);
    }
}
