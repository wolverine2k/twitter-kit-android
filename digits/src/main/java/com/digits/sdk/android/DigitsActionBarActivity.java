package com.digits.sdk.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

// TODO(EF) Cannot test due to Android bug
// See http://stackoverflow.com/questions/24760354/namenotfoundexception-at-activityunittestcase-with-actionbaractivity
public abstract class DigitsActionBarActivity extends ActionBarActivity {

    static final int RESULT_FINISH_DIGITS = 200;
    static final int REQUEST_CODE = 140;

    DigitsActivityDelegate delegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(Digits.getInstance().getTheme());
        super.onCreate(savedInstanceState);

        delegate = getActivityDelegate();
        final Bundle bundle = getIntent().getExtras();
        if (delegate.isValid(bundle)) {
            setContentView(delegate.getLayoutId());
            delegate.init(this, bundle);
        } else {
            finish();
            throw new IllegalAccessError("This activity can only be started from Digits");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        delegate.onResume();
    }

    @Override
    public void onDestroy(){
        delegate.onDestroy();
        super.onDestroy();
    }

    abstract DigitsActivityDelegate getActivityDelegate();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_FINISH_DIGITS && requestCode == REQUEST_CODE) {
            finish();
        }
    }
}
