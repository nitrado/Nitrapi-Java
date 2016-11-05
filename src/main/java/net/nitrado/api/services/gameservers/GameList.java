package net.nitrado.api.services.gameservers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;

/**
 * This class represents the list of games that can be installed on a server.
 */
public class GameList {
    @SerializedName("installed_currently")
    private int currentlyInstalled;
    @SerializedName("installed_maximum")
    private int maximumInstalled;
    private Game[] games;

    /**
     * Used internally.
     * <p>
     * Call Gameserver.getGames() instead.
     *
     * @param api reference to the api
     * @param id  the id of the service
     * @return a gamelist object
     * @see Gameserver#getGames()
     */
    public static GameList newInstance(Nitrapi api, int id) {
        JsonObject data = api.dataGet("services/" + id + "/gameservers/games", null);
        GameList list = api.fromJson(data, GameList.class);
        return list;
    }


    /**
     * Returns the amount of games currently installed.
     *
     * @return the number of currently installed games
     */
    public int getCurrentlyInstalled() {
        return currentlyInstalled;
    }

    /**
     * Returns the maximum amount of games that can be installed on this server.
     *
     * @return the maximum amount of installed games
     */
    public int getMaximumInstalled() {
        return maximumInstalled;
    }

    /**
     * Returns the full list of games.
     *
     * @return a list of games
     */
    public Game[] getAllGames() {
        return games;
    }
}
