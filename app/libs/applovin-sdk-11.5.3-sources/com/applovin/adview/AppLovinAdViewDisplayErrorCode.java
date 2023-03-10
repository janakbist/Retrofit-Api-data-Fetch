package com.applovin.adview;

/**
 * This enum contains possible error codes that should be returned when the ad view fails to display an ad.
 * <p>
 * Created by thomasso on 7/5/17.
 */

public enum AppLovinAdViewDisplayErrorCode
{
    /**
     * The ad view failed to display an ad for an unspecified reason.
     */
    UNSPECIFIED,

    /**
     * The ad view could not be rendered, because the Android WebView framework is updating.
     */
    WEBVIEW_NOT_FOUND
}
