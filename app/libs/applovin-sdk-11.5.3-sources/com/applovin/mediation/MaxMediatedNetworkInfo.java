package com.applovin.mediation;

/**
 * Represents information for a mediated network.
 */
public interface MaxMediatedNetworkInfo
{
    /**
     * @return The name of the mediated network.
     */
    String getName();

    /**
     * @return The class name of the adapter for the mediated network.
     */
    String getAdapterClassName();

    /**
     * @return The version of the adapter for the mediated network.
     */
    String getAdapterVersion();

    /**
     * @return The version of the mediated network's SDK.
     */
    String getSdkVersion();
}
