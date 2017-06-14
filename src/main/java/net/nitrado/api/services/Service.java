package net.nitrado.api.services;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.http.Parameter;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * A general class representing a service.
 */
public abstract class Service {
    protected transient Nitrapi api;

    public enum Status {
        /**
         * The service is active and useable
         */
        @SerializedName("active")
        ACTIVE,
        /**
         * The service is currently installing
         */
        @SerializedName("installing")
        INSTALLING,
        /**
         * The service is suspended and can be reactivated
         */
        @SerializedName("suspended")
        SUSPENDED,
        /**
         * The service is admin locked, please contact support.
         */
        @SerializedName("adminlocked")
        ADMINLOCKED,
        /**
         * The service is admin locked and suspended.
         */
        @SerializedName("adminlocked_suspended")
        ADMINLOCKED_SUSPENDED,
        /**
         * The service is deleted.
         */
        @SerializedName("deleted")
        DELETED
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


    private String iso2LangCodeToIso3LangCode(String iso2Code){
        Locale locale = new Locale(iso2Code);
        return locale.getISO3Language();
    }

    public String getWebinterfaceUrl() {
        return "https://webinterface.nitrado.net/?access_token=" + api.getAccessToken() + "&language="+iso2LangCodeToIso3LangCode(api.getLanguage())+"&service_id=" + id;
    }

    /**
     * @return the status of this service
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @return true if auto extension is activated for this service
     */
    public boolean hasAutoExtension() {
        return autoExtension;
    }

    /**
     *
     * @return the comment for this service
     */
    public String getComment() {
        return comment;
    }

    /**
     * @permission  ROLE_OWNER
     * @return all methods that can be used for auto extending
     */
    public AutoExtendMethod[] getAutoExtendMethods() {
        JsonObject data = api.dataGet("services/" + id + "/auto_extend", null);
        return api.fromJson(data.get("auto_extend"), AutoExtendMethod[].class);
    }


    /**
     * @permission  ROLE_OWNER
     * @param method
     * @param rentalTime
     */
    public void changeAutoExtendMethod(int method, int rentalTime) {
        api.dataPost("services/" + id + "/auto_extend", new Parameter[]{
                new Parameter("auto_extend_id", method),
                new Parameter("rental_time", rentalTime)
        });
    }

    /**
     * internally used.
     * @param autoExtension
     */
    public void setAutoExtension(boolean autoExtension) {
        this. autoExtension = autoExtension;
    }


    /**
     * Returns the logs of this server.
     *
     * @permission ROLE_WEBINTERFACE_LOGS_READ
     * @return a Logs object
     */
    public Logs getLogs() {
        JsonObject data = api.dataGet("services/" + getId() + "/logs", null);
        return api.fromJson(data, Logs.class);
    }

    /**
     * Returns a page of the logs of this server.
     *
     * @permission ROLE_WEBINTERFACE_LOGS_READ
     * @param page the number of the page
     * @return a Logs object
     */
    public Logs getLogs(int page) {
        JsonObject data = api.dataGet("services/" + getId() + "/logs", new Parameter[]{new Parameter("page", page + "")});
        return api.fromJson(data, Logs.class);
    }

    /**
     * Refreshes the service-specific data of this service.
     */
    public abstract void refresh();

    /**
     * Used internally.
     * <p>
     * Get the service directly via the Nitrapi-Object.
     *
     * @see Nitrapi#getService(int)
     * @see Nitrapi#getServices()
     * @param api reference to the api
     */
    protected void init(Nitrapi api) {
        this.api = api;
        if (status == Status.ACTIVE) {
            refresh(); // initially load the data
        }
    }

    /**
     *
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
}