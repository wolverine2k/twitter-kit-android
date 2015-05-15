package com.digits.sdk.android;

/**
 * PhoneNumberActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 */

public class PhoneNumberActivity extends DigitsActivity {
    @Override
    DigitsActivityDelegate getActivityDelegate() {
        return new PhoneNumberActivityDelegate();
    }
}
