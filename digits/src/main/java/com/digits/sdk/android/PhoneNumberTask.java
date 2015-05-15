package com.digits.sdk.android;

import io.fabric.sdk.android.services.concurrency.AsyncTask;

class PhoneNumberTask extends AsyncTask<Void, Void, PhoneNumber> {
    private final Listener listener;
    private final PhoneNumberUtils phoneNumberUtils;

    protected PhoneNumberTask(PhoneNumberUtils phoneNumberUtils, Listener listener) {
        if (phoneNumberUtils == null) {
            throw new NullPointerException("phoneNumberManager can't be null");
        }
        this.listener = listener;
        this.phoneNumberUtils = phoneNumberUtils;
    }

    @Override
    protected PhoneNumber doInBackground(Void... params) {
        return phoneNumberUtils.getPhoneNumber();
    }


    @Override
    protected void onPostExecute(PhoneNumber phoneNumber) {
        if (listener != null) {
            listener.onLoadComplete(phoneNumber);
        }
    }

    interface Listener {
        void onLoadComplete(PhoneNumber result);
    }
}
