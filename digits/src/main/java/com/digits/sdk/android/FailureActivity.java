package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;

/**
 * FailureActivity should never be started from outside digits otherwise
 * will throw an IllegalAccessError
 */
public class FailureActivity extends Activity {
    FailureActivityDelegateImpl delegate;

    public void onCreate(Bundle savedInstanceState) {
        setTheme(Digits.getInstance().getTheme());
        super.onCreate(savedInstanceState);

        delegate = new FailureActivityDelegateImpl(this);
        delegate.init();
    }
}
