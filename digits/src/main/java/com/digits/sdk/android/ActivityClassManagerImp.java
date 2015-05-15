package com.digits.sdk.android;

import android.app.Activity;

class ActivityClassManagerImp implements ActivityClassManager {
    @Override
    public Class<? extends Activity> getPhoneNumberActivity() {
        return PhoneNumberActivity.class;
    }

    @Override
    public Class<? extends Activity> getConfirmationActivity() {
        return ConfirmationCodeActivity.class;
    }

    @Override
    public Class<? extends Activity> getLoginCodeActivity() {
        return LoginCodeActivity.class;
    }

    @Override
    public Class<? extends Activity> getFailureActivity() {
        return FailureActivity.class;
    }

    @Override
    public Class<? extends Activity> getContactsActivity() {
        return ContactsActivity.class;
    }

    @Override
    public Class<? extends Activity> getPinCodeActivity() {
        return PinCodeActivity.class;
    }
}
