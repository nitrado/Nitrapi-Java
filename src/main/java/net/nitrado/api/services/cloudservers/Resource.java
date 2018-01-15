package net.nitrado.api.services.cloudservers;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

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
    @Nullable
    public String getType() {
        return type;
    }

    /**
     * Returns datapoints.
     *
     * @return datapoints
     */
    @Nullable
    public HashMap<GregorianCalendar, Float> getDatapoints() {
        return datapoints;
    }
}
