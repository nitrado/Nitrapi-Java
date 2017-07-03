package net.nitrado.api.services.cloudservers.systemd;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class represents a Journald.
 */
public class Journald {

    private transient Service service;
    private transient Nitrapi api;

    /**
     * This class represents a JournalEntry.
     */
    public class JournalEntry {
        @SerializedName("__CURSOR")
        private String cursor;
        @SerializedName("__REALTIME_TIMESTAMP")
        private int realtimeTimestamp;
        @SerializedName("_BOOT_ID")
        private String bootId;
        @SerializedName("PRIORITY")
        private int priority;
        @SerializedName("_UID")
        private int uid;
        @SerializedName("_GID")
        private int gid;
        @SerializedName("_SYSTEMD_SLICE")
        private String systemdSlice;
        @SerializedName("_MACHINE_ID")
        private String machineId;
        @SerializedName("_HOSTNAME")
        private String hostname;
        @SerializedName("_TRANSPORT")
        private String transport;
        @SerializedName("SYSLOG_FACILITY")
        private int syslogFacility;
        @SerializedName("SYSLOG_IDENTIFIER")
        private String syslogIdentifier;
        @SerializedName("_COMM")
        private String comm;
        @SerializedName("_EXE")
        private String exe;
        @SerializedName("_CMDLINE")
        private String cmdline;
        @SerializedName("_SYSTEMD_CGROUP")
        private String systemdCGroup;
        @SerializedName("_SYSTEMD_UNIT")
        private String systemdUnit;
        @SerializedName("MESSAGE")
        private String message;
        @SerializedName("_PID")
        private int pid;

        /**
         * Returns cursor.
         *
         * @return cursor
         */
        public String getCursor() {
            return cursor;
        }

        /**
         * Returns realtimeTimestamp.
         *
         * @return realtimeTimestamp
         */
        public int getRealtimeTimestamp() {
            return realtimeTimestamp;
        }

        /**
         * Returns bootId.
         *
         * @return bootId
         */
        public String getBootId() {
            return bootId;
        }

        /**
         * Returns priority.
         *
         * @return priority
         */
        public int getPriority() {
            return priority;
        }

        /**
         * Returns uid.
         *
         * @return uid
         */
        public int getUid() {
            return uid;
        }

        /**
         * Returns gid.
         *
         * @return gid
         */
        public int getGid() {
            return gid;
        }

        /**
         * Returns systemdSlice.
         *
         * @return systemdSlice
         */
        public String getSystemdSlice() {
            return systemdSlice;
        }

        /**
         * Returns machineId.
         *
         * @return machineId
         */
        public String getMachineId() {
            return machineId;
        }

        /**
         * Returns hostname.
         *
         * @return hostname
         */
        public String getHostname() {
            return hostname;
        }

        /**
         * Returns transport.
         *
         * @return transport
         */
        public String getTransport() {
            return transport;
        }

        /**
         * Returns syslogFacility.
         *
         * @return syslogFacility
         */
        public int getSyslogFacility() {
            return syslogFacility;
        }

        /**
         * Returns syslogIdentifier.
         *
         * @return syslogIdentifier
         */
        public String getSyslogIdentifier() {
            return syslogIdentifier;
        }

        /**
         * Returns comm.
         *
         * @return comm
         */
        public String getComm() {
            return comm;
        }

        /**
         * Returns exe.
         *
         * @return exe
         */
        public String getExe() {
            return exe;
        }

        /**
         * Returns cmdline.
         *
         * @return cmdline
         */
        public String getCmdline() {
            return cmdline;
        }

        /**
         * Returns systemdCGroup.
         *
         * @return systemdCGroup
         */
        public String getSystemdCGroup() {
            return systemdCGroup;
        }

        /**
         * Returns systemdUnit.
         *
         * @return systemdUnit
         */
        public String getSystemdUnit() {
            return systemdUnit;
        }

        /**
         * Returns message.
         *
         * @return message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Returns pid.
         *
         * @return pid
         */
        public int getPid() {
            return pid;
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
     *
     * @param unit Filter by unit name. All journal messages are returned if unset.
     * @param cursor Initial cursor reference obtained from a log event. Seeks to tail if unspecified.
     * @param backlog Offset from the current log tail, must be greater or equals 0
     * @param count Number of messages to return, starting at the cursor position specified by backlog. -1 returns all messages and continuously streams any new ones.
     * @return String
     */
    public String getUrl(String unit, String cursor, int backlog, int count) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/system/journal/", new Parameter[] {
                new Parameter("unit", unit),
                new Parameter("cursor", cursor),
                new Parameter("backlog", backlog),
                new Parameter("count", count)
        });

        return api.fromJson(data.get("token").getAsJsonObject().get("url"), String.class);
    }
}
