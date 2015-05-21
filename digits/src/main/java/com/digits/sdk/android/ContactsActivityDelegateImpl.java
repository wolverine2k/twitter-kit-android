package com.digits.sdk.android;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class ContactsActivityDelegateImpl implements ContactsActivityDelegate {
    final Activity activity;
    final ContactsController controller;

    public ContactsActivityDelegateImpl(Activity activity) {
        this(activity, new ContactsControllerImpl());
    }

    public ContactsActivityDelegateImpl(Activity activity, ContactsController controller) {
        this.activity = activity;
        this.controller = controller;
    }

    public void init() {
        setContentView();
        setUpViews();
    }

    protected void setContentView() {
        activity.setContentView(R.layout.dgts__activity_contacts);
    }

    protected void setUpViews() {
        final Button notNowButton = (Button) activity.findViewById(R.id.dgts__not_now);
        final Button okayButton = (Button) activity.findViewById(R.id.dgts__okay);
        final TextView description = (TextView) activity.findViewById(R.id.dgts__upload_contacts);

        setUpNotNowButton(notNowButton);
        setUpOkayButton(okayButton);
        setUpDescription(description);
    }

    protected void setUpDescription(TextView textView) {
        textView.setText(getFormattedDescription());
    }

    protected String getApplicationName() {
        return activity.getApplicationInfo().loadLabel(activity.getPackageManager()).toString();
    }

    protected String getFormattedDescription() {
        return activity.getString(R.string.dgts__upload_contacts, getApplicationName());
    }

    protected void setUpNotNowButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    protected void setUpOkayButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.startUploadService(activity);
                activity.finish();
            }
        });
    }
}
