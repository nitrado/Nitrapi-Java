package net.nitrado.api.services.cloudservers;

import com.google.gson.annotations.SerializedName;
import com.sun.deploy.security.ValidationState;

import java.util.Date;

public class Backup {

    // TODO
    public enum Type {
        @SerializedName("manual")
        MANUAL
    }

    // TODO
    public enum Status {
        @SerializedName("available")
        AVAILABLE
    }

    private String id;
    private String name;
    private Date createdAt;
    private Type type;
    private String setId;
    private Status status;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Type getType() {
        return type;
    }

    // TODO
    public String getSetId() {
        return setId;
    }

    public Status getStatus() {
        return status;
    }
}
