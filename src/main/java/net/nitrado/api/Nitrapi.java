package net.nitrado.api;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.HttpClient;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.common.http.ProductionHttpClient;
import net.nitrado.api.customer.Customer;
import net.nitrado.api.customer.SSHKeys;
import net.nitrado.api.order.Dimension;
import net.nitrado.api.payment.Country;
import net.nitrado.api.payment.PaymentMethod;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.ServiceFactory;
import net.nitrado.api.services.cloudservers.CloudServer;
import net.nitrado.api.services.gameservers.GlobalGameList;
import net.nitrado.api.services.gameservers.minecraft.Minecraft;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Main class to interact with the Nitrapi.
 * <p>
 * You create it by passing it the access token of the user.
 * The next step is to get a list of services and then operate further using the service-specific classes.
 * <p>
 * Every call on this api can throw a NitrapiHttpException when there is a problem connection to the api (e.g. the pc
 * has no internet connection)
 * and a NitrapiErrorException if there is a problem using the api (e.g. not enough permissions)
 */
public class Nitrapi {
    /**
     * Base url of the api.
     */
    public static final String NITRAPI_LIVE_URL = "https://api.nitrado.net/";

    private HttpClient client;

    private String accessToken;
    private String nitrapiUrl;

    /**
     * Gson object to convert the json-data
     */
    private Gson gson;

    private String applicationName = "nitrapi";

    /**
     * Creates a new Nitrapi with the given access token.
     *
     * @param accessToken access token for the user
     */
    public Nitrapi(String accessToken) {
        this(accessToken, NITRAPI_LIVE_URL);
    }

    /**
     * Creates a new Nitrapi with the given access token and url.
     *
     * @param accessToken access token for the user
     * @param nitrapiUrl  url at which nitrapi is listening
     */
    public Nitrapi(String accessToken, String nitrapiUrl) {
        this.accessToken = accessToken;
        this.client = new ProductionHttpClient();
        this.nitrapiUrl = nitrapiUrl;

        TypeAdapter<Boolean> booleanAsIntAdapter = new TypeAdapter<Boolean>() {
            @Override
            public void write(JsonWriter out, Boolean value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(value);
                }
            }

            @Override
            public Boolean read(JsonReader in) throws IOException {
                JsonToken peek = in.peek();
                switch (peek) {
                    case BOOLEAN:
                        return in.nextBoolean();
                    case NULL:
                        in.nextNull();
                        return null;
                    case NUMBER:
                        return in.nextInt() != 0;
                    case STRING:
                        return Boolean.parseBoolean(in.nextString());
                    default:
                        throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
                }
            }
        };

        this.gson = new GsonBuilder()
                .registerTypeAdapter(GregorianCalendar.class, new JsonDeserializer<GregorianCalendar>() {
                    public GregorianCalendar deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

                        GregorianCalendar calendar = (GregorianCalendar) (GregorianCalendar.getInstance());

                        try {
                            String value = json.getAsString();
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            format.setLenient(false);
                            Date date = format.parse(value);
                            calendar.setTime(date);
                            return calendar;
                        } catch (JsonParseException e) {
                            throw new IllegalStateException("Invalid Json format to convert Calendar: " + e.getMessage());
                        } catch (ParseException e) {
                            throw new IllegalStateException("Error to convert Calendar: " + e.getMessage());
                        }
                    }
                })
                .registerTypeAdapter(Dimension.DimensionValues.class, new Dimension.DimensionValuesDeserializer())
                .registerTypeAdapter(boolean.class, booleanAsIntAdapter)
                .registerTypeAdapter(Boolean.class, booleanAsIntAdapter)
                .registerTypeHierarchyAdapter(Value.class, new JsonDeserializer<Value>() {
                    public Value deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        if (jsonElement == null) {
                            return null;
                        }
                        String value = jsonElement.getAsString();

                        if (type instanceof Class) {
                            try {
                                return (Value) ((Class) type).getConstructor(String.class).newInstance(value);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                })
                .create();
    }


    /**
     * Returns the current customer.
     *
     * @return the current customer
     */
    public Customer getCustomer() throws NitrapiException {
        JsonObject data = dataGet("user", null);

        Customer customer = fromJson(data.get("user"), Customer.class);
        customer.init(this);
        return customer;
    }


    /**
     * Returns a specific service.
     *
     * @param id id of the service
     * @return the service specified by the id
     */
    public Service getService(int id) throws NitrapiException {
        JsonObject data = this.dataGet("services/" + id, null);
        return ServiceFactory.factory(this, data.get("service"));
    }


    /**
     * Returns a list of all services.
     *
     * @return a list of all services for the current user
     */
    public Service[] getServices() throws NitrapiException {
        JsonObject data = this.dataGet("services", null);
        JsonArray serviceArray = data.get("services").getAsJsonArray();
        ArrayList<Service> services = new ArrayList<Service>();
        for (int i = 0; i < serviceArray.size(); i++) {
            try {
                services.add(ServiceFactory.factory(this, serviceArray.get(i)));
            } catch (NitrapiErrorException exception) {
                exception.printStackTrace();
            }
        }
        return services.toArray(new Service[services.size()]);
    }

    /**
     * Returns true if the api is operating as expected.
     *
     * @return true if the api is working. otherwise an exception is thrown
     */
    public boolean ping() throws NitrapiException {
        this.dataGet("ping", null);
        return true;
    }

    /**
     * Returns the full list of games.
     * <p>
     * Contains all available games.
     *
     * @return a GlobalGameList object
     */
    public GlobalGameList getGames() throws NitrapiException {
        return GlobalGameList.newInstance(this);
    }


    /**
     * Returns the full list of available images.
     *
     * @return
     */
    public CloudServer.Image[] getImages() throws NitrapiException {
        JsonObject data = dataGet("information/cloud_servers/images", null);

        CloudServer.Image[] images = fromJson(data.get("images"), CloudServer.Image[].class);
        return images;
    }

    // PAYMENT

    public Country[] getPaymentCountries() throws NitrapiException {
        JsonObject data = this.dataGet("order/payment/countries", null);
        return gson.fromJson(data.get("countries"), Country[].class);
    }

    public PaymentMethod[] getPaymentMethods() throws NitrapiException {
        JsonObject data = this.dataGet("order/payment/payment_methods", null);

        data = data.get("payment_methods").getAsJsonObject();
        ArrayList<PaymentMethod> methods = new ArrayList<PaymentMethod>(data.entrySet().size());
        for (Map.Entry<String, JsonElement> entry : data.entrySet()) {
            PaymentMethod method = fromJson(entry.getValue(), PaymentMethod.class);
            method.setId(entry.getKey());
            methods.add(method);
        }

        return methods.toArray(new PaymentMethod[0]);
    }

    public SSHKeys getSSHKeys() throws NitrapiException {
        JsonObject data = dataGet("user/ssh_keys", null);

        SSHKeys keys = fromJson(data, SSHKeys.class);
        keys.init(this);
        return keys;
    }

    public AccessToken getAccessTokenInfo() throws NitrapiException {
        JsonObject data = this.dataGet("token", null);
        return gson.fromJson(data.get("token"), AccessToken.class);
    }

    public HashMap<String, String> getCountries() throws NitrapiException {
        JsonObject data = this.dataGet("countries", null);
        return gson.fromJson(data.get("countries"), new TypeToken<Map<String, String>>(){}.getType());
    }

    public HashMap<String, String> getStates() throws NitrapiException {
        JsonObject data = this.dataGet("countries/states", null);
        return gson.fromJson(data.get("states"), new TypeToken<Map<String, String>>(){}.getType());
    }

    public HashMap<String, String[]> getTimezones() throws NitrapiException {
        JsonObject data = this.dataGet("timezones", null);
        return gson.fromJson(data.get("timezones"), new TypeToken<Map<String, String[]>>(){}.getType());
    }


    /**
     * Returns the current limit of requests per hour for each user.
     *
     * @return the current limit of requests
     */
    public int getRateLimit() {
        return client.getRateLimit();
    }

    /**
     * Returns the remaining requests for this hour and the current user.
     *
     * @return the remaining requests
     */
    public int getRateLimitRemaining() {
        return client.getRateLimitRemaining();
    }

    /**
     * Returns the date when the limit resets for the current user.
     *
     * @return the date when the limit resets
     */
    public long getRateLimitReset() {
        return client.getRateLimitReset();
    }


    /**
     * Changes the language of error messages
     *
     * @param lang two char language code
     */
    public void setLanguage(String lang) {
        client.setLanguage(lang);
    }

    public String getLanguage() {
        return client.getLanguage();
    }

    // http client stuff

    /**
     * Used internally.
     * <p>
     * Creates a GET-Request.
     *
     * @param url        URL to call
     * @param parameters parameters
     * @return the result as a JsonObject
     */
    public JsonObject dataGet(String url, Parameter[] parameters) throws NitrapiException {
        return client.dataGet(nitrapiUrl + url, accessToken, parameters);
    }

    /**
     * Used internally.
     * <p>
     * Creates a POST-Request.
     *
     * @param url        URL to call
     * @param parameters parameters
     * @return the result as a JsonObject
     */
    public JsonObject dataPost(String url, Parameter[] parameters) throws NitrapiException {
        return client.dataPost(nitrapiUrl + url, accessToken, parameters);
    }

    /**
     * Used internally.
     * <p>
     * Creates a PUT-Request.
     *
     * @param url        URL to call
     * @param parameters parameters
     * @return the result as a JsonObject
     */
    public JsonObject dataPut(String url, Parameter[] parameters) throws NitrapiException {
        return client.dataPut(nitrapiUrl + url, accessToken, parameters);
    }

    /**
     * Used internally.
     * <p>
     * Creates a DELETE-Request.
     *
     * @param url        URL to call
     * @param parameters parameters
     * @return the result as a JsonObject
     */
    public JsonObject dataDelete(String url, Parameter[] parameters) throws NitrapiException {
        return client.dataDelete(nitrapiUrl + url, accessToken, parameters);
    }

    /**
     * Used internally.
     * <p>
     * Creates a GET-Request and returns the raw InputStream.
     *
     * @param url URL to call
     * @return the InputStream of the response
     */
    public InputStream rawGet(String url) throws NitrapiException {
        return client.rawGet(url);
    }

    /**
     * Used internally.
     * <p>
     * Creates a POST-Request and returns the raw InputStream.
     *
     * @param url   URL to call
     * @param token access token of the current user
     * @param body  body of the POST-REQUEST
     */

    public void rawPost(String url, String token, byte[] body) throws NitrapiException {
        client.rawPost(url, token, body);
    }

    /**
     * This method deserializes the Json read from the specified parse tree into an object of the specified type.
     *
     * @param json   the root of the parse tree of JsonElements from which the object is to be deserialized
     * @param tClass The class of T
     * @param <T>    the type of the desired object
     * @return an object of type T from the json. Returns null if json is null.
     */
    public <T> T fromJson(JsonElement json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    /**
     * Changes the HttpClient.
     *
     * @param httpClient new http client to use
     */
    public void setHttpClient(HttpClient httpClient) {
        this.client = httpClient;
    }

    /**
     * Changes the url at which the Nitrapi is.
     *
     * @param url nitrapi url
     */
    public void changeNitrapiUrl(String url) {
        this.nitrapiUrl = url;
    }

    /**
     * Changes the name of the application displayed in logs.
     *
     * @param name name of this application
     */
    public void setApplicationName(String name) {
        this.applicationName = name;
    }

    /**
     * Returns the name of the application displayed in logs.
     *
     * @return the name of this application
     */
    public String getApplicationName() {
        return applicationName;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
