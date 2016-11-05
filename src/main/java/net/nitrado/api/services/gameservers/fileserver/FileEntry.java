package net.nitrado.api.services.gameservers.fileserver;

import com.google.gson.annotations.SerializedName;

/**
 * A single file or single directory.
 */
public class FileEntry {
    /**
     * Type of this entry.
     */
    public enum Type {
        /**
         * This entry is a file.
         */
        @SerializedName("file")
        FILE,
        /**
         * This entry is a directory.
         */
        @SerializedName("dir")
        DIR
    }

    private Type type;
    private String path;
    private String name;
    private int size;
    private String owner;
    private String group;
    private String chmod;
    @SerializedName("created_at")
    private long createdAt;
    @SerializedName("modified_at")
    private long modifiedAt;
    @SerializedName("accessed_at")
    private long accessedAt;

    public FileEntry(String name, String path) {
        this.name = name;
        this.path = path;
        this.type = Type.DIR;
    }

    /**
     * Returns the type of this entry.
     *
     * @return FILE or DIR
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the path of this file.
     *
     * @return the absolute path
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the name of this file.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the size of this file.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the owner of this file.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the group of this file.
     *
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Returns the rights of this file.
     *
     * @return the rights in chmod-notation
     */
    public String getChmod() {
        return chmod;
    }

    /**
     * Returns the creation date of this file.
     *
     * @return the creation date as a unix timestamp
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the modified date of this file.
     *
     * @return the modified date as a unix timestamp
     */
    public long getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Returns the last accessed date of this file.
     *
     * @return the last accessed date as a unix timestamp
     */
    public long getAccessedAt() {
        return accessedAt;
    }

    @Override
    public String toString() {
        return name;
    }
}
