package net.nitrado.api.customer;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;

/**
 * This class represents a SSHKeys.
 */
public class SSHKeys {

    private transient Nitrapi api;

    /**
     * Used internally.
     */
    public void init(Nitrapi api) {
        this.api = api;
    }


    private SSHKey[] keys;

    public class SSHKey {
        private int id;
        private String type;
        @SerializedName("public_key")
        private String publicKey;
        private String comment;
        private boolean enabled;

        /**
         * Returns id.
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns type.
         *
         * @return type
         */
        public String getType() {
            return type;
        }

        /**
         * Returns publicKey.
         *
         * @return publicKey
         */
        public String getPublicKey() {
            return publicKey;
        }

        public String getFullPublicKey() {
            return type + " " + publicKey + " " + comment;
        }

        /**
         * Returns comment.
         *
         * @return comment
         */
        public String getComment() {
            return comment;
        }

        /**
         * Returns enabled.
         *
         * @return enabled
         */
        public boolean isEnabled() {
            return enabled;
        }

    }


    /**
     * Returns keys.
     *
     * @return keys
     */
    public SSHKey[] getKeys() {
        return keys;
    }


    /**
     * Upload a SSH key.
     *
     * @param key
     */
    public void uploadKey(String key) throws NitrapiException {
        api.dataPost("user/ssh_keys/", new Parameter[]{
                new Parameter("key", key)
        });
    }

    /**
     * Updates a SSH public key.
     *
     * @param id
     * @param key
     * @param enabled
     */
    public void updateKey(int id, String key, boolean enabled) throws NitrapiException {
        api.dataPost("user/ssh_keys/" + id + "", new Parameter[]{
                new Parameter("key", key),
                new Parameter("enabled", enabled)
        });
    }

    /**
     * Deletes a SSH public key.
     *
     * @param id
     */
    public void deleteKey(int id) throws NitrapiException {
        api.dataDelete("user/ssh_keys/" + id + "", null);
    }


}
