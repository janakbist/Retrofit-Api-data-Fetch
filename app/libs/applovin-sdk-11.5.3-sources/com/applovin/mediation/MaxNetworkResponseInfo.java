package com.applovin.mediation;

import android.os.Bundle;

/**
 * Created by Thomas So on November 04 2021
 * <p>
 * This class represents an ad response in a waterfall.
 */
public interface MaxNetworkResponseInfo
{
    /**
     * This enum contains possible states of an ad in the waterfall the adapter response info could represent.
     */
    enum AdLoadState
    {
        /**
         * The AppLovin MAX SDK did not attempt to load an ad from this network in the waterfall because an ad higher in the waterfall loaded successfully.
         */
        AD_LOAD_NOT_ATTEMPTED,

        /**
         * An ad successfully loaded from this network.
         */
        AD_LOADED,

        /**
         * An ad failed to load from this network.
         */
        FAILED_TO_LOAD
    }

    /**
     * The state of the ad that this {@code MaxResponseInfo} object represents. For more info, see the {@code AdLoadState} enum.
     */
    AdLoadState getAdLoadState();

    /**
     * The mediated network that this adapter response info object represents.
     */
    MaxMediatedNetworkInfo getMediatedNetwork();

    /**
     * The credentials used to load an ad from this adapter, as entered in the AppLovin MAX dashboard.
     */
    Bundle getCredentials();

    /**
     * The amount of time the network took to load (either successfully or not) an ad, in milliseconds. If an attempt to load an ad has not been made (i.e. the {@code MaxResponseInfo#getAdLoadState()} is {@code AdLoadState.AD_LOAD_NOT_ATTEMPTED}, the value will be -1).
     */
    long getLatencyMillis();

    /**
     * The ad load error this network response resulted in. Will be {@code null} if an attempt to load an ad has not been made or an ad was loaded successfully (i.e. the {@code MaxResponseInfo#getAdLoadState()} is NOT {@code AdLoadState.FAILED_TO_LOAD}).
     */
    MaxError getError();
}
