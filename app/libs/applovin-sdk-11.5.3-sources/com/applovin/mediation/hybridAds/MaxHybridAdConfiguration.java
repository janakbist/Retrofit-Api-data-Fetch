package com.applovin.mediation.hybridAds;

import android.graphics.Color;

import com.applovin.impl.sdk.utils.JsonUtils;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import lombok.val;

public class MaxHybridAdConfiguration
{
    private final JSONObject configuration;

    public MaxHybridAdConfiguration(@Nullable final JSONObject configuration)
    {
        this.configuration = ( configuration != null ) ? configuration : new JSONObject();
    }

    /**
     * The background color for the hybrid fullscreen ad.
     */
    public int getBackgroundColor()
    {
        val colorString = JsonUtils.getString( configuration, "background_color", null );
        return ( colorString != null ) ? Color.parseColor( colorString ) : Color.BLACK;
    }

    /**
     * The close button margin from the top of the screen.
     */
    public int getCloseButtonTopMargin()
    {
        return JsonUtils.getInt( configuration, "close_button_top_margin", 20 );
    }

    /**
     * The close button margin from the side of the screen.
     */
    public int getCloseButtonHorizontalMargin()
    {
        return JsonUtils.getInt( configuration, "close_button_h_margin", 5 );
    }

    /**
     * The size of the close button, excluding the extended touch area.
     * <p>
     * Note: Unlike iOS this determines the size of the "x", so it is smaller and is set to our {@link com.applovin.impl.adview.CloseButton#SIZE}.
     */
    public int getCloseButtonSize()
    {
        return JsonUtils.getInt( configuration, "close_button_size", 30 );
    }

    /**
     * The size of which extended from the close button, should still be interactable.
     */
    public int getCloseButtonExtendedTouchAreaSize()
    {
        return JsonUtils.getInt( configuration, "close_button_extended_touch_area_size", 10 );
    }

    /**
     * The delay, in milliseconds, before showing the close button.
     *
     * NOTE: We intentionally use milliseconds on Android since the value may get rounded for long type values.
     */
    public long getCloseButtonDelayMillis()
    {
        return JsonUtils.getLong( configuration, "close_button_delay_ms", 3000 );
    }
}
