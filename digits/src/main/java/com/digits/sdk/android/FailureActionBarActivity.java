package com.digits.sdk.android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * FailureActionBarActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 */
public class FailureActionBarActivity extends ActionBarActivity {
    FailureActivityDelegateImpl delegate;

    public void onCreate(Bundle savedInstanceState) {
        setTheme(Digits.getInstance().getTheme());
        super.onCreate(savedInstanceState);

        delegate = new FailureActivityDelegateImpl(this);
        delegate.init();
    }
}
