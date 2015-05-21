package com.digits.sdk.android;


public class PhoneNumberTest extends DigitsAndroidTestCase {
    public void testIsValid_emptyPhone() throws Exception {
        assertFalse(PhoneNumber.isValid(PhoneNumber.emptyPhone()));
    }

    public void testIsValid_nullPhone() throws Exception {
        assertFalse(PhoneNumber.isValid(null));
    }

    public void testIsValid_emptyMembers() throws Exception {
        PhoneNumber invalidPhoneNumber = new PhoneNumber("", US_ISO2, US_COUNTRY_CODE);
        assertFalse(PhoneNumber.isValid(invalidPhoneNumber));
        invalidPhoneNumber = new PhoneNumber(PHONE, "", US_COUNTRY_CODE);
        assertFalse(PhoneNumber.isValid(invalidPhoneNumber));
        invalidPhoneNumber = new PhoneNumber(PHONE, US_ISO2, "");
        assertFalse(PhoneNumber.isValid(invalidPhoneNumber));
    }

    public void testIsValid() throws Exception {
        final PhoneNumber validPhoneNumber = new PhoneNumber(PHONE, US_ISO2, US_COUNTRY_CODE);
        assertTrue(PhoneNumber.isValid(validPhoneNumber));
    }

    public void testIsCountryValid_emptyPhone() throws Exception {
        assertFalse(PhoneNumber.isCountryValid(PhoneNumber.emptyPhone()));
    }

    public void testIsCountryValid_nullPhone() throws Exception {
        assertFalse(PhoneNumber.isCountryValid(null));
    }

    public void testIsCountryValid_emptyMembers() throws Exception {
        PhoneNumber invalidPhoneNumber = new PhoneNumber("", "", US_COUNTRY_CODE);
        assertFalse(PhoneNumber.isCountryValid(invalidPhoneNumber));
        invalidPhoneNumber = new PhoneNumber("", US_ISO2, "");
        assertFalse(PhoneNumber.isCountryValid(invalidPhoneNumber));
    }

    public void testIsCountryValid() throws Exception {
        final PhoneNumber validPhoneNumber = new PhoneNumber("", US_ISO2, US_COUNTRY_CODE);
        assertTrue(PhoneNumber.isCountryValid(validPhoneNumber));
    }
}
