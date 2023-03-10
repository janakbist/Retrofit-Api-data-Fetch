package com.applovin.mediation;

import java.util.List;

/**
 * Created by Thomas So on November 04 2021
 * <p>
 * This class represents an ad waterfall, encapsulating various metadata such as total latency, underlying ad responses, etc.
 */
public interface MaxAdWaterfallInfo
{
    /**
     * The loaded ad, if any, for this waterfall.
     */
    MaxAd getLoadedAd();

    /**
     * The ad waterfall name.
     */
    String getName();

    /**
     * The ad waterfall test name.
     */
    String getTestName();

    /**
     * The list of @c MAAdapterResponseInfo info objects relating to each ad in the waterfall, ordered by their position.
     */
    List<MaxNetworkResponseInfo> getNetworkResponses();

    /**
     * The total latency in seconds for this waterfall to finish processing.
     */
    long getLatencyMillis();
}
