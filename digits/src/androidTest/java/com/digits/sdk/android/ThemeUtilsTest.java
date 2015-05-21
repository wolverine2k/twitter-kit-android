package com.digits.sdk.android;

import android.graphics.Color;
import android.test.AndroidTestCase;

public class ThemeUtilsTest  extends AndroidTestCase {

    public void testIsLightColor_blue() {
        assertFalse(ThemeUtils.isLightColor(Color.BLUE));
    }

    public void testIsLightColor_black() {
        assertFalse(ThemeUtils.isLightColor(Color.BLACK));
    }

    public void testIsLightColor_white() {
        assertTrue(ThemeUtils.isLightColor(Color.WHITE));
    }

    public void testCalculateOpacityTransform_zeroOpacity() {
        assertEquals(Color.WHITE, ThemeUtils.calculateOpacityTransform(0, Color.BLUE, Color.WHITE));
    }

    public void testCalculateOpacityTransform_fullOpacity() {
        assertEquals(Color.BLUE, ThemeUtils.calculateOpacityTransform(1, Color.BLUE, Color.WHITE));
    }

    public void testCalculateOpacityTransform_returnsFullOpacity() {
        final int color = ThemeUtils.calculateOpacityTransform(0, Color.BLUE, Color.WHITE);
        assertEquals(0xFF000000, color & 0xFF000000);
    }
}
