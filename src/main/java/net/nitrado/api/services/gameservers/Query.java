package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the query of the gameserver.
 */
public class Query {
    /**
     * This class represents a player on a gameserver.
     */
    public class Player {
        private Integer id = -1;
        private String name;
        private Boolean bot;
        private Integer score = -1;
        private Integer frags = -1;
        private Integer deaths = -1;
        private Integer time = -1;
        private Integer ping = -1;

        /**
         * Returns the id of the player.
         *
         * @return the id of the player
         */
        public Integer getId() {
            return id;
        }

        /**
         * Returns the name of the player.
         *
         * @return the name of the player
         */
        public String getName() {
            return name;
        }

        /**
         * Returns true if the player is a bot.
         *
         * @return true if the player is a bot
         */
        public Boolean isBot() {
            return bot;
        }

        /**
         * Returns the score of the player.
         *
         * @return the score
         */
        public Integer getScore() {
            return score;
        }

        /**
         * Returns the number of frags of the player.
         *
         * @return the number of frags
         */
        public Integer getFrags() {
            return frags;
        }

        /**
         * Returns the number of deaths of the player.
         *
         * @return the number of deaths
         */
        public Integer getDeaths() {
            return deaths;
        }

        /**
         * Returns the time the player has spend on the server.
         *
         * @return the time the player has spend on the server in seconds
         */
        public Integer getTime() {
            return time;
        }

        /**
         * Returns the ping of the player.
         *
         * @return the ping as a number
         */
        public Integer getPing() {
            return ping;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @SerializedName("server_name")
    private String serverName;
    @SerializedName("connect_ip")
    private String connectIp;
    private String map;
    private String version;
    @SerializedName("player_current")
    private int playerCurrent = -1;
    @SerializedName("player_max")
    private int playerMax = -1;
    private Player[] players;

    /**
     * Returns the name of the server.
     *
     * @return the name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Returns ip and port of the server.
     *
     * @return the ip and port
     */
    public String getConnectIp() {
        return connectIp;
    }

    /**
     * Returns the map.
     *
     * @return the map
     */
    public String getMap() {
        return map;
    }

    /**
     * Returns the game-version on the server.
     *
     * @return the version number
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the number of players currently on the server.
     *
     * @return the number of players
     */
    public int getPlayerCurrent() {
        return playerCurrent;
    }

    /**
     * Returns the max number of players on the server.
     *
     * @return the max number of players
     */
    public int getPlayerMax() {
        return playerMax;
    }

    /**
     * Returns the players currently on the server.
     *
     * @return a list of the player
     */
    public Player[] getPlayers() {
        return players;
    }


    public void update(Query query) {
        if (query.serverName != null) {
            serverName = query.serverName;
        }
        if (query.connectIp != null) {
            connectIp = query.connectIp;
        }
        if (query.map != null) {
            map = query.map;
        }
        if (query.version != null) {
            version = query.version;
        }
        if (query.playerCurrent != -1) {
            playerCurrent = query.playerCurrent;
        }
        if (query.playerMax != -1) {
            playerMax = query.playerMax;
        }
        if (query.players != null) {
            players = query.players;
        }
    }
}
