package com.applovin.mediation;

import androidx.annotation.Nullable;

/**
 * Created by Thomas So on May 08 2021
 * <p>
 * This class encapsulates various data for MAX load and display errors.
 */
public interface MaxError
{
    /**
     * The error code for the error. Will be one of the codes listed in {@link MaxErrorCode}.
     */
    int getCode();

    /**
     * The error message for the error.
     */
    String getMessage();

    /**
     * The mediated network's error code for the error. Available for errors returned in {@code MaxAdViewListener#onAdDisplayFailed} only.
     */
    int getMediatedNetworkErrorCode();

    /**
     * The mediated network's error message for the error. Defaults to an empty string. Available for errors returned in {@code MaxAdViewListener#onAdDisplayFailed} only.
     */
    String getMediatedNetworkErrorMessage();

    /**
     * The underlying waterfall of ad responses.
     */
    @Nullable
    MaxAdWaterfallInfo getWaterfall();

    /**
     * @deprecated The ad load failure info string is deprecated and removed in a future SDK version. Please use {@code MaxError#getWaterfall} instead.
     */
    @Deprecated
    String getAdLoadFailureInfo();
}
