package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a modpack installed on a gameserver.
 */
public class Modpack {
    private String name;
    @SerializedName("modpack_version")
    private String modpackVersion;
    @SerializedName("game_version")
    private String gameVersion;
    @SerializedName("modpack_file")
    private String modpackFile;

    /**
     * Returns the name of the modpack.
     *
     * @return name of the modpack
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the version of the modpack.
     *
     * @return version of the modpack
     */
    public String getModpackVersion() {
        return modpackVersion;
    }

    /**
     * Returns the version of the game.
     *
     * @return version of the game
     */
    public String getGameVersion() {
        return gameVersion;
    }

    /**
     * Returns the file.
     *
     * @return file of the modpack
     */
    public String getModpackFile() {
        return modpackFile;
    }
}
