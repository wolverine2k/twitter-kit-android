package com.digits.sdk.android;

/**
 * PinCodeActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 */
public class PinCodeActivity extends DigitsActivity {
    @Override
    DigitsActivityDelegate getActivityDelegate() {
        return new PinCodeActivityDelegate();
    }
}
