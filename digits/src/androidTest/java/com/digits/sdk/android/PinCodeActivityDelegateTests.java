package com.digits.sdk.android;

import android.os.Bundle;
import android.os.ResultReceiver;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class PinCodeActivityDelegateTests extends
        DigitsActivityDelegateTests<PinCodeActivityDelegate> {

    @Override
    public PinCodeActivityDelegate getDelegate() {
        return spy(new DummyPinCodeActivityDelegate());
    }

    public void testIsValid() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(DigitsClient.EXTRA_RESULT_RECEIVER, new ResultReceiver(null));
        bundle.putString(DigitsClient.EXTRA_PHONE, "");
        bundle.putString(DigitsClient.EXTRA_REQUEST_ID, "");
        bundle.putString(DigitsClient.EXTRA_USER_ID, "");

        assertTrue(delegate.isValid(bundle));
    }

    public void testIsValid_missingResultReceiver() {
        final Bundle bundle = new Bundle();
        bundle.putString(DigitsClient.EXTRA_PHONE, "");
        bundle.putString(DigitsClient.EXTRA_REQUEST_ID, "");
        bundle.putString(DigitsClient.EXTRA_USER_ID, "");

        assertFalse(delegate.isValid(bundle));
    }

    public void testIsValid_missingPhoneNumber() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(DigitsClient.EXTRA_RESULT_RECEIVER, new ResultReceiver(null));
        bundle.putString(DigitsClient.EXTRA_REQUEST_ID, "");
        bundle.putString(DigitsClient.EXTRA_USER_ID, "");

        assertFalse(delegate.isValid(bundle));
    }

    public void testIsValid_missingRequestId() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(DigitsClient.EXTRA_RESULT_RECEIVER, new ResultReceiver(null));
        bundle.putString(DigitsClient.EXTRA_PHONE, "");
        bundle.putString(DigitsClient.EXTRA_USER_ID, "");

        assertFalse(delegate.isValid(bundle));
    }

    public void testIsValid_missingUserId() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(DigitsClient.EXTRA_RESULT_RECEIVER, new ResultReceiver(null));
        bundle.putString(DigitsClient.EXTRA_PHONE, "");
        bundle.putString(DigitsClient.EXTRA_REQUEST_ID, "");

        assertFalse(delegate.isValid(bundle));
    }

    public void testGetLayoutId() {
        assertEquals(R.layout.dgts__activity_pin_code, delegate.getLayoutId());
    }

    public void testOnResume() {
        delegate.controller = controller;
        delegate.onResume();
        verify(controller).onResume();
    }

    public class DummyPinCodeActivityDelegate extends PinCodeActivityDelegate {

    }
}
