package net.nitrado.api.services.gameservers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import net.nitrado.api.Nitrapi;

/**
 * This class represents the list of all games that exist in the Nitrado universe.
 */
public class GlobalGameList {

    class Location {
        private int id;
        private String country;
        private String city;

        public int getId() {
            return id;
        }

        public String getCountry() {
            return country;
        }

        public String getCity() {
            return city;
        }
    }

    @SerializedName("steam_id")
    private String steamId;
    private Location[] locations;
    private Game[] games;

    /**
     * Used internally.
     * <p>
     * Call Nitrapi.getAllGames() instead.
     *
     * @param api reference to the api
     * @return a gamelist object
     * @see Gameserver#getGames()
     */
    public static GlobalGameList newInstance(Nitrapi api) {
        JsonObject data = api.dataGet("gameserver/games", null);
        GlobalGameList list = api.fromJson(data.get("games"), GlobalGameList.class);
        return list;
    }

    public String getSteamId() {
        return steamId;
    }

    public Location[] getLocations() {
        return locations;
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
