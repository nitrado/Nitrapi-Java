package net.nitrado.api.services.gameservers.pluginsystem;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.services.gameservers.Credentials;

import java.util.GregorianCalendar;

/**
 * Information about the Plugin system.
 */
public class PluginSystemInfo {
    private String username;

    /**
     * Status of the plugin system
     */
    public enum Status {
        /**
         * Plugin system is stopping.
         */
        @SerializedName("do_stop")
        STOPPING,
        /**
         * Plugin system is restarting.
         */
        @SerializedName("do_restart")
        RESTARTING,
        /**
         * Plugin system is installing.
         */
        @SerializedName("do_install")
        INSTALLING,
        /**
         * Plugin system is uninstalling.
         */
        @SerializedName("do_uninstall")
        UNINSTALLING,
        /**
         * Plugin system is started.
         */
        @SerializedName("started")
        STARTED,
        /**
         * Plugin system is stopped.
         */
        @SerializedName("stopped")
        STOPPED,
        /**
         * Plugin system has an error.
         */
        @SerializedName("error")
        ERROR
    }

    private Status status;
    private String hostname;
    private String ip;
    private int port;
    @SerializedName("installed_at")
    private GregorianCalendar installedAt;
    private String password;
    private Credentials database;

    /**
     * Returns the status of the plugin system.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the hostname of the plugin system.
     *
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Returns the ip of the plugin system.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Returns the port of the plugin system.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns the date the plugin system was installed.
     *
     * @return the install date
     */
    public GregorianCalendar getInstalledAt() {
        return installedAt;
    }

    /**
     * Returns the username for the plugin system.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password for the plugin system.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the database of the plugin system.
     *
     * @return the database
     */
    public Credentials getDatabase() {
        return database;
    }
}
