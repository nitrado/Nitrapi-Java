package net.nitrado.api.services.cloudservers;

import java.util.HashMap;
import java.util.Date;

public class Resource {
    private String type;
    private HashMap<Date, Float> datapoints; // TODO


    public String getType() {
        return type;
    }

    public HashMap<Date, Float> getDatapoints() {
        return datapoints;
    }
}
