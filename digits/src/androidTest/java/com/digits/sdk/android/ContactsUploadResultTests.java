package com.digits.sdk.android;

import android.os.Parcel;

public class ContactsUploadResultTests extends DigitsAndroidTestCase {
    public void testParcelable() {
        final ContactsUploadResult result = new ContactsUploadResult(1, 2);
        final Parcel parcel = Parcel.obtain();
        result.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final ContactsUploadResult parceledResult = ContactsUploadResult
                .CREATOR.createFromParcel(parcel);
        assertEquals(result.successCount, parceledResult.successCount);
        assertEquals(result.totalCount, parceledResult.totalCount);
    }
}
