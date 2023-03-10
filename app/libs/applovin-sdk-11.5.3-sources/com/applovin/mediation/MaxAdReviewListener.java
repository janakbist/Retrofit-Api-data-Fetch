package com.applovin.mediation;

/**
 * Created by Thomas So on March 17 2022
 * <p>
 * This interface defines a listener to be notified when the Ad Review SDK successfully generates a creative id.
 */
public interface MaxAdReviewListener
{
    /**
     * The SDK invokes this callback when the Ad Review SDK successfully generates a creative id.
     * <p>
     * The SDK invokes this callback on the UI thread.
     *
     * @param creativeId The Ad Review creative id tied to the ad, if any. You can report creative issues to our Ad review team using this id.
     * @param ad         The ad for which the ad review event was detected. Guaranteed not to be null.
     */
    void onCreativeIdGenerated(final String creativeId, final MaxAd ad);
}
