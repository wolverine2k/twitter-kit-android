package com.digits.sdk.android;

import android.os.Bundle;

public class BundleManagerTest extends DigitsAndroidTestCase {

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";

    public void testAssertContains_populatedBundle() throws Exception {
        final Bundle bundle = new Bundle();
        bundle.putString(KEY_1, "data");
        assertTrue(BundleManager.assertContains(bundle, KEY_1));
    }

    public void testAssertContains_notFullyPopulated() throws Exception {
        final Bundle bundle = new Bundle();
        bundle.putString(KEY_1, "data");
        assertFalse(BundleManager.assertContains(bundle, KEY_1, KEY_2));
    }

    public void testAssertContais_nullBundle() throws Exception {
        assertFalse(BundleManager.assertContains(null, KEY_1));
    }

    public void testAssertContains_emptyBundle() throws Exception {
        assertFalse(BundleManager.assertContains(new Bundle(), KEY_1));
    }

    @SuppressWarnings("NullArgumentToVariableArgMethod")
    public void testAssertContains_nullKey() throws Exception {
        assertFalse(BundleManager.assertContains(new Bundle(), null));
    }
}
