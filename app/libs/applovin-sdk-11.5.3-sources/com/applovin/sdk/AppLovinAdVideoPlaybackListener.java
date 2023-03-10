package com.applovin.sdk;


/**
 * This interface defines a listener to ad video playback state. All registered listeners will be notified when a video ad changes to a relevant state. If a given ad is not a video ad, no video callbacks will be triggered.
 */

public interface AppLovinAdVideoPlaybackListener
{
    /**
     * Triggered when a video begins playing in a video advertisement.
     * <p>
     * If your app plays other videos or music, please pause them upon receiving this callback.
     *
     * @param ad Ad in which playback began.
     */
    void videoPlaybackBegan(final AppLovinAd ad);

    /**
     * Triggered when a video stops playing in a video advertisement.
     * <p>
     * If your app was playing music when the video began, this is a good opportunity to resume it. If your app was playing video or otherwise requires user interaction, you probably want to use <code>adHidden()</code> in {@link com.applovin.sdk.AppLovinAdDisplayListener} instead.
     *
     * @param ad            Ad in which playback ended.
     * @param percentViewed Percent of the video which the user watched.
     * @param fullyWatched  Whether or not the video was watched to, or very near, completion. This parameter is a simply convenience and is computed as <code>(percentViewed >= 95)</code>.
     */
    void videoPlaybackEnded(final AppLovinAd ad, final double percentViewed, final boolean fullyWatched);
}
