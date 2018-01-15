package net.nitrado.api;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an AccessToken.
 */
public class AccessToken {

    private User user;
    @SerializedName("expires_at")
    private Integer expiresAt;
    @SerializedName("valid_until")
    private GregorianCalendar validUntil;
    private String[] scopes;
    private Boolean employee;

    /**
     * This class represents an user.
     */
    public class User {
        private Integer id;
        private String username;

        /**
         * Returns id.
         *
         * @return id
         */
        @Nullable
        public Integer getId() {
            return id;
        }

        /**
         * Returns username.
         *
         * @return username
         */
        @Nullable
        public String getUsername() {
            return username;
        }
    }

    /**
     * Returns user.
     *
     * @return user
     */
    @Nullable
    public User getUser() {
        return user;
    }

    /**
     * Returns expiresAt.
     *
     * @return expiresAt
     */
    @Nullable
    public Integer getExpiresAt() {
        return expiresAt;
    }

    /**
     * Returns validUntil.
     *
     * @return validUntil
     */
    @Nullable
    public GregorianCalendar getValidUntil() {
        return validUntil;
    }

    /**
     * Returns scopes.
     *
     * @return scopes
     */
    @Nullable
    public String[] getScopes() {
        return scopes;
    }

    /**
     * Returns employee.
     *
     * @return employee
     */
    @Nullable
    public Boolean isEmployee() {
        return employee;
    }
}
