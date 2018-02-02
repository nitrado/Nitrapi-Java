package net.nitrado.api.customer;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;
import net.nitrado.api.common.Value;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * Details of the current customer.
 */
public class Customer {

    private transient Nitrapi api;

    @SerializedName("user_id")
    private Integer userId;
    private String username;
    private Boolean activated;
    private String timezone;
    private String email;
    private Integer credit;
    private String currency;
    private GregorianCalendar registered;
    private String language;
    private String avatar;
    private Boolean donations;
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
        private Boolean verified;

        /**
         * Returns phonenumber of the user.
         *
         * @return phonenumber of the user
         */
        @Nullable
        public String getNumber() {
            return number;
        }

        /**
         * Returns countryCode.
         *
         * @return countryCode
         */
        @Nullable
        public String getCountryCode() {
            return countryCode;
        }

        /**
         * Returns true if this number is verified.
         *
         * @return true if this number is verified
         */
        @Nullable
        public Boolean isVerified() {
            return verified;
        }
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
    @NotNull
    public Integer getUserId() {
        return userId;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    @NotNull
    public String getUsername() {
        return username;
    }

    /**
     * Returns true if the user is activated.
     *
     * @return true if the user is activated
     */
    @Nullable
    public Boolean isActivated() {
        return activated;
    }

    /**
     * Returns the timezone of this user.
     *
     * @return the timezone of this user
     */
    @Nullable
    public String getTimezone() {
        return timezone;
    }

    /**
     * Returns the email address of this user.
     *
     * @return the email address of this user
     */
    @Nullable
    public String getEmail() {
        return email;
    }

    /**
     * Returns the credit of this user.
     *
     * @return the credit of this user
     */
    @Nullable
    public Integer getCredit() {
        return credit;
    }

    /**
     * Returns the currency the user paid.
     *
     * @return the currency the user paid
     */
    @Nullable
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns the date the user registered.
     *
     * @return the date the user registered
     */
    @Nullable
    public GregorianCalendar getRegistered() {
        return registered;
    }

    /**
     * Returns language.
     *
     * @return language
     */
    @Nullable
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the url of the avatar.
     *
     * @return the url of the avatar
     */
    @Nullable
    public String getAvatar() {
        return avatar;
    }

    /**
     * Returns true if donations are enabled.
     *
     * @return true if donations are enabled
     */
    @Nullable
    public Boolean isDonations() {
        return donations;
    }

    /**
     * Returns phone.
     *
     * @return phone
     */
    @Nullable
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns activated two factor authentication methods.
     *
     * @return activated two factor authentication methods
     */
    @Nullable
    public String[] getTwoFactor() {
        return twoFactor;
    }

    /**
     * Returns personal details of the user.
     *
     * @return personal details of the user
     */
    @Nullable
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
    public AccountOverview getAccountOverview(@NotNull Integer year, @NotNull Integer month) throws NitrapiException {
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
    public String requestUserUpdateToken(@NotNull String password) throws NitrapiException {
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
     * @param token the update token of the current user
     */
    public void addPhoneNumber(@NotNull String number, @NotNull String token) throws NitrapiException {
        api.dataPost("user/phone", new Parameter[] {
            new Parameter("number", number),
            new Parameter("token", token)
        });
    }

    /**
     * Delete phone number.
     *
     * @param token the update token of the current user
     */
    public void deletePhoneNumber(@NotNull String token) throws NitrapiException {
        api.dataDelete("user/phone", new Parameter[] {
            new Parameter("token", token)
        });
    }

    /**
     * Verify phone number.
     *
     * @param code Verification code from SMS
     */
    public void verifyPhoneNumber(@NotNull String code) throws NitrapiException {
        api.dataPost("user/phone/verify", new Parameter[] {
            new Parameter("code", code)
        });
    }

    /**
     * Updates the timezone of the user.
     *
     * @param timezone new timezone for the user
     * @param token the update token of the current user
     */
    public void updateTimezone(@NotNull String timezone, @NotNull String token) throws NitrapiException {
        api.dataPost("user/", new Parameter[] {
            new Parameter("timezone", timezone),
            new Parameter("token", token)
        });
    }

    /**
     * Updates the profile information.
     *
     * @param token the update token of the current user
     * @param name name
     * @param street street
     * @param postcode postcode
     * @param city city
     * @param country country
     * @param state state
     */
    public void updateProfile(@NotNull String token, @Nullable String name, @Nullable String street, @Nullable String postcode, @Nullable String city, @Nullable String country, @Nullable String state) throws NitrapiException {
        api.dataPost("user/", new Parameter[] {
            new Parameter("token", token),
            new Parameter("profile[name]", name),
            new Parameter("profile[street]", street),
            new Parameter("profile[postcode]", postcode),
            new Parameter("profile[city]", city),
            new Parameter("profile[country]", country),
            new Parameter("profile[state]", state)
        });
    }

    /**
     * Updates the password.
     *
     * @param password the new password
     * @param token The update token of the current user
     */
    public void updatePassword(@NotNull String password, @NotNull String token) throws NitrapiException {
        api.dataPost("user/", new Parameter[] {
            new Parameter("password", password),
            new Parameter("token", token)
        });
    }

    /**
     * Updates the donation setting.
     *
     * @param donations true if donations can be received
     * @param token The update token of the current user
     */
    public void updateDonations(@NotNull Boolean donations, @NotNull String token) throws NitrapiException {
        api.dataPost("user/", new Parameter[] {
            new Parameter("donations", donations),
            new Parameter("token", token)
        });
    }
}
