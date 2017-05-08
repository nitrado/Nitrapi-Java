package net.nitrado.api.services.cloudservers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;
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
         * The Server is currently processing a up- or downgrade.
         */
        @SerializedName("flavour_change")
        FLAVOUR_CHANGE,

        /**
         * The server is currently restoring a Backup. This can take some minutes.
         */
        @SerializedName("restoring")
        RESTORING,

        /**
         * A error while the up- or downgrade is occurred. The support has been informed.
         */
        @SerializedName("error_fc")
        ERROR_FC,

        /**
         * A error while deleting the Server is occurred. The support has been informed.
         */
        @SerializedName("error_delete")
        ERROR_DELETE,

        /**
         * A error while installing the Server is occurred. The support has been informed.
         */
        @SerializedName("error_install")
        ERROR_INSTALL,

        /**
         * A error while re-installing the Server is occurred. The support has been informed.
         */
        @SerializedName("error_reinstall")
        ERROR_REINSTALL
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

    }

    public class Image {
        private int id;
        private String name;
        private boolean daemon;

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
         * Returns daemon.
         *
         * @return daemon
         */
        public boolean isDaemon() {
            return daemon;
        }

    }


    /**
     * The Status of the CloudServer.
     *
     * @return cloudserverStatus
     */
    public CloudserverStatus getCloudserverStatus() {
        return data.cloudserverStatus;
    }

    /**
     * Returns hostname.
     *
     * @return hostname
     */
    public String getHostname() {
        return data.hostname;
    }

    /**
     * Returns dynamic.
     *
     * @return dynamic
     */
    public boolean isDynamic() {
        return data.dynamic;
    }

    /**
     * Returns hardware.
     *
     * @return hardware
     */
    public Hardware getHardware() {
        return data.hardware;
    }

    /**
     * Returns ips.
     *
     * @return ips
     */
    public Ip[] getIps() {
        return data.ips;
    }

    /**
     * The currently installed image.
     *
     * @return image
     */
    public Image getImage() {
        return data.image;
    }

    /**
     * True if the Cloud Server has a Nitrapi Daemon instance running.
     *
     * @return daemonAvailable
     */
    public boolean isDaemonAvailable() {
        return data.daemonAvailable;
    }

    /**
     * Returns passwordAvailable.
     *
     * @return passwordAvailable
     */
    public boolean isPasswordAvailable() {
        return data.passwordAvailable;
    }

    /**
     * Returns bandwidthLimited.
     *
     * @return bandwidthLimited
     */
    public boolean isBandwidthLimited() {
        return data.bandwidthLimited;
    }


    /**
     * Returns a list of all backups.
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
    public void restoreBackup(int backupId) {
        api.dataPost("services/" + getId() + "/cloud_servers/backups/" + backupId+ "/restore", null);
    }

    /**
     * Deletes the backup with the given id.
     *
     * @param backupId
     */
    public void deleteBackup(int backupId) {
        api.dataDelete("services/" + getId() + "/cloud_servers/backups/" + backupId+ "", null);
    }

    /**
     */
    public void doBoot() {
        api.dataPost("services/" + getId() + "/cloud_servers/boot", null);
    }

    /**
     *
     * @param hostname
     */
    public void changeHostame(String hostname) {
        api.dataPost("services/" + getId() + "/cloud_servers/hostname", new Parameter[] {
                new Parameter("hostname", hostname)
        });
    }

    /**
     *
     * @param ipAddress
     * @param hostname
     */
    public void changePTREntry(String ipAddress, String hostname) {
        api.dataPost("services/" + getId() + "/cloud_servers/ptr/" + ipAddress+ "", new Parameter[] {
                new Parameter("hostname", hostname)
        });
    }

    /**
     *
     * @param imageId
     */
    public void doReinstall(int imageId) {
        api.dataPost("services/" + getId() + "/cloud_servers/reinstall", new Parameter[] {
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
        api.dataPost("services/" + getId() + "/cloud_servers/reset", null);
    }

    /**
     * Returns resource stats.
     *
     * @param time  valid time parameters: 1h, 4h, 1d, 7d
     * @return
     */
    public Resource[] getResourceUsage(int time) {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/resources", new Parameter[] {
                new Parameter("time", time)
        });

        Resource[] resources = api.fromJson(data.get("resources"), Resource[].class);
        return resources;
    }

    /**
     *
     * @param lines
     * @return
     */
    public String getConsoleLogs(int lines) {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/console_logs", new Parameter[] {
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


    @Override
    public void refresh() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers", null);
        CloudServerData datas = api.fromJson(data.get("cloud_server"), CloudServerData.class);
        this.data = datas;
    }
}
