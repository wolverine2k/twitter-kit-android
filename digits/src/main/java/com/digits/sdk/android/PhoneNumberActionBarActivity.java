package com.digits.sdk.android;

/**
 * PhoneNumberActionBarActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 */

public class PhoneNumberActionBarActivity extends DigitsActionBarActivity {
    @Override
    DigitsActivityDelegate getActivityDelegate() {
        return new PhoneNumberActivityDelegate();
    }
}
