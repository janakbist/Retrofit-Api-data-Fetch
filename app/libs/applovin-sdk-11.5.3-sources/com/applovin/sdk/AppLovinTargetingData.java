package com.applovin.sdk;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * This class allows you to provide user or app data that will improve how we target ads.
 *
 * @author Basil Shikin
 */
public interface AppLovinTargetingData
{
    /**
     * Set the year of birth of the user.
     * Set this property to {@code null} to clear this value.
     */
    void setYearOfBirth(@Nullable final Integer yearOfBirth);

    /**
     * @return the year of birth of the user.
     */
    @Nullable
    Integer getYearOfBirth();

    /**
     * Set the gender of user.
     * Set this property to {@link AppLovinGender#UNKNOWN} or {@code null} to clear this value.
     */
    void setGender(@Nullable final AppLovinGender gender);

    /**
     * @return the gender of user.
     */
    @Nullable
    AppLovinGender getGender();

    /**
     * Set the maximum ad content rating shown the user.
     * Set this property to {@link AppLovinAdContentRating#NONE} or {@code null} to clear this value.
     */
    void setMaximumAdContentRating(@Nullable final AppLovinAdContentRating maximumAdContentRating);

    /**
     * @return the maximum ad content rating shown the user.
     */
    @Nullable
    AppLovinAdContentRating getMaximumAdContentRating();

    /**
     * Set the email of the user.
     * Set this property to {@code null} to clear this value.
     */
    void setEmail(@Nullable final String email);

    /**
     * @return the email of the user.
     */
    @Nullable
    String getEmail();

    /**
     * Set the phone number of the user. Do not include the country calling code.
     * Set this property to {@code null} to clear this value.
     */
    void setPhoneNumber(@Nullable final String phoneNumber);

    /**
     * @return the phone number of the user.
     */
    @Nullable
    String getPhoneNumber();

    /**
     * Set the keywords describing the application.
     * Set this property to {@code null} to clear this value.
     */
    void setKeywords(@Nullable final List<String> keywords);

    /**
     * @return keywords describing the application
     */
    @Nullable
    List<String> getKeywords();

    /**
     * Set the interests of the user.
     * Set this property to {@code null} to clear this value.
     */
    void setInterests(@Nullable final List<String> interests);

    /**
     * @return the interests of the user.
     */
    @Nullable
    List<String> getInterests();

    /**
     * Clear all saved data from this class.
     */
    void clearAll();
}

