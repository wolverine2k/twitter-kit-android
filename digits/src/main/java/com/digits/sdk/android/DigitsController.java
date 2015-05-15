package com.digits.sdk.android;

import android.content.Context;
import android.os.ResultReceiver;
import android.text.TextWatcher;

/**
 * Interface that implements the logic business for
 * DigitsActivity
 */
interface DigitsController {
    void showTOS(Context context);

    void executeRequest(final Context context);

    boolean validateInput(CharSequence text);

    void onResume();

    void startFallback(Context context, ResultReceiver receiver, DigitsException reason);

    TextWatcher getTextWatcher();

    //TODO IC move this into a ErrorHandler
    ErrorCodes getErrors();

    int getErrorCount();

    void handleError(Context context, DigitsException digitsException);

    void clearError();

}
