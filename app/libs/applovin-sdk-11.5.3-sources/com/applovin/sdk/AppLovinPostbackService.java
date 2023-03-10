package com.applovin.sdk;

/**
 * Defines an AppLovin service which can be used to dispatch HTTP GET postbacks to arbitrary URLs.
 * <p>
 * While we provide this service primarily as a convenience for native ad tracking URLs, you are welcome to use it for any postbacks you need to dispatch. Postbacks dispatched from this service happen in a asynchronous task.
 */
public interface AppLovinPostbackService
{
    /**
     * Dispatch a postback to a given URL.
     *
     * @param targetUrl        URL to call via HTTP GET.
     * @param postbackListener Optional listener. May be null.
     */
    void dispatchPostbackAsync(final String targetUrl, final AppLovinPostbackListener postbackListener);
}
