package com.digits.sdk.android;

/**
 * ConfirmationCodeActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 */
public class ConfirmationCodeActivity extends DigitsActivity {
    @Override
    DigitsActivityDelegate getActivityDelegate() {
        return new ConfirmationCodeActivityDelegate();
    }
}
