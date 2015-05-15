package com.twitter.sdk.android.core.identity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import static org.mockito.Mockito.*;

public final class TestUtils {

    private TestUtils() {
        // Private constructor
    }

    public static void setUpTwitterInstalled(Context mockContext)
            throws PackageManager.NameNotFoundException {
        setupTwitterInstalled(mockContext, SSOAuthHandler.APP_SIGNATURE);
    }

    public static void setupTwitterInstalled(Context mockContext, String signature)
            throws PackageManager.NameNotFoundException {
        final PackageManager mockPm = mock(PackageManager.class);
        final PackageInfo mockPackageInfo = mock(PackageInfo.class);
        mockPackageInfo.signatures = new Signature[] {
                new Signature(signature)
        };

        when(mockContext.getPackageManager()).thenReturn(mockPm);
        when(mockPm.getPackageInfo(SSOAuthHandler.PACKAGE_NAME, PackageManager.GET_SIGNATURES))
                .thenReturn(mockPackageInfo);
    }

    public static void setUpTwitterNotInstalled(Context mockContext)
            throws PackageManager.NameNotFoundException {
        final PackageManager mockPm = mock(PackageManager.class);

        when(mockContext.getPackageManager()).thenReturn(mockPm);
        when(mockPm.getPackageInfo(SSOAuthHandler.PACKAGE_NAME, PackageManager.GET_SIGNATURES))
                .thenThrow(new PackageManager.NameNotFoundException());
    }
}
