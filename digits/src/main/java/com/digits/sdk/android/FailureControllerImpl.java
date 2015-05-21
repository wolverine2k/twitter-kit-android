package com.digits.sdk.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;

class FailureControllerImpl implements FailureController {
    final ActivityClassManager classManager;

    public FailureControllerImpl() {
        this(Digits.getInstance().getActivityClassManager());
    }

    public FailureControllerImpl(ActivityClassManager classManager) {
        this.classManager = classManager;
    }

    public void tryAnotherNumber(Activity activity, ResultReceiver resultReceiver) {
        final Intent intent = new Intent(activity, classManager.getPhoneNumberActivity());
        final Bundle bundle = new Bundle();
        bundle.putParcelable(DigitsClient.EXTRA_RESULT_RECEIVER, resultReceiver);
        intent.putExtras(bundle);
        intent.setFlags(getFlags());
        activity.startActivity(intent);
    }

    public void sendFailure(ResultReceiver resultReceiver, Exception exception) {
        final Bundle bundle = new Bundle();
        bundle.putString(LoginResultReceiver.KEY_ERROR, exception.getLocalizedMessage());
        resultReceiver.send(LoginResultReceiver.RESULT_ERROR, bundle);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    int getFlags() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        } else {
            return Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK;
        }
    }
}
