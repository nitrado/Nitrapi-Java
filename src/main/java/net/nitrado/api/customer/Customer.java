package net.nitrado.api.customer;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Details of the current customer.
 */
public class Customer {

    private transient Nitrapi api;

    @SerializedName("user_id")
    private int userId;
    private String username;
    private boolean activated;
    private String timezone;
    private String email;
    private int credit;
    private String currency;
    private GregorianCalendar registered;
    private String language;
    private String avatar;
    private Phone phone;
    @SerializedName("two_factor")
    private String[] twoFactor;
    private HashMap<String, String> profile;

    /**
     * This class represents a phone.
     */
    public class Phone {
        private String number;
        @SerializedName("country_code")
        private String countryCode;
        private boolean verified;

        /**
         * Returns phonenumber of the user.
         *
         * @return phonenumber of the user
         */
        public String getNumber() {
            return number;
        }

        /**
         * Returns countryCode.
         *
         * @return countryCode
         */
        public String getCountryCode() {
            return countryCode;
        }

        /**
         * Returns true if this number is verified.
         *
         * @return true if this number is verified
         */
        public boolean isVerified() {
            return verified;
        }
    }

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
     * Used internally.
     */
    public void init(Nitrapi api) {
        this.api = api;
    }

    /**
     * Returns the id of this user.
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
     * Returns true if the user is activated.
     *
     * @return true if the user is activated
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Returns the timezone of this user.
     *
     * @return the timezone of this user
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Returns the email address of this user.
     *
     * @return the email address of this user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the credit of this user.
     *
     * @return the credit of this user
     */
    public int getCredit() {
        return credit;
    }

    /**
     * Returns the currency the user paid.
     *
     * @return the currency the user paid
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns the date the user registered.
     *
     * @return the date the user registered
     */
    public GregorianCalendar getRegistered() {
        return registered;
    }

    /**
     * Returns language.
     *
     * @return language
     */
    public String getLanguage() {
        return language;
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
     * Returns phone.
     *
     * @return phone
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns activated two factor authentication methods.
     *
     * @return activated two factor authentication methods
     */
    public String[] getTwoFactor() {
        return twoFactor;
    }

    /**
     * Returns personal details of the user.
     *
     * @return personal details of the user
     */
    public HashMap<String, String> getProfile() {
        return profile;
    }

    /**
     * @return AccountOverview
     */
    public AccountOverview getAccountOverview() throws NitrapiException {
        JsonObject data = api.dataGet("user/account_overview", null);

        AccountOverview account_overview = api.fromJson(data.get("account_overview"), AccountOverview.class);
        return account_overview;
    }

    /**
     *
     * @param year year
     * @param month month
     * @return AccountOverview
     */
    public AccountOverview getAccountOverview(int year, int month) throws NitrapiException {
        JsonObject data = api.dataGet("user/account_overview", new Parameter[] {
                new Parameter("year", year),
                new Parameter("month", month)
        });

        AccountOverview account_overview = api.fromJson(data.get("account_overview"), AccountOverview.class);
        return account_overview;
    }

    /**
     * Request user update token.
     *
     * @param password The current user password.
     * @return String
     */
    public String requestUserUpdateToken(String password) throws NitrapiException {
        JsonObject data = api.dataPost("user/token", new Parameter[] {
                new Parameter("password", password)
        });

        String token = api.fromJson(data.get("token"), String.class);
        return token;
    }

    /**
     * Add phone number.
     *
     * @param number phone number with country code
     * @param token the current user update token
     */
    public void addPhoneNumber(String number, String token) throws NitrapiException {
        api.dataPost("user/phone", new Parameter[] {
                new Parameter("number", number),
                new Parameter("token", token)
        });
    }

    /**
     * Delete phone number.
     *
     * @param token the current user update token
     */
    public void deletePhoneNumber(String token) throws NitrapiException {
        api.dataDelete("user/phone", new Parameter[] {
                new Parameter("token", token)
        });
    }

    /**
     * Verify phone number.
     *
     * @param code Verification code from SMS
     */
    public void verifyPhoneNumber(String code) throws NitrapiException {
        api.dataPost("user/phone/verify", new Parameter[] {
                new Parameter("code", code)
        });
    }
}
