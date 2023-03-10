package com.applovin.mediation;

/**
 * This interface defines a listener that is notified about ad view events
 */
public interface MaxAdViewAdListener
        extends MaxAdListener
{
    /**
     * This method will be invoked when the {@link com.applovin.mediation.ads.MaxAdView} has expanded full screen.
     *
     * @param ad An ad for which the ad view expanded for. Guaranteed not to be null.
     */
    void onAdExpanded(final MaxAd ad);

    /**
     * This method will be invoked when the {@link com.applovin.mediation.ads.MaxAdView} has collapsed back to its original size.
     *
     * @param ad An ad for which the ad view collapsed for. Guaranteed not to be null.
     */
    void onAdCollapsed(final MaxAd ad);
}
