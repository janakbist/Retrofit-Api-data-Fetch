package com.applovin.sdk;

/**
 * Defines a listener which will be notified upon completion of a postback requested via {@link AppLovinPostbackService}.
 */
public interface AppLovinPostbackListener
{
    /**
     * Indicates that a postback dispatched to a given URL completed successfully.
     * <p>
     * We define success as having received a 2XX response code from the remote endpoint.
     *
     * @param url URL which was notified.
     */
    void onPostbackSuccess(final String url);

    /**
     * Indicates that a postback dispatched to a given URL has failed.
     * <p>
     * We define failed as having received a response code outside the 2XX range, or having been unable to establish a connection.
     *
     * @param url       URL which was notified.
     * @param errorCode HTTP status code received, if any; otherwise a negative constant.
     */
    void onPostbackFailure(final String url, final int errorCode);
}
