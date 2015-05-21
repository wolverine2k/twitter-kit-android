package com.digits.sdk.android;

import junit.framework.TestCase;

public class ActivityClassManagerImpTests extends TestCase {
    private ActivityClassManagerImp activityClassManager;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activityClassManager = new ActivityClassManagerImp();
    }

    public void testGetPhoneNumberActivity() throws Exception {
        assertEquals(PhoneNumberActivity.class, activityClassManager.getPhoneNumberActivity());
    }

    public void testGetConfirmationActivity() throws Exception {
        assertEquals(ConfirmationCodeActivity.class,
                activityClassManager.getConfirmationActivity());
    }

    public void testGetLoginCodeActivity() throws Exception {
        assertEquals(LoginCodeActivity.class, activityClassManager.getLoginCodeActivity());
    }

    public void testFailureActivity() throws Exception {
        assertEquals(FailureActivity.class, activityClassManager.getFailureActivity());
    }

    public void testContactsActivity() throws Exception {
        assertEquals(ContactsActivity.class, activityClassManager.getContactsActivity());
    }

    public void testPinCodeActivity() throws Exception {
        assertEquals(PinCodeActivity.class, activityClassManager.getPinCodeActivity());
    }
}
