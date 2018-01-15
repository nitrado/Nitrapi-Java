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
 * This class represents a SSHKeys.
 */
public class SSHKeys {

    private transient Nitrapi api;

    private SSHKey[] keys;

    /**
     * Used internally.
     */
    public void init(Nitrapi api) {
        this.api = api;
    }

    /**
     * Returns keys.
     *
     * @return keys
     */
    @Nullable
    public SSHKey[] getKeys() {
        return keys;
    }

    /**
     * Upload a SSH key.
     *
     * @param key key
     */
    public void uploadKey(String key) throws NitrapiException {
        api.dataPost("user/ssh_keys/", new Parameter[] {
            new Parameter("key", key)
        });
    }

    /**
     * Updates a SSH public key.
     *
     * @param id id
     * @param key key
     * @param enabled enabled
     */
    public void updateKey(Integer id, String key, Boolean enabled) throws NitrapiException {
        api.dataPost("user/ssh_keys/" + id + "", new Parameter[] {
            new Parameter("key", key),
            new Parameter("enabled", enabled)
        });
    }

    /**
     * Deletes a SSH public key.
     *
     * @param id id
     */
    public void deleteKey(Integer id) throws NitrapiException {
        api.dataDelete("user/ssh_keys/" + id + "", null);
    }
}
