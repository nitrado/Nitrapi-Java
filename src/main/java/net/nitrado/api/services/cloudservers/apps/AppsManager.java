package net.nitrado.api.services.cloudservers.apps;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.common.Value;
import net.nitrado.api.services.Service;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an AppsManager.
 */
public class AppsManager {

    private transient Service service;
    private transient Nitrapi api;

    public static class Status extends Value {
        public Status(String value) {
            super(value);
        }
        public static Status INSTALLING = new Status("installing");
        public static Status UPDATING = new Status("updating");
        public static Status UNINSTALLING = new Status("uninstalling");
        public static Status INSTALLED = new Status("installed");
        public static Status STARTING = new Status("starting");
        public static Status STOPPING = new Status("stopping");
        public static Status STARTED = new Status("started");
        public static Status STOPPED = new Status("stopped");
        public static Status FAILED = new Status("failed");
        public static Status UNKNOWN = new Status("unknown");
    }

    /**
     * This class represents an App.
     */
    public class App {
        @SerializedName("app_name")
        private String appName;
        @SerializedName("app_type")
        private String appType;
        private String description;
        private Status status;
        @SerializedName("systemd_path")
        private String systemdPath;
        @SerializedName("systemd_config")
        private String systemdConfig;
        @SerializedName("systemd_modified")
        private Boolean systemdModified;
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
        @NotNull
        public String getAppName() {
            return appName;
        }

        /**
         * Returns appType.
         *
         * @return appType
         */
        @NotNull
        public String getAppType() {
            return appType;
        }

        /**
         * Returns description.
         *
         * @return description
         */
        @Nullable
        public String getDescription() {
            return description;
        }

        /**
         * Returns status.
         *
         * @return status
         */
        @Nullable
        public Status getStatus() {
            return status;
        }

        /**
         * Returns systemdPath.
         *
         * @return systemdPath
         */
        @Nullable
        public String getSystemdPath() {
            return systemdPath;
        }

        /**
         * Returns systemdConfig.
         *
         * @return systemdConfig
         */
        @Nullable
        public String getSystemdConfig() {
            return systemdConfig;
        }

        /**
         * Returns systemdModified.
         *
         * @return systemdModified
         */
        @Nullable
        public Boolean isSystemdModified() {
            return systemdModified;
        }

        /**
         * Returns cmd.
         *
         * @return cmd
         */
        @Nullable
        public String getCmd() {
            return cmd;
        }

        /**
         * Returns parsedCmd.
         *
         * @return parsedCmd
         */
        @Nullable
        public String getParsedCmd() {
            return parsedCmd;
        }

        /**
         * Returns parameters.
         *
         * @return parameters
         */
        @Nullable
        public HashMap<String, String> getParameters() {
            return parameters;
        }

        /**
         * Returns configurations.
         *
         * @return configurations
         */
        @Nullable
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
        private Integer recommended;

        /**
         * Returns name.
         *
         * @return name
         */
        @Nullable
        public String getName() {
            return name;
        }

        /**
         * Returns description.
         *
         * @return description
         */
        @Nullable
        public String getDescription() {
            return description;
        }

        /**
         * Returns recommended.
         *
         * @return recommended
         */
        @Nullable
        public Integer getRecommended() {
            return recommended;
        }
    }

    /**
     * the minimum requirements
     */
    public class MinimumRequirements {
        private Integer cpu;
        private Integer ram;
        private Integer ssd;

        /**
         * Returns needed cpu cores.
         *
         * @return needed cpu cores
         */
        @Nullable
        public Integer getCpu() {
            return cpu;
        }

        /**
         * Returns needed RAM in MB.
         *
         * @return needed RAM in MB
         */
        @Nullable
        public Integer getRam() {
            return ram;
        }

        /**
         * Returns needed SSD space in GB.
         *
         * @return needed SSD space in GB
         */
        @Nullable
        public Integer getSsd() {
            return ssd;
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
        private Boolean supportsIpBinding;
        private Port[] ports;
        @SerializedName("minimum_requirements")
        private MinimumRequirements minimumRequirements;

        /**
         * Returns appType.
         *
         * @return appType
         */
        @NotNull
        public String getAppType() {
            return appType;
        }

        /**
         * Returns category.
         *
         * @return category
         */
        @Nullable
        public String getCategory() {
            return category;
        }

        /**
         * Returns description.
         *
         * @return description
         */
        @Nullable
        public String getDescription() {
            return description;
        }

        /**
         * Returns supportsIpBinding.
         *
         * @return supportsIpBinding
         */
        @Nullable
        public Boolean isSupportsIpBinding() {
            return supportsIpBinding;
        }

        /**
         * Returns ports.
         *
         * @return ports
         */
        @Nullable
        public Port[] getPorts() {
            return ports;
        }

        /**
         * Returns the minimum requirements.
         *
         * @return the minimum requirements
         */
        @Nullable
        public MinimumRequirements getMinimumRequirements() {
            return minimumRequirements;
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
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @return App[]
     */
    public App[] getInstalledApps() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/apps/", null);

        App[] apps = api.fromJson(data.get("apps"), App[].class);
        return apps;
    }

    /**
     * Returns a list of available apps.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @return AppDescription[]
     */
    public AppDescription[] getAvailableApps() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/apps/available", null);

        AppDescription[] apps = api.fromJson(data.get("apps"), AppDescription[].class);
        return apps;
    }

    /**
     * Installs an app.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @param appType Valid application type from available list.
     * @param appName Name for the new application, only /^[a-zA-Z0-9\_]{1,32}$/ allowed.
     * @param ip ip
     * @param ports ports
     */
    public void install(String appType, String appName, String ip, HashMap<String, String> ports) throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/", new Parameter[] {
            new Parameter("app_type", appType),
            new Parameter("app_name", appName),
            new Parameter("ip", ip),
            new Parameter("ports", ports)
        });
    }

    /**
     * Uninstalls the application.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @param appName appName
     */
    public void uninstall(String appName) throws NitrapiException {
        api.dataDelete("services/" + service.getId() + "/cloud_servers/apps/" + appName + "", null);
    }

    /**
     * Configures the application.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @param appName appName
     * @param cmd the application start command line
     * @param parameters parameters
     */
    public void configure(String appName, String cmd, HashMap<String, String> parameters) throws NitrapiException {
        api.dataPut("services/" + service.getId() + "/cloud_servers/apps/" + appName + "", new Parameter[] {
            new Parameter("cmd", cmd),
            new Parameter("parameters", parameters)
        });
    }

    /**
     * Installs the latest application version on your server.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @param appName appName
     */
    public void update(String appName) throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/" + appName + "/update", null);
    }

    /**
     * Restarts the application.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @param appName appName
     */
    public void restart(String appName) throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/" + appName + "/restart", null);
    }

    /**
     * Stopps the application.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     * @param appName appName
     */
    public void stop(String appName) throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/apps/" + appName + "/stop", null);
    }
}
