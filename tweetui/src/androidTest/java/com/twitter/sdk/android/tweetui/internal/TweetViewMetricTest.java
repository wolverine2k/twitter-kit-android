package com.twitter.sdk.android.tweetui.internal;

import com.twitter.cobalt.metrics.MetricsManager;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import static org.mockito.Mockito.mock;

/**
 * Tests the TweetViewMetrics of BaseTweetViews
 */
public class TweetViewMetricTest extends TwitterAndroidTestCase {
    private static final String REQUIRED_RENDER_METRIC_TAG = "TweetTimingMetric";
    private static final String REQUIRED_RENDER_METRIC_NAME = "tweetview:render";

    MetricsManager metricsManager;
    MetricsManager mockMetricsManager;
    TweetViewMetric metricComponent;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        MetricsManager.initialize(getContext());
        metricsManager = MetricsManager.getInstance();
        mockMetricsManager = mock(MetricsManager.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        // ensure metrics are destroyed at the end of a test
        if (metricComponent != null) {
            metricComponent.destroyAll();
        }
    }

    public void testConstruction_nullManager() {
        try {
            final TweetViewMetric metricComponent = new TweetViewMetric(null);
            // TweetViewMetric with null MetricManager should perform NoOps
            metricComponent.start();
            metricComponent.finishRender();
            metricComponent.destroyAll();
            assertNull(metricComponent.renderMetric);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    public void testConstructor() {
        metricComponent = new TweetViewMetric(metricsManager);
        assertNotNull(metricComponent.metricsManager);
    }

    // render metric

    public void testRenderMetricName() {
        assertEquals(REQUIRED_RENDER_METRIC_NAME, TweetViewMetric.RENDER_METRIC_NAME);
    }

    public void testMetric_renderMetric() {
        final TweetViewMetric metricComponent = new TweetViewMetric(metricsManager);
        metricComponent.start();
        assertTrue(metricComponent.renderMetric.isMeasuring());
        metricComponent.finishRender();
        assertFalse(metricComponent.renderMetric.isMeasuring());
    }

    public void testMetricTag_renderMetric() {
        final TweetViewMetric metricComponent = new TweetViewMetric(metricsManager);
        metricComponent.start();
        assertEquals(REQUIRED_RENDER_METRIC_TAG, metricComponent.renderMetric.getTag());
    }
}
