package com.twitter.sdk.android.tweetui;

import android.graphics.Color;
import android.test.AndroidTestCase;

public class ColorUtilsTest extends AndroidTestCase {

    public void testIsLightColor_blue() {
        assertFalse(ColorUtils.isLightColor(Color.BLUE));
    }

    public void testIsLightColor_black() {
        assertFalse(ColorUtils.isLightColor(Color.BLACK));
    }

    public void testIsLightColor_white() {
        assertTrue(ColorUtils.isLightColor(Color.WHITE));
    }

    public void testCalculateOpacityTransform_zeroOpacity() {
        assertEquals(Color.WHITE, ColorUtils.calculateOpacityTransform(0, Color.BLUE, Color.WHITE));
    }

    public void testCalculateOpacityTransform_fullOpacity() {
        assertEquals(Color.BLUE, ColorUtils.calculateOpacityTransform(1, Color.BLUE, Color.WHITE));
    }

    public void testCalculateOpacityTransform_returnsFullOpacity() {
        final int color = ColorUtils.calculateOpacityTransform(0, Color.BLUE, Color.WHITE);
        assertEquals(0xFF000000, color & 0xFF000000);
    }
}
