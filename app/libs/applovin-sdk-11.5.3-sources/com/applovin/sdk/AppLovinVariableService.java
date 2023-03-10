package com.applovin.sdk;

import android.os.Bundle;

/**
 * Created by Thomas So on October 06 2018
 * <p>
 * This service allows for retrieval of variables pre-defined on AppLovin's dashboard.
 */
public interface AppLovinVariableService
{
    /**
     * Interface definition for a callback to be invoked when latest variables are retrieved from the server.
     *
     * @deprecated This API has been deprecated. Please use our SDK's initialization callback to retrieve variables instead.
     */
    interface OnVariablesUpdateListener
    {
        /**
         * Called when new variables are retrieved. The initial set of variables will be returned after the AppLovin SDK finishes initializing.
         *
         * @param variables The bundle containing the latest variables. These values may also be retrieved via the various getters in this class.
         *
         * @deprecated This API has been deprecated. Please use our SDK's initialization callback to retrieve variables instead.
         */
        void onVariablesUpdate(Bundle variables);
    }

    /**
     * Register a callback to be invoked when the latest variables are retrieved from the server.
     * The initial set of variables will be returned after the AppLovin SDK finishes initializing.
     *
     * @param listener The callback that will be run.
     *
     * @deprecated This API has been deprecated. Please use our SDK's initialization callback to retrieve variables instead.
     */
    @Deprecated
    void setOnVariablesUpdateListener(OnVariablesUpdateListener listener);

    /**
     * Explicitly retrieve the latest variables to be returned via the listener.
     * Please make sure that the listener has been set via {@link AppLovinVariableService#setOnVariablesUpdateListener}.
     *
     * @deprecated This API has been deprecated. Please use our SDK's initialization callback to retrieve variables instead.
     */
    @Deprecated
    void loadVariables();

    /**
     * Returns the variable value associated with the given key, or false if
     * no mapping of the desired type exists for the given key.
     *
     * @param name The variable name to retrieve the value for.
     *
     * @return The variable value to be used for the given key, or null if no value was found.
     */
    boolean getBoolean(String name);

    /**
     * Returns the variable value associated with the given key, or the specified default value if
     * no mapping of the desired type exists for the given key.
     *
     * @param name         The variable name to retrieve the value for.
     * @param defaultValue The value to be returned if the variable name does not exist.
     *
     * @return The variable value to be used for the given key, or the default value if no value was found.
     */
    boolean getBoolean(String name, boolean defaultValue);

    /**
     * Returns the variable value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param name The variable name to retrieve the value for.
     *
     * @return The variable value to be used for the given key, or null if no value was found.
     */
    String getString(String name);

    /**
     * Returns the variable value associated with the given key, or the specified default value if
     * no mapping of the desired type exists for the given key.
     *
     * @param name         The variable name to retrieve the value for.
     * @param defaultValue The value to be returned if the variable name does not exist.
     *
     * @return The variable value to be used for the given key, or the default value if no value was found.
     */
    String getString(String name, String defaultValue);
}
