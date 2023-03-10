package com.applovin.sdk;

import android.app.Activity;

/**
 * Created by Thomas So on October 02 2018
 * <p>
 * Service object for performing user-related tasks.
 */
public interface AppLovinUserService
{
    /**
     * Interface used to allow the presented of the consent dialog to run some code when the dialog is dismissed.
     */
    interface OnConsentDialogDismissListener
    {
        /**
         * This method will be invoked when the consent dialog is dismissed.
         */
        void onDismiss();
    }

    /**
     * Preload the user consent dialog. You have the option to call this so the consent dialog appears
     * more quickly when you call {@link AppLovinUserService#showConsentDialog(Activity, OnConsentDialogDismissListener)}.
     */
    void preloadConsentDialog();

    /**
     * Show the user consent dialog to the user using one from AppLovin's SDK. You should check that you actually need to show the
     * consent dialog by checking {@link AppLovinSdkConfiguration#getConsentDialogState()} in the callback function of {@link AppLovinSdk#initializeSdk(AppLovinSdk.SdkInitializationListener)}}.
     */
    void showConsentDialog(final Activity parentActivity, final OnConsentDialogDismissListener listener);
}
