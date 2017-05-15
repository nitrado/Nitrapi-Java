package net.nitrado.api.services.gameservers.customersettings;

import java.util.GregorianCalendar;

/**
 * A set of settings that is saved for later usage.
 */
public class CustomerSettingsSet {
    private int id;
    private String name;
    private String game;
    private CustomerSettings data;
    private GregorianCalendar datetime;

    /**
     * Returns the id of this config set.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of this config set.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the game this config set is specified for.
     *
     * @return the game
     */
    public String getGame() {
        return game;
    }

    /**
     * Returns the settings.
     *
     * @return the customer settings object
     */
    public CustomerSettings getSettings() {
        return data;
    }

    /**
     * Returns the date this configset was created.
     *
     * @return the creation date
     */
    public GregorianCalendar getDatetime() {
        return datetime;
    }

}
