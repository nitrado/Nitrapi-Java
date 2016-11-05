package net.nitrado.api.services.gameservers.minecraft;

/**
 * Settings of McMyAdmin
 */
public class McMyAdmin {
    private boolean enabled;
    private String url;
    private String username;
    private String password;
    private String language;

    /**
     * Returns true if McMyAdmin is enabled.
     *
     * @return true if McMyAdmin is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the url at which McMyAdmin is running.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the username for login into McMyAdmin.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password for login into McMyAdmin.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the language of McMyAdmin.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }
}
