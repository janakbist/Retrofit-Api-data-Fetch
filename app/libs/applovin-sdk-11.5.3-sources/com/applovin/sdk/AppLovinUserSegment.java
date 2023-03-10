package com.applovin.sdk;

/**
 * Created by Thomas So on October 31 2020
 * <p>
 * User segments  allow us to serve ads using custom-defined rules based on which segment the user is in. For now, we only support a custom string 32 alphanumeric characters or less as the user segment.
 */
public interface AppLovinUserSegment
{
    /**
     * Custom segment name with 32 alphanumeric characters or less defined in your AppLovin dashboard.
     *
     * @param name Custom segment name with 32 alphanumeric characters or less defined in your AppLovin dashboard. Can be null.
     */
    void setName(final String name);

    /**
     * @return The custom segment name that is currently set.
     */
    String getName();
}
