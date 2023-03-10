package com.applovin.mediation;

/**
 * Created by Thomas So on May 08 2021
 * <p>
 * This class contains various error codes that the SDK can return when a MAX ad fails to load or display.
 */
public interface MaxErrorCode
{
    /**
     * This error code represents an error that could not be categorized into one of the other defined errors. See the message field in the error object for more details.
     */
    int UNSPECIFIED = -1;

    /**
     * This error code indicates that MAX returned no eligible ads from any mediated networks for this app/device.
     */
    int NO_FILL = 204;

    /**
     * This error code indicates that MAX returned eligible ads from mediated networks, but all ads failed to load. See the adLoadFailureInfo field in the error object for more details.
     */
    int AD_LOAD_FAILED = -5001;

    /**
     * This error code indicates that the ad request failed due to a generic network error. See the message field in the error object for more details.
     */
    int NETWORK_ERROR = -1000;

    /**
     * This error code indicates that the ad request timed out due to a slow internet connection.
     */
    int NETWORK_TIMEOUT = -1001;

    /**
     * This error code indicates that the ad request failed because the device is not connected to the internet.
     */
    int NO_NETWORK = -1009;

    /**
     * This error code indicates that you attempted to show a fullscreen ad while another fullscreen ad is still showing.
     */
    int FULLSCREEN_AD_ALREADY_SHOWING = -23;

    /**
     * This error code indicates you are attempting to show a fullscreen ad before the one has been loaded.
     */
    int FULLSCREEN_AD_NOT_READY = -24;

    /**
     * This error code indicates that the SDK failed to load an ad because it could not find the top Activity.
     */
    int NO_ACTIVITY = -5601;

    /**
     * This error code indicates that the SDK failed to display an ad because the user has the "Don't Keep Activities" developer setting enabled.
     */
    int DONT_KEEP_ACTIVITIES_ENABLED = -5602;
}
