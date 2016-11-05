package net.nitrado.api.services.gameservers.minecraft;

/**
 * Information about a Minecraft version.
 */
public class Version {
    private String version;
    private String name;
    private String md5;
    private boolean installed;

    /**
     * Returns the version number.
     *
     * @return the version number
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns a long version name.
     *
     * @return a name for the version
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the md5-hash needed to install this version
     *
     * @return the md5-hash of this version
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Returns true, if this is the currently installed version.
     *
     * @return true, if this is the currently installed version
     */
    public boolean isInstalled() {
        return installed;
    }

    @Override
    public String toString() {
        return name;
    }
}
