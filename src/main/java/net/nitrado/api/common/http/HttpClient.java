package net.nitrado.api.common.http;

import com.google.gson.JsonObject;
import net.nitrado.api.common.exceptions.NitrapiException;

import java.io.InputStream;

/**
 * Interface for a client to get data from the api.
 */
public interface HttpClient {
    /**
     * Creates a GET-Request.
     *
     * @param url         URL to call
     * @param accessToken access token of the current user
     * @param parameters  parameters
     * @return the result as a JsonObject
     */
    JsonObject dataGet(String url, String accessToken, Parameter[] parameters) throws NitrapiException;

    /**
     * Creates a POST-Request.
     *
     * @param url         URL to call
     * @param accessToken access token of the current user
     * @param parameters  parameters
     * @return the result as a JsonObject
     */
    JsonObject dataPost(String url, String accessToken, Parameter[] parameters) throws NitrapiException;

    /**
     * Creates a PUT-Request.
     *
     * @param url         URL to call
     * @param accessToken access token of the current user
     * @param parameters  parameters
     * @return the result as a JsonObject
     */
    JsonObject dataPut(String url, String accessToken, Parameter[] parameters) throws NitrapiException;

    /**
     * Creates a DELETE-Request.
     *
     * @param url         URL to call
     * @param accessToken access token of the current user
     * @param parameters  parameters
     * @return the result as a JsonObject
     */
    JsonObject dataDelete(String url, String accessToken, Parameter[] parameters) throws NitrapiException;

    /**
     * Creates a GET-Request and returns the raw InputStream.
     *
     * @param url URL to call
     * @return the InputStream of the response
     */
    InputStream rawGet(String url) throws NitrapiException;

    /**
     * Creates a POST-Request and returns the raw InputStream.
     *
     * @param url   URL to call
     * @param token access token of the current user
     * @param body  body of the POST-REQUEST
     */
    void rawPost(String url, String token, byte[] body) throws NitrapiException;

    /**
     * Returns the total rate limit for the current user.
     *
     * @return the total rate limit
     */
    int getRateLimit();

    /**
     * Returns the remaining rate limit for the current user.
     *
     * @return the remaining rate limit
     */
    int getRateLimitRemaining();

    /**
     * Returns the date when the rate limit for the current user will reset.
     *
     * @return the reset-date
     */
    long getRateLimitReset();

    void setLanguage(String lang);

    String getLanguage();
}
