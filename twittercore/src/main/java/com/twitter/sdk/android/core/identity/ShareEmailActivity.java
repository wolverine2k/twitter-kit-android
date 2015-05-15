package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.TextView;

import com.twitter.sdk.android.core.R;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import io.fabric.sdk.android.Fabric;

/**
 * Activity for requesting access to a user's email.  This activity should not be called directly.
 */
public class ShareEmailActivity extends Activity {

    static final String EXTRA_RESULT_RECEIVER = "result_receiver";
    static final String EXTRA_SESSION_ID = "session_id";

    /**
     * Package private for testing.
     */
    ShareEmailController controller;

    private TwitterSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tw__activity_share_email);

        try {
            final Intent startIntent = getIntent();
            final ResultReceiver resultReceiver = getResultReceiver(startIntent);
            session = getSession(startIntent);
            controller = new ShareEmailController(new ShareEmailClient(session), resultReceiver);

            final TextView shareEmailDescView = (TextView) findViewById(R.id.tw__share_email_desc);
            setUpShareEmailDesc(this, shareEmailDescView);
        } catch (IllegalArgumentException e) {
            Fabric.getLogger().e(TwitterCore.TAG, "Failed to create ShareEmailActivity.", e);
            finish();
        }
    }

    private ResultReceiver getResultReceiver(Intent intent) {
        final ResultReceiver resultReceiver = intent.getParcelableExtra(EXTRA_RESULT_RECEIVER);
        if (resultReceiver == null) {
            throw new IllegalArgumentException("ResultReceiver must not be null. This activity "
                    + "should not be started directly.");
        }
        return resultReceiver;
    }

    private TwitterSession getSession(Intent intent) {
        // TODO: Make session parcelable and pass actual session.
        final long sessionId = intent.getLongExtra(EXTRA_SESSION_ID,
                TwitterSession.UNKNOWN_USER_ID);
        final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                .getSession(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("No TwitterSession for id:" + sessionId);
        }
        return session;
    }

    void setUpShareEmailDesc(Context context, TextView shareEmailDescView) {
        final PackageManager packageManager = context.getPackageManager();
        shareEmailDescView.setText(getResources().getString(R.string.tw__share_email_desc,
                packageManager.getApplicationLabel(context.getApplicationInfo()),
                session.getUserName()));
    }

    public void onClickNotNow(View view) {
        controller.cancelRequest();
        finish();
    }

    public void onClickAllow(View view) {
        controller.executeRequest();
        finish();
    }

    @Override
    public void onBackPressed() {
        controller.cancelRequest();
        super.onBackPressed();
    }
}
