package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;

public class DigitsActivityDelegateImplTests extends DigitsActivityDelegateTests {
    @Override
    public DigitsActivityDelegateImpl getDelegate() {
        return new MockDigitsActivityDelegateImpl();
    }

    class MockDigitsActivityDelegateImpl extends DigitsActivityDelegateImpl {
        @Override
        public int getLayoutId() {
            return 0;
        }

        @Override
        public boolean isValid(Bundle bundle) {
            return false;
        }

        @Override
        public void init(Activity activity, Bundle bundle) {

        }

        @Override
        public void onResume() {

        }
    }
}
