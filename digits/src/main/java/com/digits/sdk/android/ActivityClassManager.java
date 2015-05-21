package com.digits.sdk.android;

import android.app.Activity;


public interface ActivityClassManager {

    Class<? extends Activity> getPhoneNumberActivity();

    Class<? extends Activity> getConfirmationActivity();

    Class<? extends Activity> getLoginCodeActivity();

    Class<? extends Activity> getFailureActivity();

    Class<? extends Activity> getContactsActivity();

    Class<? extends Activity> getPinCodeActivity();
}
