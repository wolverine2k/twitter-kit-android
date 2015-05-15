package com.digits.sdk.android;

/**
 * ConfirmationCodeActionBarActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 */
public class ConfirmationCodeActionBarActivity extends DigitsActionBarActivity {
    @Override
    DigitsActivityDelegate getActivityDelegate() {
        return new ConfirmationCodeActivityDelegate();
    }
}
