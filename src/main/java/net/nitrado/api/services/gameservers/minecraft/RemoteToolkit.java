package net.nitrado.api.services.gameservers.minecraft;

/**
 * Information about the remote toolkit.
 */
public class RemoteToolkit {
    private boolean enabled;
    private String username;
    private String password;

    /**
     * Returns true if the remote toolkit is enabled.
     *
     * @return true if the remote toolkit is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the username for remote toolkit.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password for remote toolkit.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
