package com.twitter.sdk.android.tweetui.internal.util;

import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.twitter.sdk.android.tweetui.R;

public class AspectRatioImageViewTest extends AndroidTestCase {

    private static final float DELTA = 0.001f;

    AspectRatioImageView getHeightAdjustedView() {
        return (AspectRatioImageView) getInflatedLayout().findViewById(R.id.height_adjusted_view);
    }

    AspectRatioImageView getWidthAdjustedView() {
        return (AspectRatioImageView) getInflatedLayout().findViewById(R.id.width_adjusted_view);
    }

    public void testHeightAdjusted() {
        final AspectRatioImageView imageView = getHeightAdjustedView();
        assertEquals(1.6, imageView.getAspectRatio(), DELTA);
        assertEquals(AspectRatioImageView.ADJUST_DIMENSION_HEIGHT,
                imageView.getDimensionToAdjust());
    }

    public void testWidthAdjusted() {
        final AspectRatioImageView imageView = getWidthAdjustedView();
        assertEquals(1.2, imageView.getAspectRatio(), DELTA);
        assertEquals(AspectRatioImageView.ADJUST_DIMENSION_WIDTH,
                imageView.getDimensionToAdjust());
    }

    protected View getInflatedLayout() {
        final ViewGroup group = new LinearLayout(getContext());
        return LayoutInflater.from(getContext()).inflate(
                R.layout.activity_aspect_ratio_image_view_test, group, true);
    }
}
