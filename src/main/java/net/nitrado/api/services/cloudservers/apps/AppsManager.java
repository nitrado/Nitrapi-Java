package net.nitrado.api.services.cloudservers.apps;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;

import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class represents an AppsManager.
 */
public class AppsManager {

    private Service service;
    private Nitrapi api;

    /**
     * This class represents an App.
     */
    public class App {
        @SerializedName("app_name")
        private String appName;
        @SerializedName("app_type")
        private String appType;
        private String description;
        private String status;
        @SerializedName("systemd_path")
        private String systemdPath;
        @SerializedName("systemd_config")
        private String systemdConfig;
        @SerializedName("systemd_modified")
        private boolean systemdModified;
        private String cmd;
        @SerializedName("parsed_cmd")
        private String parsedCmd;
        private HashMap<String, String> parameters;
        private HashMap<String, String> configurations;

        /**
         * Returns appName.
         *
         * @return appName
         */
        public String getAppName() {
            return appName;
        }

        /**
         * Returns appType.
         *
         * @return appType
         */
        public String getAppType() {
            return appType;
        }

        /**
         * Returns description.
         *
         * @return description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns tODO enum?.
         *
         * @return tODO enum?
         */
        public String getStatus() {
            return status;
        }

        /**
         * Returns systemdPath.
         *
         * @return systemdPath
         */
        public String getSystemdPath() {
            return systemdPath;
        }

        /**
         * Returns systemdConfig.
         *
         * @return systemdConfig
         */
        public String getSystemdConfig() {
            return systemdConfig;
        }

        /**
         * Returns systemdModified.
         *
         * @return systemdModified
         */
        public boolean isSystemdModified() {
            return systemdModified;
        }

        /**
         * Returns cmd.
         *
         * @return cmd
         */
        public String getCmd() {
            return cmd;
        }

        /**
         * Returns parsedCmd.
         *
         * @return parsedCmd
         */
        public String getParsedCmd() {
            return parsedCmd;
        }

        /**
         * Returns parameters.
         *
         * @return parameters
         */
        public HashMap<String, String> getParameters() {
            return parameters;
        }

        /**
         * Returns configurations.
         *
         * @return configurations
         */
        public HashMap<String, String> getConfigurations() {
            return configurations;
        }
    }

    /**
     * This class represents a Port.
     */
    public class Port {
        private String name;
        private String description;
        private int recommended;

        /**
         * Returns name.
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * Returns description.
         *
         * @return description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns recommended.
         *
         * @return recommended
         */
        public int getRecommended() {
            return recommended;
        }
    }

    /**
     * This class represents an AppDescription.
     */
    public class AppDescription {
        @SerializedName("app_type")
        private String appType;
        private String category;
        private String description;
        @SerializedName("supports_ip_binding")
        private boolean supportsIpBinding;
        private Port[] ports;

        /**
         * Returns appType.
         *
         * @return appType
         */
        public String getAppType() {
            return appType;
        }

        /**
         * Returns category.
         *
         * @return category
         */
        public String getCategory() {
            return category;
        }

        /**
         * Returns description.
         *
         * @return description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns supportsIpBinding.
         *
         * @return supportsIpBinding
         */
        public boolean isSupportsIpBinding() {
            return supportsIpBinding;
        }

        /**
         * Returns ports.
         *
         * @return ports
         */
        public Port[] getPorts() {
            return ports;
        }
    }

    /**
     * Used internally.
     */
    public void init(Service service, Nitrapi api) {
        this.service = service;
        this.api = api;
    }

    /**
     * Returns the list of installed apps.
     *
     * @return App[]
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public App[] getInstalledApps() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/apps/", null);

        App[] apps = api.fromJson(data.get("apps"), App[].class);
        return apps;
    }

    /**
     * Returns a list of available apps.
     *
     * @return AppDescription[]
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public AppDescription[] getAvailableApps() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/apps/available", null);

        AppDescription[] apps = api.fromJson(data.get("apps"), AppDescription[].class);
        return apps;
    }

    /**
     * Installs an app.
     *
     * @param appType Valid application type from available list.
     * @param appName Name for the new application, only /^[a-zA-Z0-9\_]{1,16}$/ allowed.
     * @param ports   ports
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void install(String appType, String appName, HashMap<String, String> ports) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/", new Parameter[]{
                new Parameter("app_type", appType),
                new Parameter("app_name", appName),
                new Parameter("ports", ports)
        });
    }

    /**
     * Uninstalls the application.
     *
     * @param appName appName
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void uninstall(String appName) {
        api.dataDelete("services/" + service.getId() + "/cloud_servers/apps/" + appName + "", null);
    }

    /**
     * Configures the application.
     *
     * @param appName    appName
     * @param cmd        the application start command line
     * @param parameters parameters
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void configure(String appName, String cmd, HashMap<String, String> parameters) {
        api.dataPut("services/" + service.getId() + "/cloud_servers/apps/" + appName + "", new Parameter[]{
                new Parameter("cmd", cmd),
                new Parameter("parameters", parameters)
        });
    }

    /**
     * Installs the latest application version on your server.
     *
     * @param appName appName
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void update(String appName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/" + appName + "/update", null);
    }

    /**
     * Restarts the application.
     *
     * @param appName appName
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void restart(String appName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/" + appName + "/restart", null);
    }

    /**
     * Stopps the application.
     *
     * @param appName appName
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void stop(String appName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/" + appName + "/stop", null);
    }
}
