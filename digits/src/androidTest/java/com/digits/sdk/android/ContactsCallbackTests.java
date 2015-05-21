package com.digits.sdk.android;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import retrofit.RetrofitError;

public class ContactsCallbackTests extends DigitsAndroidTestCase {

    private StubContactsCallback contactsCallback;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        contactsCallback = new StubContactsCallback();
    }

    public void testFailure() throws Exception {
        contactsCallback.failure(RetrofitError.unexpectedError("", new NullPointerException("")));
        assertTrue(contactsCallback.isFailureCalled);
    }

    public void testSuccess() throws Exception {
        contactsCallback.success(null, null);
        assertTrue(contactsCallback.isSuccessCalled);
    }

    private class StubContactsCallback extends ContactsCallback<String> {
        boolean isSuccessCalled = false;
        boolean isFailureCalled = false;

        @Override
        public void success(Result<String> result) {
            isSuccessCalled = true;
        }

        @Override
        public void failure(TwitterException exception) {
            isFailureCalled = true;
        }
    }
}
