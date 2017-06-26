package net.nitrado.api.services.cloudservers;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
    public Map<GregorianCalendar, Float> getDatapoints() {
        return datapoints;
    }
}
