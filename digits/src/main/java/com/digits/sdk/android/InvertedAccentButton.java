package com.digits.sdk.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class InvertedAccentButton extends Button {
    public InvertedAccentButton(Context context) {
        this(context, null);
    }

    public InvertedAccentButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public InvertedAccentButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    void init() {
        final int accentColor = ThemeUtils.getAccentColor(getResources(), getContext().getTheme());
        final ButtonThemer buttonThemer = new ButtonThemer(getResources());

        buttonThemer.disableDropShadow(this);
        buttonThemer.setBackgroundAccentColorInverse(this, accentColor);
        buttonThemer.setTextAccentColorInverse(this, accentColor);
    }
}
