package com.digits.sdk.android;

import android.app.Activity;
import android.os.ResultReceiver;

interface FailureController {
    void tryAnotherNumber(Activity activity, ResultReceiver resultReceiver);
    void sendFailure(ResultReceiver resultReceiver, Exception exception);
}
