package com.digits.sdk.android;

import android.content.Context;
import android.content.res.TypedArray;

import java.lang.reflect.Field;

class ActivityClassManagerFactory {

    ActivityClassManager createActivityClassManager(Context context, int themeResId) {
        try {
            Class.forName("android.support.v7.app.ActionBarActivity");

            final ThemeAttributes attributes = new ThemeAttributes();
            if (isAppCompatTheme(context, themeResId, attributes)) {
                return new AppCompatClassManagerImp();
            } else {
                return new ActivityClassManagerImp();
            }
        } catch (Exception e) {
            return new ActivityClassManagerImp();
        }
    }

    boolean isAppCompatTheme(Context context, int themeResId, ThemeAttributes attributes) {

        final TypedArray a = context.obtainStyledAttributes(themeResId, attributes.styleableTheme);
        final boolean result = a.hasValue(attributes.styleableThemeWindowActionBar);
        a.recycle();

        return result;
    }

    static class ThemeAttributes {
        private final static String CLASS_NAME = "android.support.v7.appcompat.R$styleable";
        private final int[] styleableTheme;
        private final int styleableThemeWindowActionBar;

        public ThemeAttributes() throws Exception {
            final Class<?> clazz = Class.forName(CLASS_NAME);
            Field field = clazz.getField("Theme");
            styleableTheme = (int[]) field.get(field.getType());

            field = clazz.getField("Theme_windowActionBar");
            styleableThemeWindowActionBar = (int) field.get(field.getType());
        }
    }
}
