package com.applovin.mediation;

/**
 * This interface defines a listener intended to be notified when a user watches a rewarded video and whether a reward was granted or rejected.
 */
public interface MaxRewardedAdListener
        extends MaxAdListener
{
    /**
     * This method will be invoked when rewarded video has started.
     *
     * @param ad An ad for which the video was started. Guaranteed not to be null.
     */
    void onRewardedVideoStarted(final MaxAd ad);

    /**
     * This method will be invoked when rewarded video has completed.
     *
     * @param ad An ad for which the video was completed. Guaranteed not to be null.
     */
    void onRewardedVideoCompleted(final MaxAd ad);

    /**
     * This method will be invoked when a user should be granted a reward.
     *
     * @param ad     Ad for which reward ad was rewarded for. Guaranteed not to be null.
     * @param reward The reward to be granted to the user. Guaranteed not to be null.
     */
    void onUserRewarded(final MaxAd ad, final MaxReward reward);
}
