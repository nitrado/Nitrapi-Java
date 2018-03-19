package net.nitrado.api.services.cloudservers;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns name.
     *
     * @return name
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * Returns createdAt.
     *
     * @return createdAt
     */
    @Nullable
    public GregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns tODO enum.
     *
     * @return tODO enum
     */
    @Nullable
    public String getType() {
        return type;
    }

    /**
     * Returns setId.
     *
     * @return setId
     */
    @Nullable
    public String getSetId() {
        return setId;
    }

    /**
     * Returns tODO enum.
     *
     * @return tODO enum
     */
    @Nullable
    public String getStatus() {
        return status;
    }
}
