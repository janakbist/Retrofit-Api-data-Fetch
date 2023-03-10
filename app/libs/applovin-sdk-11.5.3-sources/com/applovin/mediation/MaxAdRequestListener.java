package com.applovin.mediation;

/**
 * Created by Andrew Tian on July 8 2022
 * <p>
 * This interface defines a listener to be notified about ad request events.
 */
public interface MaxAdRequestListener
{
    /**
     * The SDK invokes this callback when it sends a request for an ad, which can be for the initial ad load and upcoming ad refreshes.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param adUnitId The ad unit ID that the SDK requested an ad for.
     */
    void onAdRequestStarted(final String adUnitId);
}
