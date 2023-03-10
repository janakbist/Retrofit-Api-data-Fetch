package com.applovin.sdk;

import java.util.Map;

/**
 * Class containing constants for use with {@link AppLovinEventService#trackEvent(String, Map)}.
 */
public class AppLovinEventTypes
{

    /**
     * Event signifying that the user logged in to an existing account.
     * <p>
     * Suggested parameters: USER_ACCOUNT_IDENTIFIER. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_LOGGED_IN = "login";

    /**
     * Event signifying that the finished a registration flow and created a new account.
     * <p>
     * Suggested parameters: USER_ACCOUNT_IDENTIFIER. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_CREATED_ACCOUNT = "registration";

    /**
     * Event signifying that the user viewed a specific piece of content.
     * <p>
     * For views of saleable products, it is preferred you pass USER_VIEWED_PRODUCT.
     * <p>
     * Suggested parameters: CONTENT_IDENTIFIER. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_VIEWED_CONTENT = "content";

    /**
     * Event signifying that the user executed a search query.
     * <p>
     * Suggested parameters: SEARCH_QUERY. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_EXECUTED_SEARCH = "search";

    /**
     * Event signifying that the user completed a tutorial or introduction sequence.
     * <p>
     * Suggested parameters: None.
     */
    public static final String USER_COMPLETED_TUTORIAL = "tutorial";

    /**
     * Event signifying that the user completed a given level or game sequence.
     * <p>
     * Suggested parameters: COMPLETED_LEVEL. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_COMPLETED_LEVEL = "level";

    /**
     * Event signifying that the user completed (or "unlocked") a particular achievement.
     * <p>
     * Suggested parameters: COMPLETED_ACHIEVEMENT. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_COMPLETED_ACHIEVEMENT = "achievement";

    /**
     * Event signifying that the user spent virtual currency on an in-game purchase.
     * <p>
     * Suggested parameters: VIRTUAL_CURRENCY_AMOUNT. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_SPENT_VIRTUAL_CURRENCY = "vcpurchase";

    /**
     * Event signifying that the user viewed a specific piece of content.
     * <p>
     * For general content, e.g. not saleable products, it is preferred you pass USER_VIEWED_CONTENT.
     * <p>
     * Suggested parameters: PRODUCT_IDENTIFIER. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_VIEWED_PRODUCT = "product";

    /**
     * Event signifying that the user added a product/item to their shopping cart.
     * <p>
     * Suggested parameters: PRODUCT_IDENTIFIER. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_ADDED_ITEM_TO_CART = "cart";

    /**
     * Event signifying that the user added a product/item to their wishlist.
     * <p>
     * Suggested parameters: PRODUCT_IDENTIFIER. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_ADDED_ITEM_TO_WISHLIST = "wishlist";

    /**
     * Event signifying that the user provided payment information, such as a credit card number.
     * <p>
     * Suggested parameters: None. Please DO NOT pass us any personally identifiable information (PII) or financial/payment information.
     */
    public static final String USER_PROVIDED_PAYMENT_INFORMATION = "payment_info";

    /**
     * Event signifying that the user began a check-out / purchase process.
     * <p>
     * Suggested parameters: PRODUCT_IDENTIFIER, REVENUE_AMOUNT and REVENUE_CURRENCY. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_BEGAN_CHECKOUT = "checkout_start";

    /**
     * Event signifying that the user completed a check-out / purchase.
     * <p>
     * Suggested parameters: CHECKOUT_TRANSACTION_IDENTIFIER, PRODUCT_IDENTIFIER, REVENUE_AMOUNT and REVENUE_CURRENCY. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_COMPLETED_CHECKOUT = "checkout";

    /**
     * Event signifying that the user completed an iTunes in-app purchase using StoreKit.
     * <p>
     * Note that this event implies an in-app content purchase; for purchases of general products completed using Apple Pay, use kALEventTypeUserCompletedCheckOut instead.
     * <p>
     * Suggested parameters: PRODUCT_IDENTIFIER. We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_COMPLETED_IN_APP_PURCHASE = "iap";

    /**
     * Event signifying that the user has created a reservation or other date-specific event.
     * <p>
     * Suggested parameters: PRODUCT_IDENTIFIER, RESERVATION_START_TIMESTAMP and RESERVATION_END_TIMESTAMP We recommend you populate these keys in the parameters map passed to {@link AppLovinEventService#trackEvent(String, Map)}.
     */
    public static final String USER_CREATED_RESERVATION = "reservation";

    /**
     * Event signifying that the user sent an invitation to use your app to a friend.
     * <p>
     * Suggested parameters: None.
     */
    public static final String USER_SENT_INVITATION = "invite";

    /**
     * Event signifying that the user shared a link or deep-link to some content within your app.
     * <p>
     * Suggested parameters: None.
     */
    public static final String USER_SHARED_LINK = "share";

}
