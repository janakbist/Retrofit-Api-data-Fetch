package com.applovin.mediation;

/**
 * This object represents a reward given to the user.
 */
public interface MaxReward
{
    /**
     * This constant represents a label that is used when a label is not given by the third-party network.
     */
    String DEFAULT_LABEL = "";

    /**
     * This constant represents a amount that is used when no amount is given by the third-party network.
     */
    int DEFAULT_AMOUNT = 0;

    /**
     * Get rewarded label.
     *
     * @return Rewarded label or {@link #DEFAULT_LABEL} if none specified.
     */
    String getLabel();

    /**
     * Get rewarded amount.
     *
     * @return Rewarded amount or {@link #DEFAULT_AMOUNT} if none specified.
     */
    int getAmount();
}
