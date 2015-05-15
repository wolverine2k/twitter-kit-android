package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.fabric.sdk.android.services.common.CommonUtils;

class PhoneNumberActivityDelegate extends DigitsActivityDelegateImpl implements
        PhoneNumberTask.Listener {
    CountryListSpinner countryCodeSpinner;
    StateButton sendButton;
    EditText phoneEditText;
    TextView termsTextView;
    PhoneNumberController controller;

    @Override
    public int getLayoutId() {
        return R.layout.dgts__activity_phone_number;
    }

    @Override
    public boolean isValid(Bundle bundle) {
        return BundleManager.assertContains(bundle, DigitsClient.EXTRA_RESULT_RECEIVER);
    }

    //TODO IC: add test
    @Override
    public void init(Activity activity, Bundle bundle) {

        countryCodeSpinner = (CountryListSpinner) activity.findViewById(R.id.dgts__countryCode);
        sendButton = (StateButton) activity.findViewById(R.id.dgts__sendCodeButton);
        phoneEditText = (EditText) activity.findViewById(R.id.dgts__phoneNumberEditText);
        termsTextView = (TextView) activity.findViewById(R.id.dgts__termsText);

        controller = initController(bundle);

        setUpEditText(activity, controller, phoneEditText);

        setUpSendButton(activity, controller, sendButton);

        setUpTermsText(activity, controller, termsTextView);

        setUpCountrySpinner(countryCodeSpinner);

        executePhoneNumberTask(new PhoneNumberUtils(SimManager.createSimManager(activity)));

        CommonUtils.openKeyboard(activity, phoneEditText);
    }

    private void executePhoneNumberTask(PhoneNumberUtils phoneNumberUtils) {
        new PhoneNumberTask(phoneNumberUtils, this).executeOnExecutor(Digits.getInstance()
                .getExecutorService());
    }

    PhoneNumberController initController(Bundle bundle) {
        return new PhoneNumberController(bundle
                .<ResultReceiver>getParcelable(DigitsClient.EXTRA_RESULT_RECEIVER), sendButton,
                phoneEditText, countryCodeSpinner);
    }

    @Override
    public void setUpTermsText(Activity activity, DigitsController controller, TextView termsText) {
        termsText.setText(getFormattedTerms(activity, R.string.dgts__terms_text));
        super.setUpTermsText(activity, controller, termsText);
    }

    protected void setUpCountrySpinner(CountryListSpinner countryCodeSpinner) {
        countryCodeSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.clearError();
            }
        });
    }

    @Override
    public void onResume() {
        controller.onResume();
    }

    public void onLoadComplete(PhoneNumber phoneNumber) {
        controller.setPhoneNumber(phoneNumber);
        controller.setCountryCode(phoneNumber);
    }
}
