package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.Value;

/**
 * This class represents the system a gameserver is running on.
 */
public class Hostsystem {
    private String hostname;
    private String servername;

    /**
     * Status of the hostsystem.
     */
    public static class Status extends Value {
        public Status(String value) {
            super(value);
        }
        /**
         * Hostsystem is online.
         */
        public static final Status ONLINE = new Status("online");
        /**
         * Hostsystem if offline.
         */
        public static final Status OFFLINE = new Status("offline");
    }

    private Status status;

    /**
     * Returns the name of the host system.
     *
     * @return the name of the host system
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Returns the name of the server.
     *
     * @return the name of the server
     */
    public String getServername() {
        return servername;
    }

    /**
     * Returns the status of the host system.
     *
     * @return the status of the host system
     */
    public Status getStatus() {
        return status;
    }
}
