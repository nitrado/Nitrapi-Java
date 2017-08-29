package net.nitrado.api.services.cloudservers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.cloudservers.apps.AppsManager;
import net.nitrado.api.services.cloudservers.systemd.Journald;
import net.nitrado.api.services.cloudservers.systemd.Systemd;
import net.nitrado.api.services.fileserver.FileServer;

import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class represents a CloudServer.
 */
public class CloudServer extends Service {

    /**
     * The Status of the CloudServer.
     */
    public enum CloudserverStatus {
        /**
         * The Server is running.
         */
        @SerializedName("running")
        RUNNING,

        /**
         * The Server is stopped.
         */
        @SerializedName("stopped")
        STOPPED,

        /**
         * The Server is currently installing. This can take some minutes.
         */
        @SerializedName("installing")
        INSTALLING,

        /**
         * The Server is currently re-installing. This can take some minutes.
         */
        @SerializedName("reinstalling")
        REINSTALLING,

        /**
         * The Server is currently processing an up- or downgrade.
         */
        @SerializedName("flavour_change")
        FLAVOUR_CHANGE,

        /**
         * The Server is currently restoring a backup. This can take some minutes.
         */
        @SerializedName("restoring")
        RESTORING,

        /**
         * An error occurred while up- or downgrading. The support has been informed.
         */
        @SerializedName("error_fc")
        ERROR_FC,

        /**
         * An error occurred while deleting the Server. The support has been informed.
         */
        @SerializedName("error_delete")
        ERROR_DELETE,

        /**
         * An error occurred while installing the Server. The support has been informed.
         */
        @SerializedName("error_install")
        ERROR_INSTALL,

        /**
         * An error occurred while reinstalling the Server. The support has been informed.
         */
        @SerializedName("error_reinstall")
        ERROR_REINSTALL;

        @Override
        public String toString() {
            try {
                return CloudserverStatus.class.getDeclaredField(super.toString()).getAnnotation(SerializedName.class).value();
            } catch (NoSuchFieldException e) {
                // should not happen
                return super.toString();
            }
        }
    }

    private class CloudServerData {
        @SerializedName("status")
        private CloudserverStatus cloudserverStatus;
        private String hostname;
        private boolean dynamic;
        private Hardware hardware;
        private Ip[] ips;
        private Image image;
        @SerializedName("daemon_available")
        private boolean daemonAvailable;
        @SerializedName("password_available")
        private boolean passwordAvailable;
        @SerializedName("bandwidth_limited")
        private boolean bandwidthLimited;
    }

    private CloudServerData data;

    public class Hardware {
        private int cpu;
        private int ram;
        private boolean windows;
        private int ssd;
        private int ipv4;
        private int traffic;
        private int backup;

        /**
         * Returns cpu.
         *
         * @return cpu
         */
        public int getCpu() {
            return cpu;
        }

        /**
         * Returns ram.
         *
         * @return ram
         */
        public int getRam() {
            return ram;
        }

        /**
         * Returns windows.
         *
         * @return windows
         */
        public boolean isWindows() {
            return windows;
        }

        /**
         * Returns ssd.
         *
         * @return ssd
         */
        public int getSsd() {
            return ssd;
        }

        /**
         * Returns ipv4.
         *
         * @return ipv4
         */
        public int getIpv4() {
            return ipv4;
        }

        /**
         * The amount of high speed traffic in TB.
         *
         * @return traffic
         */
        public int getTraffic() {
            return traffic;
        }

        /**
         * Returns backup.
         *
         * @return backup
         */
        public int getBackup() {
            return backup;
        }

    }

    public class Ip {
        private String address;
        private int version;
        @SerializedName("main_ip")
        private boolean mainIp;
        private String mac;
        private String ptr;

        /**
         * Returns address.
         *
         * @return address
         */
        public String getAddress() {
            return address;
        }

        /**
         * The ip version (4 or 6).
         *
         * @return version
         */
        public int getVersion() {
            return version;
        }

        /**
         * Returns mainIp.
         *
         * @return mainIp
         */
        public boolean isMainIp() {
            return mainIp;
        }

        /**
         * Returns mac.
         *
         * @return mac
         */
        public String getMac() {
            return mac;
        }

        /**
         * Returns ptr.
         *
         * @return ptr
         */
        public String getPtr() {
            return ptr;
        }

        @Override
        public String toString() {
            return address;
        }
    }

    /**
     * This class represents an image.
     */
    public class Image {
        private int id;
        private String name;
        @SerializedName("is_windows")
        private boolean windows;
        @SerializedName("default")
        private boolean isDefault;
        @SerializedName("has_daemon")
        private boolean hasDaemon;
        @SerializedName("is_daemon_compatible")
        private boolean isDaemonCompatible;

        /**
         * Returns id.
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns name.
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * Returns windows.
         *
         * @return windows
         */
        public boolean isWindows() {
            return windows;
        }

        /**
         * Returns isDefault.
         *
         * @return isDefault
         */
        public boolean isDefault() {
            return isDefault;
        }

        /**
         * Returns hasDaemon.
         *
         * @return hasDaemon
         */
        public boolean hasDaemon() {
            return hasDaemon;
        }

        /**
         * Returns isDaemonCompatible.
         *
         * @return isDaemonCompatible
         */
        public boolean isDaemonCompatible() {
            return isDaemonCompatible;
        }
    }

    /**
     * This class represents a SupportAuthorization.
     */
    public class SupportAuthorization {
        @SerializedName("created_at")
        private GregorianCalendar createdAt;
        @SerializedName("expires_at")
        private GregorianCalendar expiresAt;

        /**
         * Returns createdAt.
         *
         * @return createdAt
         */
        public GregorianCalendar getCreatedAt() {
            return createdAt;
        }

        /**
         * Returns expiresAt.
         *
         * @return expiresAt
         */
        public GregorianCalendar getExpiresAt() {
            return expiresAt;
        }
    }

    /**
     * This class represents a Group.
     */
    public class Group {
        private int id;
        private String name;

        /**
         * Returns id.
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns name.
         *
         * @return name
         */
        public String getName() {
            return name;
        }
    }

    /**
     * This class represents an User.
     */
    public class User {
        private String username;
        private Group[] groups;
        private int id;
        private String home;

        /**
         * Returns username.
         *
         * @return username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Returns groups.
         *
         * @return groups
         */
        public Group[] getGroups() {
            return groups;
        }

        /**
         * Returns id.
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns home.
         *
         * @return home
         */
        public String getHome() {
            return home;
        }
    }

    /**
     * This class represents a current_month.
     */
    public class CurrentMonth {
        private int used;
        private int available;

        /**
         * Returns used traffic in MB.
         *
         * @return used traffic in MB
         */
        public int getUsed() {
            return used;
        }

        /**
         * Returns available traffic in MB.
         *
         * @return available traffic in MB
         */
        public int getAvailable() {
            return available;
        }
    }

    /**
     * This class represents a TrafficEntry.
     */
    public class TrafficEntry {
        private int incoming;
        private int outgoing;

        /**
         * Returns incoming traffic in MB.
         *
         * @return incoming traffic in MB
         */
        public int getIncoming() {
            return incoming;
        }

        /**
         * Returns outgoing traffic in MB.
         *
         * @return outgoing traffic in MB
         */
        public int getOutgoing() {
            return outgoing;
        }
    }

    /**
     * This class represents a TrafficStatistics.
     */
    public class TrafficStatistics {
        @SerializedName("current_month")
        private CurrentMonth currentMonth;
        @SerializedName("last_31_days")
        private HashMap<String, TrafficEntry> last31Days;

        /**
         * Returns currentMonth.
         *
         * @return currentMonth
         */
        public CurrentMonth getCurrentMonth() {
            return currentMonth;
        }

        /**
         * Returns last31Days.
         *
         * @return last31Days
         */
        public HashMap<String, TrafficEntry> getLast31Days() {
            return last31Days;
        }
    }

    /**
     * The Status of the CloudServer.
     *
     * @return cloudserverStatus
     */
    public CloudserverStatus getCloudserverStatus() {
        return data != null ? data.cloudserverStatus : null;
    }

    /**
     * Returns hostname.
     *
     * @return hostname
     */
    public String getHostname() {
        return data != null ? data.hostname : null;
    }

    /**
     * Returns dynamic.
     *
     * @return dynamic
     */
    public boolean isDynamic() {
        return data != null ? data.dynamic : null;
    }

    /**
     * Returns hardware.
     *
     * @return hardware
     */
    public Hardware getHardware() {
        return data != null ? data.hardware : null;
    }

    /**
     * Returns ips.
     *
     * @return ips
     */
    public Ip[] getIps() {
        return data != null ? data.ips : null;
    }

    /**
     * The currently installed image.
     *
     * @return image
     */
    public Image getImage() {
        return data != null ? data.image : null;
    }

    /**
     * True if the Cloud Server has a Nitrapi Daemon instance running.
     *
     * @return daemonAvailable
     */
    public boolean isDaemonAvailable() {
        return data != null ? data.daemonAvailable : null;
    }

    /**
     * Returns passwordAvailable.
     *
     * @return passwordAvailable
     */
    public boolean isPasswordAvailable() {
        return data != null ? data.passwordAvailable : null;
    }

    /**
     * Returns bandwidthLimited.
     *
     * @return bandwidthLimited
     */
    public boolean isBandwidthLimited() {
        return data != null ? data.bandwidthLimited : null;
    }


    /**
     * Returns a list of all backups.
     *
     * @return a list of all backups.
     */
    public Backup[] getBackups() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/backups", null);

        Backup[] backups = api.fromJson(data.get("backups"), Backup[].class);
        return backups;
    }

    /**
     * Creates a new backup.
     */
    public void createBackup() {
        api.dataPost("services/" + getId() + "/cloud_servers/backups", null);
    }

    /**
     * Restores the backup with the given id.
     *
     * @param backupId
     */
    public void restoreBackup(String backupId) {
        api.dataPost("services/" + getId() + "/cloud_servers/backups/" + backupId + "/restore", null);
    }

    /**
     * Deletes the backup with the given id.
     *
     * @param backupId
     */
    public void deleteBackup(String backupId) {
        api.dataDelete("services/" + getId() + "/cloud_servers/backups/" + backupId + "", null);
    }

    /**
     */
    public void doBoot() {
        api.dataPost("services/" + getId() + "/cloud_servers/boot", null);
    }

    /**
     * @param hostname
     */
    public void changeHostame(String hostname) {
        api.dataPost("services/" + getId() + "/cloud_servers/hostname", new Parameter[]{
                new Parameter("hostname", hostname)
        });
    }

    /**
     * @param ipAddress
     * @param hostname
     */
    public void changePTREntry(String ipAddress, String hostname) {
        api.dataPost("services/" + getId() + "/cloud_servers/ptr/" + ipAddress + "", new Parameter[]{
                new Parameter("hostname", hostname)
        });
    }

    /**
     * @param imageId
     */
    public void doReinstall(int imageId) {
        api.dataPost("services/" + getId() + "/cloud_servers/reinstall", new Parameter[]{
                new Parameter("image_id", imageId)
        });
    }

    /**
     */
    public void doReboot() {
        api.dataPost("services/" + getId() + "/cloud_servers/reboot", null);
    }

    /**
     * A hard reset will turn of your Cloud Server instantly. This can cause data loss or file system corruption. Only trigger if the instance does not respond to normal reboots.
     */
    public void doReset() {
        api.dataPost("services/" + getId() + "/cloud_servers/hard_reset", null);
    }

    /**
     * Returns resource stats.
     *
     * @param time valid time parameters: 1h, 4h, 1d, 7d
     * @return
     */
    public Resource[] getResourceUsage(String time) {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/resources", new Parameter[]{
                new Parameter("time", time)
        });

        Resource[] resources = api.fromJson(data.get("resources"), Resource[].class);
        return resources;
    }

    /**
     * @param lines
     * @return
     */
    public String getConsoleLogs(int lines) {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/console_logs", new Parameter[]{
                new Parameter("lines", lines)
        });

        String console_logs = api.fromJson(data.get("console_logs"), String.class);
        return console_logs;
    }

    /**
     * @return
     */
    public String getNoVNCUrl() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/console", null);

        String consoleurl = api.fromJson(data.get("console").getAsJsonObject().get("url"), String.class);
        return consoleurl;
    }

    /**
     * @return
     */
    public String getInitialPassword() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/password", null);

        String password = api.fromJson(data.get("password"), String.class);
        return password;
    }

    /**
     */
    public void doShutdown() {
        api.dataPost("services/" + getId() + "/cloud_servers/shutdown", null);
    }

    /**
     * @return
     */
    public net.nitrado.api.services.cloudservers.firewall.Firewall getFirewall() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/firewall", null);

        net.nitrado.api.services.cloudservers.firewall.Firewall firewall = api.fromJson(data.get("firewall"), net.nitrado.api.services.cloudservers.firewall.Firewall.class);
        firewall.init(this, api);
        return firewall;
    }


    /**
     * Returns a FileServer object.
     *
     * @return a FileServer object
     */
    public FileServer getFileServer() {
        return new FileServer(this, api);
    }

    public AppsManager getAppsManager() {
        AppsManager manager = new AppsManager();
        manager.init(this, api);
        return manager;
    }

    /**
     * Returns the existing support authorization or a NitrapiError if none exists.
     *
     * @return SupportAuthorization
     * @permission ROLE_SUPPORT_AUTHORIZATION
     */
    public SupportAuthorization getSupportAuthorization() {
        JsonObject data = api.dataGet("services/" + getId() + "/support_authorization", null);

        SupportAuthorization support_authorization = api.fromJson(data.get("support_authorization"), SupportAuthorization.class);
        return support_authorization;
    }

    /**
     * Creates a support authorization.
     *
     * @permission ROLE_SUPPORT_AUTHORIZATION
     */
    public void createSupportAuthorization() {
        api.dataPost("services/" + getId() + "/support_authorization", null);
    }

    /**
     * Deletes the support authorization.
     *
     * @permission ROLE_SUPPORT_AUTHORIZATION
     */
    public void deleteSupportAuthorization() {
        api.dataDelete("services/" + getId() + "/support_authorization", null);
    }

    public Systemd getSystemd() {
        Systemd systemd = new Systemd();
        systemd.init(this, api);
        return systemd;
    }

    public Journald getJournald() {
        Journald journald = new Journald();
        journald.init(this, api);
        return journald;
    }

    /**
     * List all the users (with groups) on a Cloud Server. These users are located in the /etc/passwd. All newly creates users on the system are included in this array.
     * @return User[]
     */
    public User[] getUsers() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/user", null);

        User[] usersusers = api.fromJson(data.get("users").getAsJsonObject().get("users"), User[].class);
        return usersusers;
    }

    /**
     * Returns the daily traffic usage of the last 30 days.
     * @return TrafficStatistics
     */
    public TrafficStatistics getTrafficStatistics() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/traffic", null);

        TrafficStatistics traffic = api.fromJson(data.get("traffic"), TrafficStatistics.class);
        return traffic;
    }

    @Override
    public void refresh() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers", null);
        CloudServerData datas = api.fromJson(data.get("cloud_server"), CloudServerData.class);
        this.data = datas;
    }

    @Override
    protected void init(Nitrapi api) {
        this.api = api;
        if (getStatus() == Status.ACTIVE || getStatus() == Status.SUSPENDED) {
            refresh(); // initially load the data
        }

        fixServiceStatus();
    }
}
