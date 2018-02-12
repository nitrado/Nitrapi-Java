package net.nitrado.api.services;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import org.jetbrains.annotations.Nullable;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * A general class representing a service.
 */
public abstract class Service {
    protected transient Nitrapi api;

    public static class Status extends Value {

        public Status(String value) {
            super(value);
        }

        /**
         * The service is active and useable
         */
        public static Status ACTIVE = new Status("active");

        /**
         * The service is currently installing
         */
        public static Status INSTALLING = new Status("installing");

        /**
         * The service is suspended and can be reactivated
         */
        public static Status SUSPENDED = new Status("suspended");

        /**
         * The service is admin locked, please contact support.
         */
        public static Status ADMINLOCKED = new Status("adminlocked");

        /**
         * The service is admin locked and suspended.
         */
        public static Status ADMINLOCKED_SUSPENDED = new Status("adminlocked_suspended");

        /**
         * The service is deleted.
         */
        public static Status DELETED = new Status("deleted");

        // These statuses are set by fixServiceStatus() if suspendDate or deleteDate are in the past.
        /**
         * The service is currently being suspended.
         */
        public static Status SUSPENDING = new Status("suspending");
        /**
         * The service is currently being deleted.
         */
        public static Status DELETING = new Status("deleting");
        /**
         * The service is preordered.
         */
        public static Status PREORDERED = new Status("preordered");
    }

    private int id;
    @SerializedName("start_date")
    private GregorianCalendar startDate;
    @SerializedName("suspend_date")
    private GregorianCalendar suspendDate;
    @SerializedName("delete_date")
    private GregorianCalendar deleteDate;
    @SerializedName("user_id")
    private int userId;
    private String username;
    private String comment;
    @SerializedName("auto_extension")
    private boolean autoExtension;
    @SerializedName("auto_extension_duration")
    private Integer autoExtensionDuration;
    @SerializedName("type_human")
    private String typeHuman;
    private ServiceDetails details;
    private Role[] roles;
    private Status status;

    /**
     * Returns the id of this service.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * Returns the start date.
     *
     * @return the start date
     */
    public GregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Returns the suspend date.
     *
     * @return the suspend date
     */
    public GregorianCalendar getSuspendDate() {
        return suspendDate;
    }

    /**
     * Returns the delete date.
     *
     * @return the delete date
     */
    public GregorianCalendar getDeleteDate() {
        return deleteDate;
    }

    /**
     * Returns the user id of the service.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public String getTypeHuman() {
        return typeHuman;
    }

    /**
     * Returns details on the installed service.
     *
     * @return details on the installed service
     */
    public ServiceDetails getDetails() {
        return details;
    }

    /**
     * Returns the roles this user has for this service.
     * <p>
     * IMPORTANT: this is only set, when you called Nitrapi.getServices() and not if you called Nitrapi.getService(&lt;id&gt;)
     *
     * @return a list of roles this user has
     */
    public Role[] getRoles() {
        return roles;
    }


    private String iso2LangCodeToIso3LangCode(String iso2Code) {
        Locale locale = new Locale(iso2Code);
        return locale.getISO3Language();
    }

    public String getWebinterfaceUrl() {
        return "https://webinterface.nitrado.net/?access_token=" + api.getAccessToken() + "&language=" + iso2LangCodeToIso3LangCode(api.getLanguage()) + "&service_id=" + id;
    }

    /**
     * @return the status of this service
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return true if auto extension is activated for this service
     */
    public boolean hasAutoExtension() {
        return autoExtension;
    }

    /**
     * @return the duration of auto extension if activated
     */
    public @Nullable Integer getAutoExtensionDuration() {
        return autoExtensionDuration;
    }

    /**
     * @return the comment for this service
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return all methods that can be used for auto extending
     * @permission ROLE_OWNER
     */
    public AutoExtendMethod[] getAutoExtendMethods() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + id + "/auto_extend", null);
        return api.fromJson(data.get("auto_extend"), AutoExtendMethod[].class);
    }


    /**
     * @param method
     * @param rentalTime
     * @permission ROLE_OWNER
     */
    public void changeAutoExtendMethod(int method, int rentalTime) throws NitrapiException {
        api.dataPost("services/" + id + "/auto_extend", new Parameter[]{
                new Parameter("auto_extend_id", method),
                new Parameter("rental_time", rentalTime)
        });
    }

    /**
     * internally used.
     *
     * @param autoExtension
     */
    public void setAutoExtension(boolean autoExtension) {
        this.autoExtension = autoExtension;
    }


    /**
     * Returns the logs of this server.
     *
     * @return a Logs object
     * @permission ROLE_WEBINTERFACE_LOGS_READ
     */
    public Logs getLogs() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + getId() + "/logs", null);
        return api.fromJson(data, Logs.class);
    }

    /**
     * Returns a page of the logs of this server.
     *
     * @param page the number of the page
     * @return a Logs object
     * @permission ROLE_WEBINTERFACE_LOGS_READ
     */
    public Logs getLogs(int page) throws NitrapiException {
        JsonObject data = api.dataGet("services/" + getId() + "/logs", new Parameter[]{new Parameter("page", page + "")});
        return api.fromJson(data, Logs.class);
    }

    /**
     * Cancels the service.
     * Not supported by all product types.
     */
    public void cancel() throws NitrapiException {
        api.dataPost("services/" + getId() + "/cancel", null);
    }

    /**
     * Deletes the service.
     * You only can delete the service if it's suspended otherwise an error will be thrown.
     */
    public void delete() throws NitrapiException {
        api.dataDelete("services/" + getId(), null);
    }


    /**
     * Refreshes the service-specific data of this service.
     */
    public abstract void refresh() throws NitrapiException;

    /**
     * Used internally.
     * <p>
     * Get the service directly via the Nitrapi-Object.
     *
     * @param api reference to the api
     * @see Nitrapi#getService(int)
     * @see Nitrapi#getServices()
     */
    protected void init(Nitrapi api) throws NitrapiException {
        this.api = api;
        if (status.equals(Status.ACTIVE)) {
            refresh(); // initially load the data
        }

        fixServiceStatus();
    }

    /**
     * @param needRole
     * @return true if the user has the requested permission
     */
    public boolean hasPermission(Role needRole) {
        for (Role role : roles) {
            if (role == null) {
                // This is a new role that was not defined when this lib was build. ignore it
                continue;
            }
            if (role.equals(Role.ROLE_OWNER)) {
                return true;
            }
            if (role.equals(needRole)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the status correctly if suspendDate or deleteDate are in the past.
     */
    protected void fixServiceStatus() {
        GregorianCalendar now = new GregorianCalendar();
        if (deleteDate != null && deleteDate.before(now) && !status.equals(Service.Status.DELETED)) {
            status = Status.DELETING;
        } else if (suspendDate != null && suspendDate.before(now) && !status.equals(Service.Status.SUSPENDED) && !status.equals(Status.DELETED)) {
            status = Status.SUSPENDING;
        }
    }
}