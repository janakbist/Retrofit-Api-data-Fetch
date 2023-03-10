package com.applovin.sdk;

import java.util.Map;

/**
 * This interface defines a listener intended to be notified when a user watches a rewarded video, and the SDK has received a response from the AppLovin server either validating or rejecting their virtual currency reward.
 */

public interface AppLovinAdRewardListener
{
    /**
     * If you are using reward validation for incentivized videos, this method will be invoked if we contacted AppLovin successfully. This means that we will be pinging your currency endpoint shortly, so you may wish to refresh the user's coins from your server.
     *
     * @param ad       An ad for which a validation request was submitted.
     * @param response Any response extras sent down by AppLovin. Typically, this includes the keys "currency" and "amount", which point to Strings containing the name and amount of the virtual currency to be awarded.
     */
    void userRewardVerified(final AppLovinAd ad, final Map<String, String> response);

    /**
     * This method will be invoked if the user has already received the maximum allocated rewards for the day.
     *
     * @param ad       An ad for which a validation request was submitted.
     * @param response Any response extras sent down by AppLovin.
     */
    void userOverQuota(final AppLovinAd ad, final Map<String, String> response);

    /**
     * This method will be invoked if the user's reward was detected as fraudulent and not awarded.
     *
     * @param ad       An ad for which a validation request was submitted.
     * @param response Any response extras sent down by AppLovin.
     */
    void userRewardRejected(final AppLovinAd ad, final Map<String, String> response);

    /**
     * This method will be invoked if we were unable to contact AppLovin, therefore no ping will be heading to your server.
     *
     * @param ad        An ad for which a validation request was submitted.
     * @param errorCode An error code indicating the cause of failure. Common error codes are defined in {@link com.applovin.sdk.AppLovinErrorCodes}.
     */
    void validationRequestFailed(final AppLovinAd ad, final int errorCode);
}
