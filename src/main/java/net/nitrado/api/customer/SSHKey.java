package net.nitrado.api.customer;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a SSHKey.
 */
public class SSHKey {

    private Integer id;
    private String type;
    @SerializedName("public_key")
    private String publicKey;
    private String comment;
    private Boolean enabled;

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
     * Returns type.
     *
     * @return type
     */
    @Nullable
    public String getType() {
        return type;
    }

    /**
     * Returns publicKey.
     *
     * @return publicKey
     */
    @Nullable
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Returns comment.
     *
     * @return comment
     */
    @Nullable
    public String getComment() {
        return comment;
    }

    /**
     * Returns enabled.
     *
     * @return enabled
     */
    @Nullable
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the full SSH public key.
     * @return the full SSH public key
     */
    public String getFullPublicKey() throws NitrapiException {
        return type + " "  + publicKey + " " + comment;
    }
}
