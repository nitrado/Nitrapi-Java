package net.nitrado.api.services.gameservers.minecraft;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.gameservers.Gameserver;

/**
 * Specific function for minecraft-servers.
 */
public class Minecraft {
    private transient Gameserver service;
    private transient Nitrapi api;

    @SerializedName("task_running")
    private boolean taskRunning;
    @SerializedName("current_world")
    private String currentWorld;
    @SerializedName("all_worlds")
    private World[] allWorlds;
    @SerializedName("world_backups")
    private World[] worldBackups;
    @SerializedName("binary_md5")
    private String binaryMD5;
    private String binary;
    private Overviewmap overviewmap;
    private McMyAdmin mcmyadmin;
    private BungeeCord bungeecord;
    @SerializedName("rtk")
    private RemoteToolkit remoteToolkit;
    private Version[] versions;

    /**
     * Used internally.
     * <p>
     * Call Gameserver.getMinecraft() instead.
     *
     * @param service Gameserver object
     * @param api     reference to the api
     * @see Gameserver#getMinecraft()
     */
    public void init(Gameserver service, Nitrapi api) {
        this.service = service;
        this.api = api;
    }

    /**
     * Returns true if there is a task running and no other task can be started. e.g. rendering the overviewmap
     *
     * @return true if there is a task running
     */
    public boolean isTaskRunning() {
        return taskRunning;
    }

    /**
     * Returns the name of the current world.
     *
     * @return the name
     */
    public String getCurrentWorld() {
        return currentWorld;
    }

    /**
     * Returns all worlds.
     *
     * @return a list of all worlds
     */
    public World[] getAllWorlds() {
        return allWorlds;
    }

    /**
     * Returns all world backups.
     *
     * @return a list of all world backups.
     */
    public World[] getWorldBackups() {
        return worldBackups;
    }

    /**
     * Returns the md5sum of the minecraft binary.
     *
     * @return the md5sum of the minecraft binary
     */
    public String getBinaryMD5() {
        return binaryMD5;
    }

    /**
     * Returns the name of the minecraft binary.
     *
     * @return the name of the minecraft binary
     */
    public String getBinary() {
        return binary;
    }

    /**
     * Returns information about the overview map.
     *
     * @return an OverviewMap object
     */
    public Overviewmap getOverviewmap() {
        return overviewmap;
    }

    /**
     * Returns information about McMyAdmin.
     *
     * @return a McMyAdmin object
     */
    public McMyAdmin getMcMyAdmin() {
        return mcmyadmin;
    }

    /**
     * Returns information about BungeeCord.
     *
     * @return a BungeeCord object
     */
    public BungeeCord getBungeeCord() {
        return bungeecord;
    }

    /**
     * Returns information about Remote Toolkit.
     *
     * @return a RemoteToolkit object.
     */
    public RemoteToolkit getRemoteToolkit() {
        return remoteToolkit;
    }

    /**
     * Returns all available versions.
     *
     * @return a list of all available versions
     */
    public Version[] getVersions() {
        return versions;
    }

    /**
     * Changes settings of BungeeCord.
     *
     * @param enabled    is BungeeCord enabled
     * @param only       should only BungeeCord and not the gameserver itself be running
     * @param firewall   the type of firewall that should be activated
     * @param firewallIp ip allowed by the firewall
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void changeBungeeCord(boolean enabled, boolean only, BungeeCord.FirewallStatus firewall, String firewallIp) {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/bungeecord", new Parameter[]{
                new Parameter("enabled", enabled ? "1" : "0"),
                new Parameter("only", only ? "1" : "0"),
                new Parameter("firewall", firewall.toString()),
                new Parameter("firewall_ip", firewallIp)
        });
    }

    /**
     * Runs a chunkfix.
     * <p>
     * The live output of the chunkfix will be send to the websocket.
     * <p>
     * Deletes entities from a chunk that are above the limit. Too many entities in a chunk can cause heavy laggs. The chunkfix should first be executed with a high limit and if the problems persist with a limit of 0.
     *
     * @param world world.getGame() + "/" + world.getWorld()
     * @param limit amount of entities that should stay in a chunk
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void doChunkfix(String world, int limit) {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/chunkfix", new Parameter[]{
                new Parameter("world", world),
                new Parameter("limit", limit + "")
        });
    }

    /**
     * Changes settings of McMyAdmin.
     *
     * @param enabled  is McMyAdmin enabled
     * @param username username for McMyAdmin
     * @param password password for McMyAdmin
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void changeMcMyAdmin(boolean enabled, String username, String password) {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/mcmyadmin", new Parameter[]{
                new Parameter("enabled", enabled ? "1" : "0"),
                new Parameter("username", username),
                new Parameter("password", password)
        });
    }

    /**
     * Changes settings of Remote Toolkit.
     *
     * @param enabled  is Remote Toolkit enabled
     * @param username username for Remote Toolkit
     * @param password password for Remote Toolkit
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void changeRemoteToolkit(boolean enabled, String username, String password) {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/rtk", new Parameter[]{
                new Parameter("enabled", enabled ? "1" : "0"),
                new Parameter("username", username),
                new Parameter("password", password)
        });
    }

    /**
     * Changes the settings of the overview map.
     * <p>
     * You can change the enabled status only once within 24 hours.
     * You can reset the map only every 3 days.
     *
     * @param enabled is the overviewmap enabled
     * @param signs   are signs shown on the overview map
     * @param reset   map will be reset if this is true
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void changeOverviewMap(boolean enabled, boolean signs, boolean reset, String ipsonly) {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/overviewmap", new Parameter[]{
                new Parameter("enabled", enabled ? "1" : "0"),
                new Parameter("signs", signs ? "1" : "0"),
                new Parameter("reset", reset ? "1" : "0"),
                new Parameter("ipsonly", ipsonly)
        });
    }

    /**
     * Starts the rendering process of the overview map.
     * The gameserver will be stopped during the rendering.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void renderOverviewMap() {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/overviewmap_render", null);
    }

    /**
     * Returns the overview map rendering log if available.
     *
     * @return the log as a string
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public String getOverviewMapLogs() {
        return api.dataGet("services/" + service.getId() + "/gameservers/games/minecraft/overviewmap_log", null).get("log").getAsString();
    }

    /**
     * Switch the running minecraft version.
     * <p>
     * Only available if you run Minecraft Vanilla or Minecraft Bukkit/Spigot.
     *
     * @param md5 md5-hash of the version you want to switch to
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void changeVersion(String md5) {
        api.dataPost("/services/" + service.getId() + "/gameservers/games/minecraft/change_version", new Parameter[]{new Parameter("md5", md5)});
    }


    /**
     * Returns the the minecraft uuid by username.
     *
     * @param username name of the minecraft-user
     * @return the uuid of this minecraft-user
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public String getUserUUID(String username) {
        return api.dataGet("services/" + service.getId() + "/gameservers/games/minecraft/uuid", new Parameter[]{new Parameter("username", username)}).get("user").getAsJsonObject().get("uuid").getAsString();
    }

    /**
     * Returns the minecraft avatar information by username.
     *
     * @param username username case sensitive
     * @return the user avatar base64 encoded
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public String getUserAvatar(String username) {
        return api.dataGet("services/" + service.getId() + "/gameservers/games/minecraft/avatar", new Parameter[]{new Parameter("username", username)}).get("user").getAsJsonObject().get("avatar").getAsString();
    }

    /**
     * Returns installed plugins for minecraft bukkit/spigot.
     *
     * @return a list of installed plugins
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public Plugin[] getPlugins() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/games/minecraft/plugins", null);
        return api.fromJson(data.get("plugins"), Plugin[].class);
    }


    //// BACKUP-specific ////

    /**
     * Creates a new minecraft backup of a specific world.
     *
     * @param world world.getGame() + "/" + world.getWorld()
     * @permission ROLE_WEBINTERFACE_BACKUPS_WRITE
     */
    public void createBackup(String world) {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/backup", new Parameter[]{
                new Parameter("world", world)
        });
    }


    /**
     * Restores a specific backup to the main directory of the minecraft installation.
     *
     * @param timestamp timestamp of the backup
     * @permission ROLE_WEBINTERFACE_BACKUPS_WRITE
     */
    public void restoreBackup(long timestamp) {
        api.dataPost("services/" + service.getId() + "/gameservers/games/minecraft/backup/" + timestamp + "/restore", null);
    }

    /**
     * Deletes a specific backup.
     * <p>
     * If you delete a backup, it's gone forever.
     *
     * @param timestamp timestamp of the backup
     * @permission ROLE_WEBINTERFACE_BACKUPS_WRITE
     */
    public void destroyBackup(long timestamp) {
        api.dataDelete("services/" + service.getId() + "/gameservers/games/minecraft/backup/" + timestamp, null);
    }
}
