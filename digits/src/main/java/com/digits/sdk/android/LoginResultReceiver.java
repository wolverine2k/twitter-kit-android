package com.digits.sdk.android;

import android.os.Bundle;
import android.os.ResultReceiver;

import com.twitter.sdk.android.core.SessionManager;


class LoginResultReceiver extends ResultReceiver {

    static final int RESULT_OK = 200;
    static final int RESULT_ERROR = 400;
    static final String KEY_ERROR = "login_error";

    final AuthCallback callback;
    final SessionManager<DigitsSession> sessionManager;

    LoginResultReceiver(AuthCallback callback,
                        SessionManager<DigitsSession> sessionManager) {
        super(null);
        this.callback = callback;
        this.sessionManager = sessionManager;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if (callback != null) {
            if (resultCode == RESULT_OK) {
                callback.success(sessionManager.getActiveSession(),
                        resultData.getString(DigitsClient.EXTRA_PHONE));
            } else if (resultCode == RESULT_ERROR) {
                callback.failure(new DigitsException(resultData.getString(KEY_ERROR)));
            }
        }
    }
}
