package com.applovin.sdk;

/**
 * This interface defines a listener intended to be notified when a user clicks on an ad.
 * <p>
 * Since all AppLovin ads launch the device browser on click, this effectively notifies you that your app is about to be minimized to the background.
 *
 * @author Basil Shikin
 */
public interface AppLovinAdClickListener
{
    /**
     * This method is invoked when the ad is clicked.
     * <p>
     * This method is invoked on the main UI thread.
     *
     * @param ad Ad that was just clicked. Guaranteed not to be null.
     */
    void adClicked(final AppLovinAd ad);
}
