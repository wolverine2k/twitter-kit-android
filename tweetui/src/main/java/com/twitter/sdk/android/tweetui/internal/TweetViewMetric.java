package com.twitter.sdk.android.tweetui.internal;

import com.twitter.cobalt.metrics.Metric;
import com.twitter.cobalt.metrics.MetricsManager;
import com.twitter.cobalt.metrics.TimingMetric;
import com.twitter.cobalt.metrics.Metric.Level;

import io.fabric.sdk.android.Fabric;

/**
 * Manages starting and stopping any metrics for a BaseTweetView.
 */
public class TweetViewMetric {
    private static final String TAG = "TweetViewMetric";

    // metric tag
    public static final String METRIC_TAG = "TweetTimingMetric";
    // metric names
    // metric ids for synchronous tasks do not need to be unique, use metric name
    static final String RENDER_METRIC_NAME = "tweetview:render";
    // metric reporting levels
    static final Level METRIC_LEVEL = Metric.LEVEL_DEBUG;

    MetricsManager metricsManager;
    // time from construction to finish rendering
    TimingMetric renderMetric;

    public TweetViewMetric(MetricsManager metricsManager) {
        if (metricsManager == null) {
            Fabric.getLogger().d(TAG, "MetricsManager was null");
        }
        this.metricsManager = metricsManager;
    }

    /**
     * Create and start metrics.
     */
    public void start() {
        // NoOp if metricsManager is null
        if (metricsManager == null) {
            Fabric.getLogger().d(TAG, "MetricsManager was null");
            return;
        }
        // metricsManager listener will make data available to reporters, does not persist
        renderMetric = new TimingMetric(RENDER_METRIC_NAME, METRIC_LEVEL, RENDER_METRIC_NAME,
                metricsManager);
        renderMetric.setTag(METRIC_TAG);
        renderMetric.startMeasuring();
    }

    /**
     * Stop the renderMetric.
     */
    public void finishRender() {
        if (renderMetric == null) {
            Fabric.getLogger().d(TAG, "Must call start() before finishRender()");
            return;
        }
        renderMetric.stopMeasuring();
    }

    /**
     * Destroy all metrics. Skip stopping timers and reporting metrics.
     * For testing cleanup.
     */
    void destroyAll() {
        if (renderMetric != null) {
            renderMetric.destroyMetric();
        }
    }
}
