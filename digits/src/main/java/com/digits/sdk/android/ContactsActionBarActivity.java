package com.digits.sdk.android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ContactsActionBarActivity extends ActionBarActivity {

    ContactsActivityDelegateImpl delegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(getIntent().getIntExtra(ThemeUtils.THEME_RESOURCE_ID,
                R.style.Theme_AppCompat_Light));
        super.onCreate(savedInstanceState);

        delegate = new ContactsActivityDelegateImpl(this);
        delegate.init();
    }
}
