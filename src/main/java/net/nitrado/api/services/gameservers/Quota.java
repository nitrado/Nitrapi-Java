package net.nitrado.api.services.gameservers;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents quota of the user on a gameserver.
 */
public class Quota {
    @SerializedName("block_usage")
    private int blockUsage;
    @SerializedName("block_softlimit")
    private int blockSoftLimit;
    @SerializedName("block_hardlimit")
    private int blockHardLimit;
    @SerializedName("file_usage")
    private int fileUsage;
    @SerializedName("file_softlimit")
    private int fileSoftLimit;
    @SerializedName("file_hardlimit")
    private int fileHardLimit;

    /**
     * Returns the number of used blocks.
     *
     * @return the number of used blocks
     */
    public int getBlockUsage() {
        return blockUsage;
    }

    /**
     * Returns the soft limit of blocks.
     *
     * @return the soft limit of blocks
     */
    public int getBlockSoftLimit() {
        return blockSoftLimit;
    }

    /**
     * Returns the hard limit of blocks.
     *
     * @return the hard limit of blocks
     */
    public int getBlockHardLimit() {
        return blockHardLimit;
    }

    /**
     * Returns the number of used files.
     *
     * @return the number of used files
     */
    public int getFileUsage() {
        return fileUsage;
    }

    /**
     * Returns the soft limit of files.
     *
     * @return the soft limit of files
     */
    public int getFileSoftLimit() {
        return fileSoftLimit;
    }

    /**
     * Returns the hard limit of files.
     *
     * @return the hard limit of files
     */
    public int getFileHardLimit() {
        return fileHardLimit;
    }
}
