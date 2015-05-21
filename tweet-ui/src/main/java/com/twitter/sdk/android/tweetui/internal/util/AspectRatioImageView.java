package com.twitter.sdk.android.tweetui.internal.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.twitter.sdk.android.tweetui.R;

/**
 * AspectRatioImageView maintains an aspect ratio by adjusting the width or height dimension. The
 * aspect ratio (width to height ratio) and adjustment dimension can be configured.
 */
public class AspectRatioImageView extends ImageView {

    private static final float DEFAULT_ASPECT_RATIO = 1f;
    private static final int DEFAULT_ADJUST_DIMENSION = 0;
    // defined by attrs.xml enum
    static final int ADJUST_DIMENSION_HEIGHT = 0;
    static final int ADJUST_DIMENSION_WIDTH = 1;

    private float aspectRatio;          // width to height ratio
    private int dimensionToAdjust;      // ADJUST_DIMENSION_HEIGHT or ADJUST_DIMENSION_WIDTH

    public AspectRatioImageView(Context context) {
        this(context, null);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.tw__AspectRatioImageView);
        try {
            aspectRatio = a.getFloat(R.styleable.tw__AspectRatioImageView_tw__image_aspect_ratio,
                    DEFAULT_ASPECT_RATIO);
            dimensionToAdjust
                    = a.getInt(R.styleable.tw__AspectRatioImageView_tw__image_dimension_to_adjust,
                    DEFAULT_ADJUST_DIMENSION);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (dimensionToAdjust == ADJUST_DIMENSION_HEIGHT) {
            height = (int) (width / aspectRatio);
        } else {
            width = (int) (height * aspectRatio);
        }
        setMeasuredDimension(width, height);
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public int getDimensionToAdjust() {
        return dimensionToAdjust;
    }
}
