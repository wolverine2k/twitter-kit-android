package com.digits.sdk.android;

import android.content.Context;
import android.net.Uri;
import android.os.ResultReceiver;
import android.widget.EditText;

import io.fabric.sdk.android.services.common.CommonUtils;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.SessionManager;

class PinCodeController extends DigitsControllerImpl {
    private final String requestId;
    private final long userId;
    private final String phoneNumber;

    PinCodeController(ResultReceiver resultReceiver, StateButton stateButton,
                      EditText phoneEditText, String requestId, long userId,
                      String phoneNumber) {
        this(resultReceiver, stateButton, phoneEditText, Digits.getSessionManager(),
                Digits.getInstance().getDigitsClient(), requestId, userId, phoneNumber,
                new ConfirmationErrorCodes(stateButton.getContext().getResources()),
                Digits.getInstance().getActivityClassManager());
    }

    PinCodeController(ResultReceiver resultReceiver, StateButton stateButton,
                      EditText phoneEditText, SessionManager<DigitsSession>
            sessionManager, DigitsClient digitsClient, String requestId, long userId,
                      String phoneNumber, ErrorCodes errors,
                      ActivityClassManager activityClassManager) {
        super(resultReceiver, stateButton, phoneEditText, digitsClient, errors,
                activityClassManager, sessionManager);
        this.requestId = requestId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void showTOS(Context context) {
        //nothing to do
    }

    @Override
    Uri getTOSUri() {
        return null;
    }

    @Override
    public void executeRequest(final Context context) {
        if (validateInput(editText.getText())) {
            sendButton.showProgress();
            CommonUtils.hideKeyboard(context, editText);
            final String code = editText.getText().toString();
            digitsClient.verifyPin(requestId, userId, code,
                    new DigitsCallback<DigitsSessionResponse>(context, this) {
                        @Override
                        public void success(Result<DigitsSessionResponse> result) {
                            final DigitsSession session = DigitsSession.create(result.data);
                            loginSuccess(context, session, phoneNumber);
                        }
                    });
        }
    }
}
