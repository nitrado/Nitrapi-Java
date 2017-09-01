package net.nitrado.api.customer;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Details of the current customer.
 */
public class Customer {
    private transient Nitrapi api;

    @SerializedName("user_id")
    private int userId;
    private String username;
    private String timezone;
    private String email;
    private int credit;
    private String currency; // TODO enum?
    private GregorianCalendar registered;
    private String language;
    private String avatar;
    private HashMap<String, String> profile;

    /**
     * Use Nitrapi.getCustomer() instead.
     *
     * @param api reference to the Nitrapi
     * @return the customer object
     */
    public static Customer newInstance(Nitrapi api) throws NitrapiException {
        JsonObject data = api.dataGet("user", null);
        Customer customer = api.fromJson(data.get("user"), Customer.class);
        customer.api = api;
        return customer;
    }

    /**
     * Returns the user id.
     *
     * @return the id of this user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the timezone of this user.
     *
     * @return the timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Returns the credit of the user.
     *
     * @return the credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * Returns the currency the user paid.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns email address of the user.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the date the user registered.
     *
     * @return the registry date
     */
    public GregorianCalendar getRegisteredDate() {
        return registered;
    }

    /**
     * Returns the url of the avatar.
     *
     * @return the url of the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Returns the personal details of the user.
     *
     * @return the personal details
     */
    public Map<String, String> getProfile() {
        return profile;
    }

    public AccountOverview getAccountOverview() throws NitrapiException {
        JsonObject data = api.dataGet("user/account_overview", null);
        return api.fromJson(data.get("account_overview"), AccountOverview.class);
    }

    public AccountOverview getAccountOverview(int year, int month) throws NitrapiException {
        JsonObject data = api.dataGet("user/account_overview", new Parameter[]{
                new Parameter("year", year),
                new Parameter("month", month)
        });
        return api.fromJson(data.get("account_overview"), AccountOverview.class);
    }
}
