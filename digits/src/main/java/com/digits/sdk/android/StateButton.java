package com.digits.sdk.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StateButton extends RelativeLayout {

    TextView textView;
    ProgressBar progressBar;
    ImageView imageView;
    CharSequence progressText;
    CharSequence finishText;
    CharSequence startText;
    ButtonThemer buttonThemer;
    int accentColor;

    public StateButton(Context context) {
        this(context, null);
    }

    public StateButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StateButton);
        init(array);
        array.recycle();

        accentColor = ThemeUtils.getAccentColor(getResources(), context.getTheme());
        buttonThemer = new ButtonThemer(getResources());

        buttonThemer.setBackgroundAccentColor(this, accentColor);
        buttonThemer.setTextAccentColor(textView, accentColor);
        setImageAccentColor();
        setSpinnerAccentColor();
    }

    void setImageAccentColor() {
        imageView.setColorFilter(getTextColor(), PorterDuff.Mode.SRC_IN);
    }

    void setSpinnerAccentColor() {
        progressBar.setIndeterminateDrawable(getProgressDrawable());
    }

    int getTextColor() {
        return buttonThemer.getTextColor(accentColor);
    }

    Drawable getProgressDrawable() {
        return ThemeUtils.isLightColor(accentColor) ? getResources().getDrawable(R.drawable
                .progress_dark) : getResources().getDrawable(R.drawable.progress_light);
    }

    public void setStatesText(int startResId, int progressResId, int finishResId) {
        final Context context = getContext();
        startText = context.getString(startResId);
        progressText = context.getString(progressResId);
        finishText = context.getString(finishResId);
    }

    void init(TypedArray array) {
        startText = array.getText(R.styleable.StateButton_startStateText);
        progressText = array.getText(R.styleable.StateButton_progressStateText);
        finishText = array.getText(R.styleable.StateButton_finishStateText);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.dgts__state_button, this);

        textView = (TextView) this.findViewById(R.id.dgts__state_button);
        progressBar = (ProgressBar) this.findViewById(R.id.dgts__state_progress);
        imageView = (ImageView) this.findViewById(R.id.dgts__state_success);

        showStart();
    }

    public void showProgress() {
        textView.setText(progressText);
        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
    }

    public void showFinish() {
        textView.setText(finishText);
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }

    public void showError() {
        //TODO error show dialog
        showStart();
    }

    public void showStart() {
        textView.setText(startText);
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
    }

}
