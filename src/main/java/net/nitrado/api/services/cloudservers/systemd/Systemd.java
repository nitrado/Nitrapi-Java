package net.nitrado.api.services.cloudservers.systemd;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;

/**
 * This class represents a Systemd.
 */
public class Systemd {

    private transient Service service;
    private transient Nitrapi api;

    /**
     * This class represents an Unit.
     */
    public class Unit {
        @SerializedName("object_path")
        private String objectPath;
        @SerializedName("unit_state")
        private String unitState;
        private String description;
        @SerializedName("job_id")
        private int jobId;
        @SerializedName("load_state")
        private String loadState;
        private String filename;
        @SerializedName("job_type")
        private String jobType;
        @SerializedName("job_object_path")
        private String jobObjectPath;
        private String name;
        @SerializedName("active_state")
        private String activeState;
        @SerializedName("sub_state")
        private String subState;
        private String leader;

        /**
         * Returns objectPath.
         *
         * @return objectPath
         */
        public String getObjectPath() {
            return objectPath;
        }

        /**
         * Returns unitState.
         *
         * @return unitState
         */
        public String getUnitState() {
            return unitState;
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
         * Returns jobId.
         *
         * @return jobId
         */
        public int getJobId() {
            return jobId;
        }

        /**
         * Returns loadState.
         *
         * @return loadState
         */
        public String getLoadState() {
            return loadState;
        }

        /**
         * Returns filename.
         *
         * @return filename
         */
        public String getFilename() {
            return filename;
        }

        /**
         * Returns jobType.
         *
         * @return jobType
         */
        public String getJobType() {
            return jobType;
        }

        /**
         * Returns jobObjectPath.
         *
         * @return jobObjectPath
         */
        public String getJobObjectPath() {
            return jobObjectPath;
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
         * Returns todo enum.
         *
         * @return todo enum
         */
        public String getActiveState() {
            return activeState;
        }

        /**
         * Returns todo enum.
         *
         * @return todo enum
         */
        public String getSubState() {
            return subState;
        }

        /**
         * Returns leader.
         *
         * @return leader
         */
        public String getLeader() {
            return leader;
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
     * Lists all the units Systemd manages.
     *
     * @return Unit[]
     */
    public Unit[] getUnits() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/system/units/", null);

        return api.fromJson(data.get("units"), Unit[].class);
    }

    /**
     * Returns a SSE (server-send event) stream URL, which will stream changes on the Systemd services.
     *
     * @return String
     */
    public String getChangeFeedUrl() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/system/units/changefeed", null);

        return api.fromJson(data.get("token").getAsJsonObject().get("url"), String.class);
    }

    /**
     * Resets all units in failure state back to normal.
     */
    public void resetAllFailedUnits() {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/reset_all_failed", null);
    }

    /**
     * Reload the Systemd daemon.
     */
    public void reloadDeamon() {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/daemon_reload", null);
    }

    /**
     * Returns details for one Systemd unit.
     *
     * @param unitName unitName
     * @return Unit
     */
    public Unit getUnit(String unitName) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "", null);

        Unit unit = api.fromJson(data.get("unit"), Unit.class);
        return unit;
    }

    /**
     * Enables a unit so it is automatically run on startup.
     *
     * @param unitName unitName
     */
    public void enableUnit(String unitName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/enable", null);
    }

    /**
     * Disables a unit so it won't automatically run on startup.
     *
     * @param unitName unitName
     */
    public void disableUnit(String unitName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/disable", null);
    }

    /**
     * Send a POSIX signal to the process(es) running in a unit.
     *
     * @param unitName unitName
     * @param who      who
     * @param signal   signal
     */
    public void killUnit(String unitName, String who, int signal) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/kill", new Parameter[]{
                new Parameter("who", who),
                new Parameter("signal", signal)
        });
    }

    /**
     * Masks a unit, preventing its use altogether.
     *
     * @param unitName unitName
     */
    public void maskUnit(String unitName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/mask", null);
    }

    /**
     * Unmasks a unit.
     *
     * @param unitName unitName
     */
    public void unmaskUnit(String unitName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/unmask", null);
    }

    /**
     * Reload a unit.
     *
     * @param unitName unitName
     * @param replace  Replace a job that is already running
     */
    public void reloadUnit(String unitName, boolean replace) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/reload", new Parameter[]{
                new Parameter("replace", replace)
        });
    }

    /**
     * Resets the failure state of a unit back to normal.
     *
     * @param unitName unitName
     */
    public void resetUnitFailedState(String unitName) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/reset_failed", null);
    }

    /**
     * Restarts a unit.
     *
     * @param unitName unitName
     * @param replace  Replace a job that is already running
     */
    public void restartUnit(String unitName, boolean replace) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/restart", new Parameter[]{
                new Parameter("replace", replace)
        });
    }

    /**
     * Starts a unit.
     *
     * @param unitName unitName
     * @param replace  Replace a job that is already running
     */
    public void startUnit(String unitName, boolean replace) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/start", new Parameter[]{
                new Parameter("replace", replace)
        });
    }

    /**
     * Stopps a unit.
     *
     * @param unitName unitName
     * @param replace  Replace a job that is already running
     */
    public void stopUnit(String unitName, boolean replace) {
        api.dataPost("services/" + service.getId() + "/cloud_servers/system/units/" + unitName + "/stop", new Parameter[]{
                new Parameter("replace", replace)
        });
    }
}
