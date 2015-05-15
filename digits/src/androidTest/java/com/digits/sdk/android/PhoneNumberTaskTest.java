package com.digits.sdk.android;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhoneNumberTaskTest extends DigitsAndroidTestCase {
    private PhoneNumberUtils phoneNumberUtils;
    private PhoneNumber phoneNumber;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        phoneNumberUtils = mock(PhoneNumberUtilsTest.DummyPhoneNumberUtils.class);
        phoneNumber = new PhoneNumber(PHONE, US_ISO2, US_COUNTRY_CODE);
        when(phoneNumberUtils.getPhoneNumber()).thenReturn(phoneNumber);
    }

    public void testExecute() throws Exception {

        new PhoneNumberTask(phoneNumberUtils, new PhoneNumberTask.Listener() {
            @Override
            public void onLoadComplete(PhoneNumber result) {
                assertEquals(phoneNumber, result);
                verify(phoneNumberUtils).getPhoneNumber();
            }
        }).execute();
    }

    public void testConstructor_nullPhoneNumberManager() throws Exception {
        try {
            new PhoneNumberTask(null, mock(PhoneNumberTask.Listener.class));
        } catch (NullPointerException ex) {
            assertEquals("phoneNumberManager can't be null", ex.getMessage());
        }
    }

    public void testConstructor_nullListener() throws Exception {
        new PhoneNumberTask(phoneNumberUtils, null);
    }
}
