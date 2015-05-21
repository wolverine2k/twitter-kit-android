package com.digits.sdk.android;

import android.app.Activity;

class AppCompatClassManagerImp implements ActivityClassManager {
    @Override
    public Class<? extends Activity> getPhoneNumberActivity() {
        return PhoneNumberActionBarActivity.class;
    }

    @Override
    public Class<? extends Activity> getConfirmationActivity() {
        return ConfirmationCodeActionBarActivity.class;
    }

    @Override
    public Class<? extends Activity> getLoginCodeActivity() {
        return LoginCodeActionBarActivity.class;
    }

    @Override
    public Class<? extends Activity> getFailureActivity() {
        return FailureActionBarActivity.class;
    }

    @Override
    public Class<? extends Activity> getContactsActivity() {
        return ContactsActionBarActivity.class;
    }

    @Override
    public Class<? extends Activity> getPinCodeActivity() {
        return PinCodeActionBarActivity.class;
    }
}
