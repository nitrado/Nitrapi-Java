package net.nitrado.api.test.mock;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.http.HttpClient;
import net.nitrado.api.common.http.Parameter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A Mock HttpClient that responds with the nextResponse JsonObject you previously passed to him
 */
public class MockHttpClient implements HttpClient {
    private Queue<JsonObject> nextResponses;
    private String lastUrl;
    private Parameter[] lastParameters;

    public MockHttpClient() {
        this.nextResponses = new LinkedBlockingQueue<JsonObject>();
    }

    /**
     * set the next response
     *
     * @param nextResponse a string containing json data
     */
    public void addNextResponse(String nextResponse) {
        this.nextResponses.add((JsonObject) new JsonParser().parse(nextResponse));
    }

    /**
     * set the next response
     *
     * @param nextResponse a json object
     */
    public void addNextResponse(JsonObject nextResponse) {
        this.nextResponses.add(nextResponse);
    }


    /**
     * set the next response
     *
     * @param filename name of a file containing json data
     */
    public void addNextResponseFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + filename)));

            StringBuffer response = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            addNextResponse(response.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // its a problem with the test, not with the api itself
        } catch (IOException e) {
            throw new RuntimeException(e); // its a problem with the test, not with the api itself
        }
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public Parameter[] getLastParameters() {
        return lastParameters;
    }


    /// MOCKED METHODS ///

    public JsonObject dataGet(String url, String accessToken, Parameter[] parameters) {
        if (this.nextResponses.isEmpty()) {
            throw new IllegalStateException("not enough nextResponses provided for call to " + url);
        }
        this.lastUrl = url;
        this.lastParameters = parameters;

        JsonObject res = this.nextResponses.poll();
        if (!res.get("status").getAsString().equals("success")) {
            throw new NitrapiErrorException(res.get("message").getAsString(), 200);
        }

        if (res.has("data")) {
            return res.get("data").getAsJsonObject();
        }

        return res;
    }

    public JsonObject dataPost(String url, String accessToken, Parameter[] parameters) {
        if (this.nextResponses.isEmpty()) {
            throw new IllegalStateException("not enough nextResponses provided for call to " + url);
        }
        this.lastUrl = url;
        this.lastParameters = parameters;

        JsonObject res = this.nextResponses.poll();
        if (!res.get("status").getAsString().equals("success")) {
            throw new NitrapiErrorException(res.get("message").getAsString(), 200);
        }

        if (res.has("data")) {
            return res.get("data").getAsJsonObject();
        }

        return res;
    }

    public JsonObject dataDelete(String url, String accessToken, Parameter[] parameters) {
        if (this.nextResponses.isEmpty()) {
            throw new IllegalStateException("not enough nextResponses provided for call to " + url);
        }
        this.lastUrl = url;
        this.lastParameters = parameters;

        JsonObject res = this.nextResponses.poll();
        if (!res.get("status").getAsString().equals("success")) {
            throw new NitrapiErrorException(res.get("message").getAsString(), 200);
        }

        if (res.has("data")) {
            return res.get("data").getAsJsonObject();
        }

        return res;
    }

    public InputStream rawGet(String url) {
        return null;
    }

    public void rawPost(String url, String token, byte[] body) {

    }

    public int getRateLimit() {
        return 0;
    }

    public int getRateLimitRemaining() {
        return 0;
    }

    public long getRateLimitReset() {
        return 0;
    }


    private String locale;
    public void setLanguage(String lang) {
        locale = lang;
    }

    public String getLanguage() {
        return locale;
    }
}
