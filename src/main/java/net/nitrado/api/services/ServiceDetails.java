package net.nitrado.api.services;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents details of the service.
 */
public class ServiceDetails {
    private String address;
    private String name;
    private String game;
    @SerializedName("portlist_short")
    private String portlistShort;
    @SerializedName("folder_short")
    private String folderShort;
    private String type; // TODO: enum?

    /**
     * Returns the address of the service.
     *
     * @return ip and port
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the name of the service.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the game if it is a gameserver
     *
     * @return the name of the game
     */
    public String getGame() {
        return game;
    }

    /**
     * Returns the portlist short.
     *
     * @return the portlist short
     */
    public String getPortlistShort() {
        return portlistShort;
    }

    /**
     * Returns the folder short.
     *
     * @return the folder short
     */
    public String getFolderShort() {
        return folderShort;
    }

    /**
     * Returns the type if it is a voiceserver.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }
}
