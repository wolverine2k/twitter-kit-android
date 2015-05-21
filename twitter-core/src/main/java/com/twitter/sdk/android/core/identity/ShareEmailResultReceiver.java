package com.twitter.sdk.android.core.identity;

import android.os.Bundle;
import android.os.ResultReceiver;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

class ShareEmailResultReceiver extends ResultReceiver {

    private final Callback<String> callback;

    public ShareEmailResultReceiver(Callback<String> callback) {
        super(null);

        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        this.callback = callback;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case ShareEmailClient.RESULT_CODE_OK: {
                callback.success(new Result<String>(
                        resultData.getString(ShareEmailClient.RESULT_DATA_EMAIL), null));
                break;
            }
            case ShareEmailClient.RESULT_CODE_CANCELED: {
                callback.failure(new TwitterException(resultData.getString(
                        ShareEmailClient.RESULT_DATA_MSG)));
                break;
            }
            case ShareEmailClient.RESULT_CODE_ERROR: {
                callback.failure((TwitterException) resultData.getSerializable(
                        ShareEmailClient.RESULT_DATA_ERROR));
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid result code " + resultCode);
            }
        }
    }
}
