package com.digits.sdk.android;

/**
 * LoginCodeActionBarActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 * <p/>
 * Implements the login screen for digits
 */
public class LoginCodeActionBarActivity extends DigitsActionBarActivity {
    @Override
    DigitsActivityDelegate getActivityDelegate() {
        return new LoginCodeActivityDelegate();
    }
}
