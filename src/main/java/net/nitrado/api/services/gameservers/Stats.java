package net.nitrado.api.services.gameservers;

/**
 * This class represents the statistics of the gameserver.
 */
public class Stats {
    private long[][] currentPlayers;
    private long[][] maxPlayers;
    private long[][] cpuUsage;
    private long[][] memoryUsage;

    /**
     * Returns statistics of the amount of current players.
     *
     * @return statistics of the amount of current players
     */
    public long[][] getCurrentPlayers() {
        return currentPlayers;
    }

    /**
     * Returns statistics of the maximum amount of players.
     *
     * @return statistics of the maximum amount of players
     */
    public long[][] getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Returns statistics of the cpu usage.
     *
     * @return statistics of the cpu usage
     */
    public long[][] getCpuUsage() {
        return cpuUsage;
    }

    /**
     * Returns statistics of the memory usage.
     *
     * @return statistics of the memory usage
     */
    public long[][] getMemoryUsage() {
        return memoryUsage;
    }
}
