package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents the list of games that can be installed on a server.
 */
public class GameList {

    @SerializedName("installed_currently")
    private Integer currentlyInstalled;
    @SerializedName("installed_maximum")
    private Integer maximumInstalled;
    @SerializedName("games")
    private Game[] allGames;

    /**
     * Returns the number of currently installed games.
     *
     * @return the number of currently installed games
     */
    @Nullable
    public Integer getCurrentlyInstalled() {
        return currentlyInstalled;
    }

    /**
     * Returns the maximum amount of games that can be installed.
     *
     * @return the maximum amount of games that can be installed
     */
    @Nullable
    public Integer getMaximumInstalled() {
        return maximumInstalled;
    }

    /**
     * Returns the full list of games.
     *
     * @return the full list of games
     */
    @Nullable
    public Game[] getAllGames() {
        return allGames;
    }
}
