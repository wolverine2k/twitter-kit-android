package com.digits.sdk.android;


import android.app.Activity;
import android.support.annotation.StringRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

abstract class DigitsActivityDelegateImpl implements DigitsActivityDelegate {

    @Override
    public void onDestroy() {
        // no-op Not currently used by all delegates
    }

    public void setUpSendButton(final Activity activity, final DigitsController controller,
                                StateButton stateButton) {
        stateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.clearError();
                controller.executeRequest(activity);
            }
        });

    }

    public void setUpEditText(final Activity activity, final DigitsController controller,
                              EditText editText) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    controller.clearError();
                    controller.executeRequest(activity);
                    return true;
                }
                return false;
            }
        });
        editText.addTextChangedListener(controller.getTextWatcher());
    }

    public void setUpTermsText(final Activity activity, final DigitsController controller,
                               TextView termsText) {
        termsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.clearError();
                controller.showTOS(activity);
            }
        });
    }

    protected String getFormattedTerms(Activity activity, @StringRes int termsResId) {
        return activity.getString(termsResId, "\"");
    }
}
