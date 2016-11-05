package net.nitrado.api.services.gameservers.minecraft;

/**
 * A world in a minecraft game.
 */
public class World {
    private String game;
    private String world;
    private long timestamp;
    private int size;

    /**
     * Returns the game this world is created for.
     *
     * @return the game
     */
    public String getGame() {
        return game;
    }

    /**
     * Returns the name of the world.
     *
     * @return the name of the world
     */
    public String getWorld() {
        return world;
    }

    /**
     * Returns the timestamp of the backup.
     * <p>
     * Only available for backups.
     *
     * @return the timestamp as a unix timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the size of the world.
     * <p>
     * Only available for backups.
     *
     * @return the size of the world
     */
    public int getSize() {
        return size;
    }
}