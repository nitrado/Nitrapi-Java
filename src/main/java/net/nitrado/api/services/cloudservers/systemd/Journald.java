package net.nitrado.api.services.cloudservers.systemd;

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
        private Integer realtimeTimestamp;
        @SerializedName("__MONOTONIC_TIMESTAMP")
        private Integer monotonicTimestamp;
        @SerializedName("_BOOT_ID")
        private String bootId;
        @SerializedName("PRIORITY")
        private Integer priority;
        @SerializedName("_UID")
        private Integer uid;
        @SerializedName("_GID")
        private Integer gid;
        @SerializedName("_SYSTEMD_SLICE")
        private String systemdSlice;
        @SerializedName("_MACHINE_ID")
        private String machineId;
        @SerializedName("_HOSTNAME")
        private String hostname;
        @SerializedName("_TRANSPORT")
        private String transport;
        @SerializedName("SYSLOG_FACILITY")
        private Integer syslogFacility;
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
        private Integer pid;

        /**
         * Returns cursor.
         *
         * @return cursor
         */
        @Nullable
        public String getCursor() {
            return cursor;
        }

        /**
         * Returns realtimeTimestamp.
         *
         * @return realtimeTimestamp
         */
        @Nullable
        public Integer getRealtimeTimestamp() {
            return realtimeTimestamp;
        }

        /**
         * Returns monotonicTimestamp.
         *
         * @return monotonicTimestamp
         */
        @Nullable
        public Integer getMonotonicTimestamp() {
            return monotonicTimestamp;
        }

        /**
         * Returns bootId.
         *
         * @return bootId
         */
        @Nullable
        public String getBootId() {
            return bootId;
        }

        /**
         * Returns priority.
         *
         * @return priority
         */
        @Nullable
        public Integer getPriority() {
            return priority;
        }

        /**
         * Returns uid.
         *
         * @return uid
         */
        @Nullable
        public Integer getUid() {
            return uid;
        }

        /**
         * Returns gid.
         *
         * @return gid
         */
        @Nullable
        public Integer getGid() {
            return gid;
        }

        /**
         * Returns systemdSlice.
         *
         * @return systemdSlice
         */
        @Nullable
        public String getSystemdSlice() {
            return systemdSlice;
        }

        /**
         * Returns machineId.
         *
         * @return machineId
         */
        @Nullable
        public String getMachineId() {
            return machineId;
        }

        /**
         * Returns hostname.
         *
         * @return hostname
         */
        @Nullable
        public String getHostname() {
            return hostname;
        }

        /**
         * Returns transport.
         *
         * @return transport
         */
        @Nullable
        public String getTransport() {
            return transport;
        }

        /**
         * Returns syslogFacility.
         *
         * @return syslogFacility
         */
        @Nullable
        public Integer getSyslogFacility() {
            return syslogFacility;
        }

        /**
         * Returns syslogIdentifier.
         *
         * @return syslogIdentifier
         */
        @Nullable
        public String getSyslogIdentifier() {
            return syslogIdentifier;
        }

        /**
         * Returns comm.
         *
         * @return comm
         */
        @Nullable
        public String getComm() {
            return comm;
        }

        /**
         * Returns exe.
         *
         * @return exe
         */
        @Nullable
        public String getExe() {
            return exe;
        }

        /**
         * Returns cmdline.
         *
         * @return cmdline
         */
        @Nullable
        public String getCmdline() {
            return cmdline;
        }

        /**
         * Returns systemdCGroup.
         *
         * @return systemdCGroup
         */
        @Nullable
        public String getSystemdCGroup() {
            return systemdCGroup;
        }

        /**
         * Returns systemdUnit.
         *
         * @return systemdUnit
         */
        @Nullable
        public String getSystemdUnit() {
            return systemdUnit;
        }

        /**
         * Returns message.
         *
         * @return message
         */
        @Nullable
        public String getMessage() {
            return message;
        }

        /**
         * Returns pid.
         *
         * @return pid
         */
        @Nullable
        public Integer getPid() {
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
     * @param backlog Offset from the current log tail, must be >= 0
     * @param count Number of messages to return, starting at the cursor position specified by backlog. -1 returns all messages and continuously streams any new ones.
     * @return String
     */
    public String getUrl(@Nullable String unit, @Nullable String cursor, @Nullable Integer backlog, @Nullable Integer count) throws NitrapiException {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/system/journal/", new Parameter[] {
            new Parameter("unit", unit),
            new Parameter("cursor", cursor),
            new Parameter("backlog", backlog),
            new Parameter("count", count)
        });

        String tokenurl = api.fromJson(data.get("token").getAsJsonObject().get("url"), String.class);
        return tokenurl;
    }

    /**
     * @return JournalEntry[]
     */
    public JournalEntry[] getJournal() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/system/journal/", null);

        JournalEntry[] journal = api.fromJson(data.get("journal"), JournalEntry[].class);
        return journal;
    }
}
