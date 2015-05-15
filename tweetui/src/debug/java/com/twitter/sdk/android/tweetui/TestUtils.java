package com.twitter.sdk.android.tweetui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.widget.ImageView;

import java.util.Locale;

public final class TestUtils {

    private TestUtils() {}

    /**
     * Sets global locale
     * @param locale Locale to set
     */
    public static Locale setLocale(Context context, Locale locale) {
        final Resources res = context.getResources();
        final Configuration config = res.getConfiguration();
        final Locale originalLocale = config.locale;

        Locale.setDefault(locale);
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
        return originalLocale;
    }


    /**
     * Gets the color of the ImageView's ColorDrawable or 0 for API < 11.
     * @param imageView an ImageView with a ColorDrawable
     * @return int color of the ImageView
     */
    @TargetApi(11)
    public static int getDrawableColor(ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 11) {
            final ColorDrawable drawable = (ColorDrawable) imageView.getDrawable();
            return drawable.getColor();
        }
        return 0;
    }
}
