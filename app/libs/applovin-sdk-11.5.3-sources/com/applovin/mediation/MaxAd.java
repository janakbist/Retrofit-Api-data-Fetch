package com.applovin.mediation;

import com.applovin.mediation.nativeAds.MaxNativeAd;
import com.applovin.sdk.AppLovinSdkUtils;

import androidx.annotation.Nullable;

/**
 * This interface represents an ad that has been served by AppLovin MAX.
 */
public interface MaxAd
{
    /**
     * @return The loaded ad format. Guaranteed not to be null.
     */
    MaxAdFormat getFormat();

    /**
     * @return The size of the AdView format ad, or size with (width: 0, height: 0) otherwise. Guaranteed not to be null.
     */
    AppLovinSdkUtils.Size getSize();

    /**
     * @return Ad unit id for which this ad was loaded. Guaranteed not to be null.
     */
    String getAdUnitId();

    /**
     * @return The ad network for which this ad was loaded from. Guaranteed not to be null.
     */
    String getNetworkName();

    /**
     * @return The ad network placement for which this ad was loaded from. Guaranteed not to be null.
     */
    String getNetworkPlacement();

    /**
     * @return The ad placement which was set for this ad. May be null.
     */
    String getPlacement();

    /**
     * @return The underlying waterfall of ad responses.
     */
    MaxAdWaterfallInfo getWaterfall();

    /**
     * The creative id tied to the ad, if any. You can report creative issues to the corresponding ad network using this id.
     * <p>
     * It may not be available until {@code MaxAdListener#onAdDisplayed()} is called.
     *
     * @return The ad's creative ID, if available. May be null.
     */
    @Nullable
    String getCreativeId();

    /**
     * The Ad Review creative id tied to the ad, if any. You can report creative issues to our Ad review team using this id.
     * <p>
     * It may not be available until {@code MaxAdListener#onAdDisplayed()} is called.
     *
     * @return The ad's Ad Review creative ID, if available. May be null.
     */
    @Nullable
    String getAdReviewCreativeId();

    /**
     * The revenue amount tied to the ad.
     *
     * @return The ad’s revenue amount. In the case where no revenue amount exists, or it is not available yet, will return a value of 0.
     */
    double getRevenue();

    /**
     * The precision of the revenue value for this ad.
     *
     * @return Possible values are:
     * - "publisher_defined" - If the revenue is the price assigned to the line item by the publisher.
     * - "exact" - If the revenue is the resulting price of a real-time auction.
     * - "estimated" - If the revenue is the price obtained by auto-CPM.
     * - "undefined" - If we do not have permission from the ad network to share impression-level data.
     */
    String getRevenuePrecision();

    /**
     * @return The DSP network that provided the loaded ad when the ad is served through AppLovin Exchange.
     */
    @Nullable
    String getDspName();

    /**
     * @return The DSP network ID that provided the loaded ad when the ad is served through AppLovin Exchange.
     */
    @Nullable
    String getDspId();

    /**
     * Get an arbitrary ad value for a given key.
     *
     * @param key The designated key to retrieve desired value for.
     *
     * @return An arbitrary ad value for a given key - or null if does not exist.
     */
    String getAdValue(final String key);

    /**
     * Get an arbitrary ad value for a given key.
     *
     * @param key          The designated key to retrieve desired value for.
     * @param defaultValue The default value to return if the desired value for does not exist or is null.
     *
     * @return An arbitrary ad value for a given key - or the default value if does not exist.
     */
    String getAdValue(final String key, final String defaultValue);

    /**
     * For Native ads only. Get an instance of the {@link MaxNativeAd} that is used to render the native ad view.
     *
     * @return The native ad containing the assets used to render the native ad view.
     */
    @Nullable
    MaxNativeAd getNativeAd();
}
