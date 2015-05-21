package com.digits.sdk.android;

import android.content.Context;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterException;

import java.lang.ref.WeakReference;

import io.fabric.sdk.android.Fabric;

public abstract class DigitsCallback<T> extends Callback<T> {
    final DigitsController controller;
    private final WeakReference<Context> context;

    DigitsCallback(Context context, DigitsController controller) {

        this.context = new WeakReference<>(context);
        this.controller = controller;
    }

    @Override
    public void failure(TwitterException exception) {
        final DigitsException digitsException = DigitsException.create(controller.getErrors(),
                exception);
        final StringBuilder builder = new StringBuilder();
        builder.append("HTTP Error: ")
                .append(exception.getMessage())
                .append(", API Error: ")
                .append(digitsException.getErrorCode())
                .append(", User Message: ")
                .append(digitsException.getMessage());
        Fabric.getLogger().e(Digits.TAG, builder.toString());
        controller.handleError(context.get(), digitsException);
    }
}
