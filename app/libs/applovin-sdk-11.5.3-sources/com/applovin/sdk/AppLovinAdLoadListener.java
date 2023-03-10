package com.applovin.sdk;

/**
 * This interface defines a listener intended to be notified when the SDK loads, or fails to load, an ad over the network.
 *
 * @author Basil Shikin
 */
public interface AppLovinAdLoadListener
{
    /**
     * This method is called when a new ad has been received.
     * <p>
     * This method is invoked on the UI thread.
     *
     * @param ad Newly received ad. Guaranteed not to be null.
     */
    void adReceived(AppLovinAd ad);

    /**
     * This method is called when an ad could not be retrieved from the server.
     * <p>
     * This method is invoked on the UI thread
     * <p>
     * Common error codes are: </br> <code>204</code> -- no ad is available</br> <code>5xx</code> -- internal server error</br> <code>negative number</code> -- internal errors </br>
     *
     * @param errorCode An error code representing the reason the ad failed to load. Common error codes are defined in {@link com.applovin.sdk.AppLovinErrorCodes}.
     */
    void failedToReceiveAd(int errorCode);
}