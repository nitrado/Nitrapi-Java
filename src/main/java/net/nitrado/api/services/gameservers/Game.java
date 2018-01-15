package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Game.
 */
public class Game {

    private Integer id;
    @SerializedName("steam_id")
    private Integer steamId;
    @SerializedName("has_steam_game")
    private String hasSteamGame;
    private String name;
    @SerializedName("minecraft_mode")
    private Boolean minecraftMode;
    @SerializedName("publicserver_only")
    private Boolean publicserverOnly;
    @SerializedName("portlist_short")
    private String portlistShort;
    @SerializedName("folder_short")
    private String folderShort;
    private Boolean installed;
    private Boolean active;
    @SerializedName("minimum_slots")
    private Integer minimumSlots;
    @SerializedName("enough_slots")
    private Boolean enoughSlots;
    private Modpack[] modpacks;
    private Icons icons;
    private Integer[] locations;
    private String[] tags;
    @SerializedName("preorder_locations")
    private Integer[] preorderLocations;
    private Boolean visible;

    /**
     * This class represents an icons.
     */
    public class Icons {
        private String x16;
        private String x32;
        private String x64;
        @SerializedName("x120")
        private String x128;
        private String x256;

        /**
         * Returns x16.
         *
         * @return x16
         */
        @Nullable
        public String getX16() {
            return x16;
        }

        /**
         * Returns x32.
         *
         * @return x32
         */
        @Nullable
        public String getX32() {
            return x32;
        }

        /**
         * Returns x64.
         *
         * @return x64
         */
        @Nullable
        public String getX64() {
            return x64;
        }

        /**
         * Returns x128.
         *
         * @return x128
         */
        @Nullable
        public String getX128() {
            return x128;
        }

        /**
         * Returns x256.
         *
         * @return x256
         */
        @Nullable
        public String getX256() {
            return x256;
        }
    }

    /**
     * Returns id.
     *
     * @return id
     */
    @NotNull
    public Integer getId() {
        return id;
    }

    /**
     * Returns steamId.
     *
     * @return steamId
     */
    @Nullable
    public Integer getSteamId() {
        return steamId;
    }

    /**
     * Returns ??.
     *
     * @return ??
     */
    @Nullable
    public String getHasSteamGame() {
        return hasSteamGame;
    }

    /**
     * Returns name.
     *
     * @return name
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns minecraftMode.
     *
     * @return minecraftMode
     */
    @Nullable
    public Boolean isMinecraftMode() {
        return minecraftMode;
    }

    /**
     * Returns publicserverOnly.
     *
     * @return publicserverOnly
     */
    @Nullable
    public Boolean isPublicserverOnly() {
        return publicserverOnly;
    }

    /**
     * Returns portlistShort.
     *
     * @return portlistShort
     */
    @Nullable
    public String getPortlistShort() {
        return portlistShort;
    }

    /**
     * Returns folderShort.
     *
     * @return folderShort
     */
    @Nullable
    public String getFolderShort() {
        return folderShort;
    }

    /**
     * Returns installed.
     *
     * @return installed
     */
    @Nullable
    public Boolean isInstalled() {
        return installed;
    }

    /**
     * Returns active.
     *
     * @return active
     */
    @Nullable
    public Boolean isActive() {
        return active;
    }

    /**
     * Returns minimumSlots.
     *
     * @return minimumSlots
     */
    @Nullable
    public Integer getMinimumSlots() {
        return minimumSlots;
    }

    /**
     * Returns enoughSlots.
     *
     * @return enoughSlots
     */
    @Nullable
    public Boolean isEnoughSlots() {
        return enoughSlots;
    }

    /**
     * Returns modpacks.
     *
     * @return modpacks
     */
    @Nullable
    public Modpack[] getModpacks() {
        return modpacks;
    }

    /**
     * Returns icons.
     *
     * @return icons
     */
    @Nullable
    public Icons getIcons() {
        return icons;
    }

    /**
     * Returns locations.
     *
     * @return locations
     */
    @Nullable
    public Integer[] getLocations() {
        return locations;
    }

    /**
     * Returns tags.
     *
     * @return tags
     */
    @Nullable
    public String[] getTags() {
        return tags;
    }

    /**
     * Returns preorderLocations.
     *
     * @return preorderLocations
     */
    @Nullable
    public Integer[] getPreorderLocations() {
        return preorderLocations;
    }

    /**
     * Returns visible.
     *
     * @return visible
     */
    @Nullable
    public Boolean isVisible() {
        return visible;
    }
}
