package com.digits.sdk.android;

/**
 * LoginCodeActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 * <p/>
 * Implements the login screen for digits
 */
public class LoginCodeActivity extends DigitsActivity {
    @Override
    DigitsActivityDelegate getActivityDelegate() {
        return new LoginCodeActivityDelegate();
    }
}
