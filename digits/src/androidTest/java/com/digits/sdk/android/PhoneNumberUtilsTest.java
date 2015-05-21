package com.digits.sdk.android;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhoneNumberUtilsTest extends DigitsAndroidTestCase {
    private static final String INVENTED_ISO = "random";
    private SimManager simManager;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        simManager = mock(SimManagerTest.DummySimManager.class);
    }

    public void testGetPhoneNumber_nullSim() throws Exception {
        final DummyPhoneNumberUtils DummyPhoneNumberUtils = new DummyPhoneNumberUtils(null);
        assertEquals(PhoneNumber.emptyPhone(), DummyPhoneNumberUtils.getPhoneNumber());
    }

    public void testGetPhoneNumber() throws Exception {
        when(simManager.getCountryIso()).thenReturn(US_ISO2);
        when(simManager.getRawPhoneNumber()).thenReturn(RAW_PHONE);
        final DummyPhoneNumberUtils DummyPhoneNumberUtils = new DummyPhoneNumberUtils(simManager);
        final PhoneNumber number = DummyPhoneNumberUtils.getPhoneNumber();
        verify(simManager).getCountryIso();
        verify(simManager).getRawPhoneNumber();
        assertEquals(PHONE_NO_COUNTRY_CODE, number.getPhoneNumber());
        assertEquals(US_COUNTRY_CODE, number.getCountryCode());
        assertEquals(US_ISO2, number.getCountryIso());
    }

    public void testGetPhoneNumber_noCountryCode() throws Exception {
        when(simManager.getCountryIso()).thenReturn(US_ISO2);
        when(simManager.getRawPhoneNumber()).thenReturn(PHONE_NO_COUNTRY_CODE);
        final DummyPhoneNumberUtils DummyPhoneNumberUtils = new DummyPhoneNumberUtils(simManager);
        final PhoneNumber number = DummyPhoneNumberUtils.getPhoneNumber();
        verify(simManager).getCountryIso();
        verify(simManager).getRawPhoneNumber();
        assertEquals(PHONE_NO_COUNTRY_CODE, number.getPhoneNumber());
        assertEquals(US_COUNTRY_CODE, number.getCountryCode());
        assertEquals(US_ISO2, number.getCountryIso());
    }

    public void testGetPhoneNumber_noPlusSymbol() throws Exception {
        when(simManager.getCountryIso()).thenReturn(US_ISO2);
        when(simManager.getRawPhoneNumber()).thenReturn(PHONE);
        final DummyPhoneNumberUtils DummyPhoneNumberUtils = new DummyPhoneNumberUtils(simManager);
        final PhoneNumber number = DummyPhoneNumberUtils.getPhoneNumber();
        verify(simManager).getCountryIso();
        verify(simManager).getRawPhoneNumber();
        assertEquals(PHONE_NO_COUNTRY_CODE, number.getPhoneNumber());
        assertEquals(US_COUNTRY_CODE, number.getCountryCode());
        assertEquals(US_ISO2, number.getCountryIso());
    }

    public void testGetPhoneNumber_plusSymbolNoCountryCode() throws Exception {
        when(simManager.getCountryIso()).thenReturn(US_ISO2);
        when(simManager.getRawPhoneNumber()).thenReturn(PHONE_PLUS_SYMBOL_NO_COUNTRY_CODE);
        final DummyPhoneNumberUtils DummyPhoneNumberUtils = new DummyPhoneNumberUtils(simManager);
        final PhoneNumber number = DummyPhoneNumberUtils.getPhoneNumber();
        verify(simManager).getCountryIso();
        verify(simManager).getRawPhoneNumber();
        assertEquals(PHONE_NO_COUNTRY_CODE, number.getPhoneNumber());
        assertEquals(US_COUNTRY_CODE, number.getCountryCode());
        assertEquals(US_ISO2, number.getCountryIso());
    }

    public void testGetPhoneNumber_nonMatchingISO() throws Exception {
        when(simManager.getCountryIso()).thenReturn(INVENTED_ISO);
        when(simManager.getRawPhoneNumber()).thenReturn(RAW_PHONE);
        final DummyPhoneNumberUtils DummyPhoneNumberUtils = new DummyPhoneNumberUtils(simManager);
        final PhoneNumber number = DummyPhoneNumberUtils.getPhoneNumber();
        verify(simManager).getCountryIso();
        verify(simManager).getRawPhoneNumber();
        assertEquals(PHONE, number.getPhoneNumber());
        assertEquals("", number.getCountryCode());
        assertEquals(INVENTED_ISO, number.getCountryIso());
    }

    public class DummyPhoneNumberUtils extends PhoneNumberUtils{


        DummyPhoneNumberUtils(SimManager simManager) {
            super(simManager);
        }
    }
}
