package com.applovin.sdk;

/**
 * Created by Thomas So on October 02 2018
 * <p>
 * Object containing various flags related to the SDK configuration.
 */
public interface AppLovinSdkConfiguration
{
    /**
     * Get the country code for this user. Returns an empty string if not available.
     */
    String getCountryCode();

    /**
     * @deprecated This API has been deprecated and will be removed in a future release.
     */
    @Deprecated
    enum ConsentDialogState
    {
        UNKNOWN,
        APPLIES,
        DOES_NOT_APPLY
    }

    /**
     * @deprecated This API has been deprecated and will be removed in a future release.
     */
    @Deprecated
    ConsentDialogState getConsentDialogState();
}
