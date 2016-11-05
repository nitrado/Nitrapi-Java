package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the system a gameserver is running on.
 */
public class Hostsystem {
    private String hostname;
    private String servername;

    /**
     * Status of the hostsystem.
     */
    public enum Status {
        /**
         * Hostsystem is online.
         */
        @SerializedName("online")
        ONLINE,
        /**
         * Hostsystem if offline.
         */
        @SerializedName("offline")
        OFFLINE
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
