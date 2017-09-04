package net.nitrado.api;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;

/**
 * This class represents an AccessToken.
 */
public class AccessToken {

    private User user;
    @SerializedName("expires_at")
    private int expiresAt;
    @SerializedName("valid_until")
    private GregorianCalendar validUntil;
    private String[] scopes;
    private boolean employee;

    /**
     * This class represents an user.
     */
    public class User {
        private int id;
        private String username;

        /**
         * Returns id.
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns username.
         *
         * @return username
         */
        public String getUsername() {
            return username;
        }
    }

    /**
     * Returns user.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns expiresAt.
     *
     * @return expiresAt
     */
    public int getExpiresAt() {
        return expiresAt;
    }

    /**
     * Returns validUntil.
     *
     * @return validUntil
     */
    public GregorianCalendar getValidUntil() {
        return validUntil;
    }

    /**
     * Returns scopes.
     *
     * @return scopes
     */
    public String[] getScopes() {
        return scopes;
    }

    /**
     * Returns employee.
     *
     * @return employee
     */
    public boolean isEmployee() {
        return employee;
    }
}
