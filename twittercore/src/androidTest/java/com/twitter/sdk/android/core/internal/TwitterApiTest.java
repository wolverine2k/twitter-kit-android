package com.twitter.sdk.android.core.internal;

import android.net.Uri;
import android.os.Build;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

public class TwitterApiTest extends TwitterAndroidTestCase {

    public void testBuildUponBaseHost_singlePath() {
        final String baseHost = "testbasehost";
        final TwitterApi api = new TwitterApi(baseHost);
        final Uri.Builder builder = api.buildUponBaseHostUrl("path1");
        assertEquals(baseHost + "/path1", builder.build().toString());
    }

    public void testBuildUponBaseHost_multiplePaths() {
        final String baseHost = "testbasehost";
        final TwitterApi api = new TwitterApi(baseHost);
        final Uri.Builder builder = api.buildUponBaseHostUrl("path1", "path2");
        assertEquals(baseHost + "/path1/path2", builder.build().toString());
    }

    public void testBuildUserAgent() {
        final String clientName = "TwitterAndroidSDK";
        final String version = "1.0.0.1";
        final String userAgent = TwitterApi.buildUserAgent(clientName, version);
        assertEquals(
                // client_name/client_version model/os_version (manufacturer;device;brand;product)
                String.format("%s/%s %s/%s (%s;%s;%s;%s)",
                        clientName, version, Build.MODEL, Build.VERSION.RELEASE, Build.MANUFACTURER,
                        Build.MODEL, Build.BRAND, Build.PRODUCT),
                userAgent);
    }
}
