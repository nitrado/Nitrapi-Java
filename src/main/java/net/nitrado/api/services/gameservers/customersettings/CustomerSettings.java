package net.nitrado.api.services.gameservers.customersettings;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.gameservers.Gameserver;

import java.util.HashMap;

/**
 * Settings of a Gameserver.
 */
public class CustomerSettings extends HashMap<String, HashMap<String, String>> {
    protected transient Nitrapi api;
    protected int serviceId;

    /**
     * Returns a specified setting.
     *
     * @param category category of the setting
     * @param key      key of the setting
     * @return the value of the setting
     * @throws CustomerSettingsNotFoundException if there is no setting with the specified category and key
     * @permission ROLE_WEBINTERFACE_SETTINGS_READ
     */
    public String readSetting(String category, String key) throws CustomerSettingsNotFoundException {
        if (!hasCategory(category)) {
            throw new CustomerSettingsNotFoundException("Category \"" + category + "\" not found.");
        }

        if (!hasSetting(category, key)) {
            throw new CustomerSettingsNotFoundException("Setting \"" + key + "\" in category \"" + category + "\" not found.");
        }
        return get(category).get(key);
    }

    /**
     * Changes a setting.
     * <p>
     * You have to call Gameserver.refresh() to see the change.
     *
     * @param category category of the setting
     * @param key      key of the setting
     * @param value    value of the setting
     * @throws CustomerSettingsNotFoundException if there is no setting with the specified category and key
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void writeSetting(String category, String key, String value) throws CustomerSettingsNotFoundException {
        if (!hasCategory(category)) {
            throw new CustomerSettingsNotFoundException("Category \"" + category + "\" not found.");
        }

        api.dataPost("services/" + serviceId + "/gameserver/settings", new Parameter[]{new Parameter("category", category), new Parameter("key", key), new Parameter("value", value)});
    }

    /**
     * Lists all available saved config sets.
     *
     * @return array of config sets
     * @permission ROLE_WEBINTERFACE_SETTINGS_READ
     */
    public CustomerSettingsSet[] getConfigSets() {
        JsonObject data = api.dataGet("services/" + serviceId + "/gameservers/settings/sets", null);
        JsonArray sets = data.get("sets").getAsJsonArray();
        CustomerSettingsSet[] configSets = new CustomerSettingsSet[sets.size()];
        for (int i = 0; i < sets.size(); i++) {
            configSets[i] = api.fromJson(sets.get(i), CustomerSettingsSet.class);
        }
        return configSets;
    }

    /**
     * Restores a specific config set.
     *
     * @param id id of the config set
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void restoreConfigSet(int id) {
        api.dataPost("services/" + serviceId + "/gameservers/settings/sets/" + id + "/restore", null);
    }

    /**
     * Deletes a specific config set.
     *
     * @param id id of the config set
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void deleteConfigSet(int id) {
        api.dataDelete("services/" + serviceId + "/gameservers/settings/sets/" + id, null);
    }

    /**
     * Creates a new config set with the current settings.
     *
     * @param name name of the new config set
     * @permission ROLE_WEBINTERFACE_SETTINGS_WRITE
     */
    public void createConfigSet(String name) {
        api.dataPost("services/" + serviceId + "/gameservers/settings/sets", new Parameter[]{new Parameter("name", name)});
    }

    /**
     * Returns true, if the category exist in the settings.
     *
     * @param category category of the setting
     * @return true if the category exists
     */
    public boolean hasCategory(String category) {
        return super.containsKey(category);
    }

    /**
     * Returns true, if the specified setting exists.
     *
     * @param category category of the setting
     * @param key      key of the setting
     * @return true if the specified setting exists
     */
    public boolean hasSetting(String category, String key) {
        HashMap<String, String> cat = super.get(category);
        if (cat == null) {
            return false;
        }
        return cat.containsKey(key);
    }


    /**
     * Used internally.
     * <p>
     * Call Gameserver.getCustomerSettings() instead.
     *
     * @param api Nitrapi object
     * @param id  id of the service
     * @see Gameserver#getCustomerSettings()
     */
    public void init(Nitrapi api, int id) {
        this.api = api;
        this.serviceId = id;
    }
}
