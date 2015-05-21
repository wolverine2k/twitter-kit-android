package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;

public interface DigitsActivityDelegate extends ActivityLifecycle {
    /**
     * Returns the layout resource id of the subclass. This resource will be used to set the view
     * of the Activity
     */
    int getLayoutId();

    /**
     * Returns true if the bundle param is valid and the activity can be created, otherwise false
     */
    boolean isValid(Bundle bundle);

    /**
     * Initializes the views in the Activity.
     */
    void init(Activity activity, Bundle bundle);
}
