package net.nitrado.api.services.gameservers.taskmanager;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;

/**
 * Information about a scheduled task.
 */
public class Task {
    /**
     * Type of the action.
     */
    public enum ActionType {
        /**
         * Restarts the gameserver.
         */
        @SerializedName("restart")
        RESTART,
        /**
         * Stops the gameserver.
         */
        @SerializedName("stop")
        STOP,
        /**
         * Starts the gameserver.
         */
        @SerializedName("start")
        START;

        @Override
        public String toString() {
            try {
                return ActionType.class.getDeclaredField(super.toString()).getAnnotation(SerializedName.class).value();
            } catch (NoSuchFieldException e) {
                // should not happen
                return super.toString();
            }
        }
    }

    private int id;
    @SerializedName("service_id")
    private int serviceId;
    private String minute;
    private String hour;
    private String day;
    private String month;
    private String weekday;
    @SerializedName("next_run")
    private GregorianCalendar nextRun;
    @SerializedName("last_run")
    private GregorianCalendar lastRun;
    @SerializedName("action_method")
    private ActionType actionMethod;
    @SerializedName("action_data")
    private String actionData;

    /**
     * Returns the id of this task.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the id of the service running this task.
     *
     * @return the id of the service
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Returns the minutes this task will run.
     *
     * @return minutes in cron format
     */
    public String getMinute() {
        return minute;
    }

    /**
     * Returns the hours this task will run.
     *
     * @return hours in cron format
     */
    public String getHour() {
        return hour;
    }

    /**
     * Returns the days this task will run.
     *
     * @return days in cron format
     */
    public String getDay() {
        return day;
    }

    /**
     * Returns the months this task will run.
     *
     * @return months in cron format
     */
    public String getMonth() {
        return month;
    }

    /**
     * Returns the weekdays this task will run.
     *
     * @return weekdays in cron format
     */
    public String getWeekday() {
        return weekday;
    }

    /**
     * Returns the next time this task will run.
     *
     * @return the date of the next run
     */
    public GregorianCalendar getNextRun() {
        return nextRun;
    }

    /**
     * Returns the last time this task ran.
     *
     * @return the date of the last run
     */
    public GregorianCalendar getLastRun() {
        return lastRun;
    }

    /**
     * Returns the method run by this task.
     *
     * @return the type of action
     */
    public ActionType getActionMethod() {
        return actionMethod;
    }

    /**
     * Returns the optional message displayed on restart or stop tasks.
     *
     * @return the message
     */
    public String getActionData() {
        return actionData;
    }
}
