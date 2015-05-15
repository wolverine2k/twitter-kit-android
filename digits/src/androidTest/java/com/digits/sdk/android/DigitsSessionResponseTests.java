package com.digits.sdk.android;

import java.lang.reflect.Field;

public class DigitsSessionResponseTests extends DigitsAndroidTestCase {
    public void testIsEmpty_true() throws Exception {
        final DigitsSessionResponse response = new DigitsSessionResponse();
        assertTrue(response.isEmpty());
    }

    public void testIsEmpty_false() throws Exception {
        for (Field field : DigitsSessionResponse.class.getFields()) {
            final DigitsSessionResponse response = new DigitsSessionResponse();
            if (field.getName().equals("userId")) {
                field.setLong(response, USER_ID);
            } else {
                field.set(response, field.getType().newInstance());
            }
            assertFalse(response.isEmpty());
        }
    }
}
