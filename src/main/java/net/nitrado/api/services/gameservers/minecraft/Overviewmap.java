package net.nitrado.api.services.gameservers.minecraft;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;

/**
 * Information about the overviewmap.
 */
public class Overviewmap {
    private boolean enabled;
    private String url;
    private boolean signs;
    private String[] ipsonly;
    private boolean reset;
    @SerializedName("last_reset")
    private GregorianCalendar lastReset;
    @SerializedName("last_enable")
    private GregorianCalendar lastEnable;
    private GregorianCalendar modified;

    /**
     * Returns true if the overviewmap is enabled.
     *
     * @return true if the overviewmap is enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the url of the overviewmap.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns true if signs are rendered on the overviewmap.
     *
     * @return true if signs are rendered on the overviewmap
     */
    public boolean isSigns() {
        return signs;
    }

    /**
     * Returns the IPs that can view the overviewmap.
     *
     * @return a list of IPs
     */
    public String[] getIPsonly() {
        return ipsonly;
    }

    /**
     * Returns true if the overviewmap will be deleted in the next seconds.
     *
     * @return true if the overviewmap will be deleted in the next seconds
     */
    public boolean isReset() {
        return reset;
    }

    /**
     * Returns the last time the overviewmap was reset.
     * <p>
     * Reset is only possible every 3 days.
     *
     * @return the last time the overviewmap was reset
     */
    public GregorianCalendar getLastReset() {
        return lastReset;
    }

    /**
     * Returns the last time the overviewmap was activated.
     * <p>
     * This status can only be changed every 24 hours.
     *
     * @return the last time the overviewmap was activated
     */
    public GregorianCalendar getLastEnable() {
        return lastEnable;
    }

    /**
     * Returns the last time the overviewmap was rendered.
     *
     * @return the last time the overviewmap was rendered
     */
    public GregorianCalendar getModified() {
        return modified;
    }
}
