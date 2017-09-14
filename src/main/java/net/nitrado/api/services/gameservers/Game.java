package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a game.
 */
public class Game {
    private class Icons {
        private String x16;
        private String x32;
        private String x64;
        @SerializedName("x120")
        private String x128;
        private String x256;
    }

    private int id;
    @SerializedName("steam_id")
    private int steamId;
    @SerializedName("has_steam_game")
    private String hasSteamGame;
    private String name;
    @SerializedName("minecraft_mode")
    boolean minecraftMode;
    @SerializedName("publicserver_only")
    boolean publicserverOnly;
    @SerializedName("portlist_short")
    private String portlistShort;
    @SerializedName("folder_short")
    private String folderShort;
    private boolean installed;
    private boolean active;
    @SerializedName("minimum_slots")
    private int minimumSlots;
    @SerializedName("enough_slots")
    private boolean enoughSlots;
    private Modpack[] modpacks;
    private Icons icons;
    private int[] locations;
    private String[] tags;
    @SerializedName("preorder_locations")
    private int[] preorderLocations;
    private boolean visible;

    public int getId() {
        return id;
    }

    public int getSteamId() {
        return steamId;
    }

    // ??
    public String hasSteamGame() {
        return hasSteamGame;
    }


    /**
     * Returns the name of this game.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    public boolean isMinecraftMode() {
        return minecraftMode;
    }

    public boolean isPublicserverOnly() {
        return publicserverOnly;
    }

    /**
     * Returns the portlist short of this game.
     *
     * @return the portlist short
     */
    public String getPortlistShort() {
        return portlistShort;
    }

    /**
     * Returns the folder short of this game.
     *
     * @return the folder short
     */
    public String getFolderShort() {
        return folderShort;
    }

    /**
     * Returns true if this game is installed.
     *
     * @return whether this game is installed
     */
    public boolean isInstalled() {
        return installed;
    }

    /**
     * Returns true if this game is active.
     *
     * @return whether this game is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the number of slots this game needs to work.
     *
     * @return the minimum number of slots
     */
    public int getMinimumSlots() {
        return minimumSlots;
    }

    /**
     * Returns true if the server has enough slots for this game.
     *
     * @return whether the server has enough slots for this game
     */
    public boolean hasEnoughSlots() {
        return enoughSlots;
    }

    /**
     * Returns the modpacks that can be installed with this game
     *
     * @return a list of modpacks that can be installed
     */
    public Modpack[] getModpacks() {
        return modpacks;
    }

    /**
     * Returns the 16x16 icon.
     *
     * @return a url to the icon
     */
    public String getIconx16() {
        return icons.x16;
    }

    /**
     * Returns the 32x32 icon.
     *
     * @return a url to the icon
     */
    public String getIconx32() {
        return icons.x32;
    }

    /**
     * Returns the 64x64 icon.
     *
     * @return a url to the icon
     */
    public String getIconx64() {
        return icons.x64;
    }

    /**
     * Returns the 128x128 icon.
     *
     * @return a url to the icon
     */
    public String getIconx128() {
        return icons.x128;
    }

    /**
     * Returns the 256x256 icon.
     *
     * @return a url to the icon
     */
    public String getIconx256() {
        return icons.x256;
    }

    public int[] getLocations() {
        return locations;
    }

    public String[] getTags() {
        return tags;
    }

    public int[] getPreorderLocations() {
        return preorderLocations;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return name;
    }
}
