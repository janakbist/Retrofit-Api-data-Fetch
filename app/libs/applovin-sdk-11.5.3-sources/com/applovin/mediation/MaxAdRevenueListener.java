package com.applovin.mediation;

/**
 * Created by Thomas So on June 12 2021
 * <p>
 * This interface defines a listener to be notified about ad revenue events.
 */
public interface MaxAdRevenueListener
{
    /**
     * The SDK invokes this callback when it detects a revenue event for an ad.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param ad The ad for which the revenue event was detected. Guaranteed not to be null.
     */
    void onAdRevenuePaid(final MaxAd ad);
}
