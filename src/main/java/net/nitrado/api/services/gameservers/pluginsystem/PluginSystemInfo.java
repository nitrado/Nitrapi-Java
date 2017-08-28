package net.nitrado.api.services.gameservers.pluginsystem;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.Value;
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
    public static class Status extends Value {
        public Status(String value) {
            super(value);
        }
        /**
         * Plugin system is stopping.
         */
        public static final Status STOPPING = new Status("do_stop");
        /**
         * Plugin system is restarting.
         */
        public static final Status RESTARTING = new Status("do_restart");
        /**
         * Plugin system is installing.
         */
        public static final Status INSTALLING = new Status("do_install");
        /**
         * Plugin system is uninstalling.
         */
        public static final Status UNINSTALLING = new Status("do_uninstall");
        /**
         * Plugin system is started.
         */
        public static final Status STARTED = new Status("started");
        /**
         * Plugin system is stopped.
         */
        public static final Status STOPPED = new Status("stopped");
        /**
         * Plugin system has an error.
         */
        public static final Status ERROR = new Status("error");
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
