package net.nitrado.api.services.cloudservers;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class represents a Resource.
 */
public class Resource {

    private String type;
    private HashMap<GregorianCalendar, Float> datapoints;

    /**
     * Returns type.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns datapoints.
     *
     * @return datapoints
     */
    public HashMap<GregorianCalendar, Float> getDatapoints() {
        return datapoints;
    }
}
