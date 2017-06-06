package net.nitrado.api.services.gameservers;

/**
 * This class represents the statistics of the gameserver.
 */
public class Stats {
    private long[][] currentPlayers;
    private long[][] cpuUsage;
    private long[][] cpuUsageNitradorized;
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
     * Returns statistics of the cpu usage.
     *
     * @return statistics of the cpu usage
     */
    public long[][] getCpuUsage() {
        return cpuUsage;
    }

    public long[][] getCpuUsageNitradorized() {
        return cpuUsageNitradorized;
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
