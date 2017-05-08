package net.nitrado.api.services.cloudservers;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class represents a Backup.
 */
public class Backup {

    private String id;
    private String name;
    @SerializedName("created_at")
    private GregorianCalendar createdAt;
    private String type;
    @SerializedName("set_id")
    private String setId;
    private String status;

    /**
     * Returns id.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns createdAt.
     *
     * @return createdAt
     */
    public GregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * TODO enum
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns setId.
     *
     * @return setId
     */
    public String getSetId() {
        return setId;
    }

    /**
     * TODO enum
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }
}
