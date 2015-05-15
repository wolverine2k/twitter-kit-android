package com.digits.sdk.android;

import junit.framework.TestCase;

public class AppCompatActivityClassManagerTests extends TestCase {
    private AppCompatClassManagerImp activityClassManager;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activityClassManager = new AppCompatClassManagerImp();
    }

    public void testGetPhoneNumberActivity() throws Exception {
        assertEquals(PhoneNumberActionBarActivity.class,
                activityClassManager.getPhoneNumberActivity());
    }

    public void testGetConfirmationActivity() throws Exception {
        assertEquals(ConfirmationCodeActionBarActivity.class,
                activityClassManager.getConfirmationActivity());
    }

    public void testGetLoginCodeActivity() throws Exception {
        assertEquals(LoginCodeActionBarActivity.class, activityClassManager.getLoginCodeActivity());
    }

    public void testFailureActivity() throws Exception {
        assertEquals(FailureActionBarActivity.class,
                activityClassManager.getFailureActivity());
    }

    public void testContactsActivity() throws Exception {
        assertEquals(ContactsActionBarActivity.class, activityClassManager.getContactsActivity());
    }

    public void testPinCodeActivity() throws Exception {
        assertEquals(PinCodeActionBarActivity.class,
                activityClassManager.getPinCodeActivity());
    }
}
