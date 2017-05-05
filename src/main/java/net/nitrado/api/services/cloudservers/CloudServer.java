package net.nitrado.api.services.cloudservers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;

/**
 * This class represents a cloud server.
 */
public class CloudServer extends Service {

    public enum Status {
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
         * The Server is currently restoring a Backup. This can take some minutes.
         */
        @SerializedName("restoring")
        RESTORING,
        /**
         * An error occurred while up- or downgrading. The support has been informed.
         */
        @SerializedName("error_fc")
        ERROR_FLAVOUR_CHANGE,
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
        ERROR_REINSTALL
    }

    private CloudServerInfo info;


    private class HardwareInfo {
        private int cpu;
        private int ram;
        private boolean windows;
        private int ssd;
        @SerializedName("ipv4")
        private int ipv4Count;
        private int traffic;
        @SerializedName("backup")
        private int backupCount;
    }

    private class Ip {
        private String address;
        private int version;
        @SerializedName("main_ip")
        private boolean mainIp;
        private String mac;
        private String ptr;

        public String getAddress() {
            return address;
        }

        /**
         * Returns the ip version (4 or 6).
         * @return
         */
        public int getVersion() {
            return version;
        }

        public boolean isMainIp() {
            return mainIp;
        }

        public String getMac() {
            return mac;
        }

        public String getPtr() {
            return ptr;
        }
    }


    private class CloudServerInfo {
        private Status status;
        private String hostname;
        private boolean dynamic;
        private HardwareInfo hardware;
        private Ip[] ips;
        private Image image;
        @SerializedName("daemon_available")
        private boolean daemonAvailable;
        @SerializedName("password_available")
        private boolean passwordAvailable;
        @SerializedName("bandwidth_limited")
        private boolean bandwidthLimited;

    }




    @Override
    public void refresh() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers", null);
        CloudServerInfo infos = api.fromJson(data.get("cloud_server"), CloudServerInfo.class);
        this.info = infos;
    }

    public Status getCloudServerStatus() {
        return info.status;
    }

    public String getHostname() {
        return info.hostname;
    }

    public boolean isDynamic() {
        return info.dynamic;
    }

    public int getCPU() {
        return info.hardware.cpu;
    }

    public int getRAM() {
        return info.hardware.ram;
    }

    public boolean isWindows() {
        return info.hardware.windows;
    }

    public int getIpv4AddressCount() {
        return info.hardware.ipv4Count;
    }

    /**
     * Returns the amount of high speed traffic in TB.
     * @return
     */
    public int getTraffic() {
        return info.hardware.traffic;
    }

    public int getBackupCount() {
        return info.hardware.backupCount;
    }

    public Ip[] getIps() {
        return info.ips;
    }

    /**
     * Returns the main ipv4 address of the server.
     * @return
     */
    public String getMainIp() {
        for (Ip ip : info.ips) {
            if (ip.mainIp && ip.version == 4) {
                return ip.address;
            }
        }
        return null;
    }

    /**
     * Returns the currently installed image.
     * @return
     */
    public Image getImage() {
        return info.image;
    }

    /**
     * Returns true if the Cloud Server has a Nitrapi Daemon instance running.
     * @return
     */
    public boolean hasDaemonSupport() {
        return info.daemonAvailable;
    }

    public boolean isPasswordAvailable() {
        return info.passwordAvailable;
    }

    public boolean isBandwidthLimited() {
        return info.bandwidthLimited;
    }


    public Backup[] getBackups() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/backups", null);
        return api.fromJson(data.get("backups"), Backup[].class);
    }

    public void createBackup() {
        api.dataPost("services/" + getId() + "/cloud_servers/backups", null);
    }

    /**
     * Restore the backup with the given id.
     * @param backupId
     */
    public void restoreBackup(int backupId) {
        api.dataPost("services/" + getId() + "/cloud_servers/backups/" + backupId + "/restore", new Parameter[0]);
    }

    /**
     * Delete the backup with the given id.
     * @param backupId
     */
    public void deleteBackup(int backupId) {
        api.dataDelete("services/" + getId() + "/cloud_servers/backups/" + backupId + "/restore", new Parameter[0]);
    }


    public void doBoot() {
        api.dataPost("services/" + getId() + "/cloud_servers/boot", null);
    }

    public void changeHostname(String newHostname) {
        api.dataPost("services/" + getId() + "/cloud_servers/hostname", new Parameter[]{new Parameter("hostname", newHostname)});
    }

    public void changePTREntry(String ipAddress, String newHostname) {
        api.dataPost("services/" + getId() + "/cloud_servers/ptr/" + ipAddress, new Parameter[]{new Parameter("hostname", newHostname)});
    }

    /**
     * Returns the full list of available images.
     * @return
     */
    public Image[] getImages() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/images", null);
        return api.fromJson(data.get("images"), Image[].class);
    }

    public void doReinstall(int imageId) {
        api.dataPost("services/" + getId() + "/cloud_servers/reinstall", new Parameter[]{new Parameter("image_id", imageId)});
    }

    public void doReboot() {
        api.dataPost("services/" + getId() + "/cloud_servers/reboot", null);
    }

    /**
     * A hard reset will turn of your Cloud Server instantly. This can cause data loss or file system corruption.
     * Only trigger if the instance does not respond to normal reboots.
     */
    public void doReset() {
        api.dataPost("services/" + getId() + "/cloud_servers/reset", null);
    }


    /**
     * Returns resource stats
     * @param time valid time parametters: 1h, 4h, 1d, 7d
     * @return
     */
    public Resource[] getResourceUsage(String time) {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/resources", new Parameter[]{new Parameter("time", time)});
        return api.fromJson(data.get("resources"), Resource[].class);
    }
    public Resource[] getResourceUsage() {
        return getResourceUsage("4h");
    }

    public String getConsoleLogs(int lines) {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/console_logs", new Parameter[]{new Parameter("lines", lines)});
        return data.get("console_logs").getAsString();
    }
    public String getConsoleLogs() {
        return getConsoleLogs(50);
    }

    public String getNoVNCUrl() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/console", null);
        return data.get("console").getAsJsonObject().get("url").getAsString();
    }

    /**
     * If you haven't any SSH keys added before the Server installation, you can use this API call to receive your initial password.
     * After requesting this password it will be deleted from our database. Please save the password after requesting.
     * @return
     */
    public String getInitialPassword() {
        JsonObject data = api.dataGet("services/" + getId() + "/cloud_servers/password", null);
        return data.get("password").getAsString();
    }

    public void doShutdown() {
        api.dataPost("services/" + getId() + "/cloud_servers/shutdown", null);
    }

    // TODO traffic statistics
}
