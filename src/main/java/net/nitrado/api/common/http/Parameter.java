package net.nitrado.api.common.http;

/**
 * A key-value Parameter for a HTTP-Request.
 */
public class Parameter {
    private String key;
    private String value;

    /**
     * Create a new key-value Parameter
     *
     * @param key   the key
     * @param value the value
     */
    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public Parameter(String key, int value) {
        this.key = key;
        this.value = value+"";
    }

    /**
     * Returns the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
