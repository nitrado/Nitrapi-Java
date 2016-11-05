package net.nitrado.api.services.gameservers.taskmanager;

import com.google.gson.JsonObject;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.gameservers.Gameserver;

/**
 * Manage scheduled tasks for a gameserver.
 */
public class TaskManager {
    private transient Gameserver service;
    private transient Nitrapi api;

    /**
     * Used internally.
     * <p>
     * Call Gameserver.getTaskManager() instead.
     *
     * @see Gameserver#getTaskManager()
     * @param service gameserver object
     * @param api reference to the api
     */
    public TaskManager(Gameserver service, Nitrapi api) {
        this.service = service;
        this.api = api;
    }

    /**
     * Returns all scheduled tasks for the service.
     *
     * @permission ROLE_WEBINTERFACE_SCHEDULED_RESTART_READ
     * @return array of ScheduledTasks
     */
    public Task[] getScheduledTasks() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/tasks", null);
        return api.fromJson(data.get("tasks"), Task[].class);
    }

    /**
     * Creates a new scheduled task for the service.
     *
     * @permission ROLE_WEBINTERFACE_SCHEDULED_RESTART_WRITE
     * @param minute  minutes in cron format
     * @param hour    hours in cron format
     * @param day     days in cron format
     * @param month   months in cron format
     * @param weekday weekdays in cron format
     * @param method  type of action to be run
     * @param message optional message for restart or stop
     */
    public void createTask(String minute, String hour, String day, String month, String weekday, Task.ActionType method, String message) {
        Parameter[] params = new Parameter[]{
                new Parameter("minute", minute),
                new Parameter("hour", hour),
                new Parameter("day", day),
                new Parameter("month", month),
                new Parameter("weekday", weekday),
                new Parameter("action_method", method.toString()),
                new Parameter("action_data", message)
        };
        api.dataPost("services/" + service.getId() + "/gameservers/tasks", params);
    }

    /**
     * Updates an existing scheduled task for the service.
     *
     * @permission ROLE_WEBINTERFACE_SCHEDULED_RESTART_READ
     * @param id      id of the existing scheduled task
     * @param minute  minutes in cron format
     * @param hour    hours in cron format
     * @param day     days in cron format
     * @param month   months in cron format
     * @param weekday weekdays in cron format
     * @param method  type of action to be run
     * @param message optional message for restart or stop
     */
    public void updateTask(int id, String minute, String hour, String day, String month, String weekday, Task.ActionType method, String message) {
        Parameter[] params = new Parameter[]{
                new Parameter("minute", minute),
                new Parameter("hour", hour),
                new Parameter("day", day),
                new Parameter("month", month),
                new Parameter("weekday", weekday),
                new Parameter("action_method", method.toString()),
                new Parameter("action_data", message)
        };
        api.dataPost("services/" + service.getId() + "/gameservers/tasks/" + id, params);
    }

    /**
     * Deletes the specific scheduled task for the service.
     *
     * @permission ROLE_WEBINTERFACE_SCHEDULED_RESTART_READ
     * @param id id of the task
     */
    public void deleteTask(int id) {
        api.dataDelete("services/" + service.getId() + "/gameservers/tasks/" + id, null);
    }


}
