package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.fabric.sdk.android.services.common.CommonUtils;

class FailureActivityDelegateImpl implements FailureActivityDelegate {
    final Activity activity;
    final FailureController controller;

    public FailureActivityDelegateImpl(Activity activity) {
        this(activity, new FailureControllerImpl());
    }

    public FailureActivityDelegateImpl(Activity activity, FailureController controller) {
        this.activity = activity;
        this.controller = controller;
    }

    public void init() {
        final Bundle bundle = activity.getIntent().getExtras();
        if (isBundleValid(bundle)) {
            setContentView();
            setUpViews();
        } else {
            throw new IllegalAccessError("This activity can only be started from Digits");
        }
    }

    protected boolean isBundleValid(Bundle bundle) {
        return BundleManager.assertContains(bundle, DigitsClient.EXTRA_RESULT_RECEIVER);
    }

    protected void setContentView() {
        activity.setContentView(R.layout.dgts__activity_failure);
    }

    protected void setUpViews() {
        final Button dismissButton = (Button) activity.findViewById(R.id.dgts__dismiss_button);
        final TextView tryAnotherNumberButton = (TextView) activity.findViewById(R.id
                .dgts__try_another_phone);

        setUpDismissButton(dismissButton);
        setUpTryAnotherPhoneButton(tryAnotherNumberButton);
    }

    protected void setUpDismissButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.finishAffinity(activity, DigitsActivity.RESULT_FINISH_DIGITS);
                controller.sendFailure(getBundleResultReceiver(), getBundleException());
            }
        });
    }

    protected void setUpTryAnotherPhoneButton(TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.tryAnotherNumber(activity, getBundleResultReceiver());
                activity.finish();
            }
        });
    }

    private ResultReceiver getBundleResultReceiver() {
        final Bundle bundle = activity.getIntent().getExtras();
        return bundle.getParcelable(DigitsClient.EXTRA_RESULT_RECEIVER);
    }

    private DigitsException getBundleException() {
        final Bundle bundle = activity.getIntent().getExtras();
        return (DigitsException) bundle.getSerializable(DigitsClient.EXTRA_FALLBACK_REASON);
    }
}
