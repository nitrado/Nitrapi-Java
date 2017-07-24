package net.nitrado.api.services.gameservers;

/**
 * This class represents the statistics of the gameserver.
 */
public class Stats {
    private Long[][] currentPlayers;
    private Long[][] cpuUsage;
    private Long[][] memoryUsage;

    /**
     * Returns statistics of the amount of current players.
     *
     * @return statistics of the amount of current players
     */
    public Long[][] getCurrentPlayers() {
        return currentPlayers;
    }

    /**
     * Returns statistics of the cpu usage.
     *
     * @return statistics of the cpu usage
     */
    public Long[][] getCpuUsage() {
        return cpuUsage;
    }

    /**
     * Returns statistics of the memory usage.
     *
     * @return statistics of the memory usage
     */
    public Long[][] getMemoryUsage() {
        return memoryUsage;
    }
}
