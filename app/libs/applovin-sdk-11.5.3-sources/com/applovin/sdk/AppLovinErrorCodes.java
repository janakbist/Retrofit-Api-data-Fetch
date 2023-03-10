package com.applovin.sdk;

import com.applovin.mediation.MaxErrorCode;

/**
 * This class contains a set of common integer error codes seen in the SDK.
 */
public class AppLovinErrorCodes
{
    /**
     * Indicates that the SDK is currently disabled.
     */
    public static final int SDK_DISABLED = -22;

    /**
     * Indicates that the network conditions prevented the SDK from receiving an ad.
     */
    public static final int FETCH_AD_TIMEOUT = MaxErrorCode.NETWORK_TIMEOUT;

    /**
     * Indicates that the device had no network connectivity at the time of an ad request, either due to airplane mode or no service.
     */
    public static final int NO_NETWORK = MaxErrorCode.NO_NETWORK;

    /**
     * Indicates that no ads are currently eligible for your device.
     */
    public static final int NO_FILL = MaxErrorCode.NO_FILL;

    /**
     * Indicates that there has been a failure to render an ad on screen.
     */
    public static final int UNABLE_TO_RENDER_AD = -6;

    /**
     * Indicates that the zone provided is invalid; the zone needs to be added to your AppLovin account or may still be propagating to our servers.
     */
    public static final int INVALID_ZONE = -7;

    /**
     * Indicates that the provided ad token is invalid; ad token must be returned from AppLovin S2S integration.
     */
    public static final int INVALID_AD_TOKEN = -8;

    /**
     * Indicates that the system is in unexpected state.
     */
    public static final int UNSPECIFIED_ERROR = MaxErrorCode.UNSPECIFIED;

    /**
     * Indicates that the developer called for a rewarded video before one was available. Note: This code is only possible when working with rewarded videos.
     */
    public static final int INCENTIVIZED_NO_AD_PRELOADED = -300;

    /**
     * Indicates that an unknown server-side error occurred. Note: This code is only possible when working with rewarded videos.
     */
    public static final int INCENTIVIZED_UNKNOWN_SERVER_ERROR = -400;

    /**
     * Indicates that a reward validation requested timed out (usually due to poor connectivity). Note: This code is only possible when working with rewarded videos.
     */
    public static final int INCENTIVIZED_SERVER_TIMEOUT = -500;

    /**
     * Indicates that the user exited out of the ad early. You may or may not wish to grant a reward depending on your preference. Note: This code is only possible when working with rewarded ads.
     */
    public static final int INCENTIVIZED_USER_CLOSED_VIDEO = -600;

    /**
     * Indicates that a AppLovin servers have returned an invalid response
     */
    public static final int INVALID_RESPONSE = -800;

    /**
     * Indicates that a postback URL you attempted to dispatch was empty or nil.
     */
    public static final int INVALID_URL = -900;

    /**
     * Indicates that an attempt to cache a resource to the filesystem failed; the device may be out of space.
     */
    public static final int UNABLE_TO_PRECACHE_RESOURCES = -200;

    /**
     * Indicates that an attempt to cache an image resource to the filesystem failed; the device may be out of space.
     */
    public static final int UNABLE_TO_PRECACHE_IMAGE_RESOURCES = -201;

    /**
     * Indicates that an attempt to cache a video resource to the filesystem failed; the device may be out of space.
     */
    public static final int UNABLE_TO_PRECACHE_VIDEO_RESOURCES = -202;
}