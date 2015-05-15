package com.twitter.sdk.android.core;

import android.os.Parcel;

public class TwitterAuthTokenTest extends TwitterAndroidTestCase {

    public void testParcelable() {
        final TwitterAuthToken authToken = new TwitterAuthToken(TOKEN, SECRET);
        final Parcel parcel = Parcel.obtain();
        authToken.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final TwitterAuthToken parceledAuthToken
                = TwitterAuthToken.CREATOR.createFromParcel(parcel);
        assertEquals(authToken, parceledAuthToken);
    }

    public void testIsExpired() {
        final TwitterAuthToken token = new TwitterAuthToken(TOKEN, SECRET);
        assertFalse(token.isExpired());
    }
}
