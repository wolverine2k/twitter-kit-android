package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;

public class ContactsActivity extends Activity {

    ContactsActivityDelegateImpl delegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(getIntent().getIntExtra(ThemeUtils.THEME_RESOURCE_ID, R.style.Digits_default));
        super.onCreate(savedInstanceState);

        delegate = new ContactsActivityDelegateImpl(this);
        delegate.init();
    }
}
