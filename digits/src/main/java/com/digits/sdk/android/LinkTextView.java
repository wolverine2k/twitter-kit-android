package com.digits.sdk.android;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;


public class LinkTextView extends TextView {
    public LinkTextView(Context context) {
        super(context);
        init(context);
    }

    public LinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public LinkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setTextColor(getLinkColor(context));
    }

    private int getLinkColor(Context context) {
        final TypedValue value = ThemeUtils.getTypedValueColor(context.getTheme(),
                android.R.attr.textColorLink);
        if (value == null) {
            return ThemeUtils.getAccentColor(context.getResources(), context.getTheme());
        }
        return value.data;
    }

}
