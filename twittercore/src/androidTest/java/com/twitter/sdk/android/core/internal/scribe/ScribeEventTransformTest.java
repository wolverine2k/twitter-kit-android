package com.twitter.sdk.android.core.internal.scribe;

import io.fabric.sdk.android.services.common.CommonUtils;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ScribeEventTransformTest extends TwitterAndroidTestCase {

    private ScribeEvent.Transform transform;
    private ScribeEvent scribeEvent;
    private String scribeEventJsonString;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        transform = new ScribeEvent.Transform(new GsonBuilder().create());

        final EventNamespace eventNamespace = new EventNamespace.Builder()
                .setClient("testclient")
                .setPage("testpage")
                .setSection("testsection")
                .setComponent("testcomponent")
                .setElement("testelement")
                .setAction("testaction")
                .builder();
        scribeEvent = new ScribeEvent("testcategory", eventNamespace, 1404426136717L);

        InputStream is = null;
        try {
            is = getContext().getAssets().open("scribe_event.json");
            scribeEventJsonString = CommonUtils.streamToString(is).trim();
        } finally {
            CommonUtils.closeQuietly(is);
        }
    }

    public void testToBytes() throws IOException {
        final byte[] bytes = transform.toBytes(scribeEvent);
        assertEquals(scribeEventJsonString, new String(bytes, "UTF-8"));
    }
}
