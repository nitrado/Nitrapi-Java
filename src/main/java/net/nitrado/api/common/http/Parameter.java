package net.nitrado.api.common.http;

import net.nitrado.api.common.Value;

import java.util.Map;
import java.util.Set;

/**
 * A key-value Parameter for a HTTP-Request.
 */
public class Parameter {
    private String key;
    private String value;

    // In case of hash maps we have sub parameters
    private Parameter[] subParameters;

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
        this.value = value + "";
    }

    public Parameter(String key, Integer value) {
        this.key = key;
        if (value != null) {
            this.value = value.toString();
        }
    }

    public Parameter(String key, Value value) {
        this.key = key;
        this.value = value.getValue();
    }

    public Parameter(String key, boolean value) {
        this.key = key;
        this.value = value ? "true" : "false";
    }

    public Parameter(String key, Map<String, String> value) {
        this.key = null;
        this.value = null;
        Set<String> keys = value.keySet();
        subParameters = new Parameter[keys.size()];
        int i = 0;
        for (String valueKey : keys) {
            subParameters[i] = new Parameter(key + "[" + valueKey + "]", value.get(valueKey));
            i++;
        }

    }

    /**
     * Returns the key.
     * Null, if this parameter has sub parameters
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

    public Parameter[] getSubParameters() {
        return subParameters;
    }
}
