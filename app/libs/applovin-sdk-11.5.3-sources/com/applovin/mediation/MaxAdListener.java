package com.applovin.mediation;

/**
 * This interface defines a listener intended to be notified about ad events.
 */
public interface MaxAdListener
{
    /**
     * The SDK invokes this callback when it successfully loads an ad.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param ad The ad that the SDK successfully loaded.
     */
    void onAdLoaded(final MaxAd ad);

    /**
     * The SDK invokes this callback when it successfully displays the ad.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param ad The ad that the SDK successfully displayed.
     */
    void onAdDisplayed(final MaxAd ad);

    /**
     * The SDK invokes this callback when it finishes displaying the ad.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param ad The ad that the SDK finished displaying.
     */
    void onAdHidden(final MaxAd ad);

    /**
     * The SDK invokes this callback when a user clicks the ad.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param ad The ad that the user clicked.
     */
    void onAdClicked(final MaxAd ad);

    /**
     * The SDK invokes this callback when it fails to load an ad.
     * <p>
     * To see the error code, see {@link MaxError#getCode()}. See {@link MaxErrorCode} for a list of error codes.
     * To see a description of the error, see {@link MaxError#getMessage()}.
     * To see the error code and reasons for each ad network that failed to load, see {@link MaxError#getAdLoadFailureInfo()}.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param adUnitId The ad unit ID that the SDK failed to load an ad for.
     * @param error    An object that encapsulates the failure info.
     */
    void onAdLoadFailed(final String adUnitId, final MaxError error);

    /**
     * The SDK invokes this callback when it fails to successfully display a fullscreen ad.
     * <p>
     * To see the error code, see {@link MaxError#getCode()}. See {@link MaxErrorCode} for a list of error codes.
     * To see the error reason, see {@link MaxError#getMessage()}.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param ad    The ad that the SDK failed to display for.
     * @param error An object that encapsulates the failure info.
     */
    void onAdDisplayFailed(final MaxAd ad, final MaxError error);
}
