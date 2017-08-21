package net.nitrado.api.services.gameservers.pluginsystem;

import com.google.gson.JsonObject;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.services.gameservers.Gameserver;

/**
 * The Plugin System is currently only available for Battlefield: Hardline.
 */
public class PluginSystem {
    private transient Gameserver service;
    private transient Nitrapi api;

    /**
     * Used internally.
     * <p>
     * Call Gameserver.getPluginSystem() instead.
     *
     * @param service gameserver object
     * @param api     reference to the api
     * @see Gameserver#getPluginSystem()
     */
    public PluginSystem(Gameserver service, Nitrapi api) {
        this.service = service;
        this.api = api;
    }

    /**
     * Installs a new plugin system instance.
     *
     * @permission ROLE_WEBINTERFACE_SETTINGS_READ
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void doInstall() throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/gameservers/plugin_system/install", null);
    }

    /**
     * Removes the current plugin system instance.
     *
     * @permission ROLE_WEBINTERFACE_SETTINGS_READ
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void doUninstall() throws NitrapiException {
        api.dataDelete("services/" + service.getId() + "/gameservers/plugin_system/uninstall", null);
    }

    /**
     * Restarts the current plugin system instance.
     *
     * @permission ROLE_WEBINTERFACE_SETTINGS_READ
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void doRestart() throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/gameservers/plugin_system/restart", null);
    }

    /**
     * Stopps the current plugin system instance.
     *
     * @permission ROLE_WEBINTERFACE_SETTINGS_READ
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void doStop() throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/gameservers/plugin_system/stop", null);
    }

    /**
     * Returns information about the installed plugin system.
     *
     * @return a PluginSystemInfo object with further information
     */
    public PluginSystemInfo info() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/plugin_system/info", null);
        return api.fromJson(data, PluginSystemInfo.class);
    }


}
