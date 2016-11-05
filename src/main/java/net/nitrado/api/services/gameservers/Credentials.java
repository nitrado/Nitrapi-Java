package net.nitrado.api.services.gameservers;

/**
 * Credentials for a MySQL or FTP-Service.
 */
public class Credentials {
    private String hostname;
    private int port;
    private String username;
    private String password;
    private String database;

    /**
     * Returns the hostname.
     *
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Returns the port.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the database for MySQL-databases.
     *
     * @return the database
     */
    public String getDatabase() {
        return database;
    }
}
