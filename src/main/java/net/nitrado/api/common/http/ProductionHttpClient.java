package net.nitrado.api.common.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import net.nitrado.api.common.exceptions.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * HttpClient that actually connects to the internet and gets the data.
 */
public class ProductionHttpClient implements HttpClient {

    private int rateLimit;
    private int rateLimitRemaining;
    private long rateLimitReset;
    private String locale = "en";

    private Parameter[] additionalHeaders;

    public ProductionHttpClient() {

    }

    public ProductionHttpClient(Parameter[] additionalHeaders) {
        this.additionalHeaders = additionalHeaders;
    }



    public JsonObject dataGet(String url, String accessToken, Parameter[] parameters) throws NitrapiException {

        // create the full url string with parameters
        boolean first = true;
        StringBuilder fullUrl = new StringBuilder();
        fullUrl.append(url);
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                if (parameter.getValue() != null) {
                    fullUrl.append(first ? "?" : "&");
                    fullUrl.append(parameter.getKey());
                    fullUrl.append("=");
                    try {
                        fullUrl.append(URLEncoder.encode(parameter.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        // everyone should support utf-8 so this should not happen
                        e.printStackTrace();
                    }
                    first = false;
                }
            }
        }
        fullUrl.append(first ? "?" : "&");
        fullUrl.append("locale");
        fullUrl.append("=");
        fullUrl.append(locale);

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl.toString()).openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            if (additionalHeaders != null) {
                for (Parameter parameter : additionalHeaders) {
                    connection.setRequestProperty(parameter.getKey(), parameter.getValue());
                }
            }

            BufferedReader reader;
            if (connection.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuffer response = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return parseResult(response, connection);

        } catch (IOException e) {
            throw new NitrapiHttpException(e);
        }
    }


    public JsonObject dataPost(String url, String accessToken, Parameter[] parameters) throws NitrapiException {
        String params = prepareParameterString(parameters);

        url += "?locale=" + locale;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            if (additionalHeaders != null) {
                for (Parameter parameter : additionalHeaders) {
                    connection.setRequestProperty(parameter.getKey(), parameter.getValue());
                }
            }

            // write post parameters
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();


            BufferedReader reader;
            if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuffer response = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return parseResult(response, connection);
        } catch (IOException e) {
            throw new NitrapiHttpException(e);
        }
    }

    public JsonObject dataPut(String url, String accessToken, Parameter[] parameters) throws NitrapiException {
        String params = prepareParameterString(parameters);

        url += "?locale=" + locale;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            if (additionalHeaders != null) {
                for (Parameter parameter : additionalHeaders) {
                    connection.setRequestProperty(parameter.getKey(), parameter.getValue());
                }
            }

            // write post parameters
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();


            BufferedReader reader;
            if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuffer response = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return parseResult(response, connection);
        } catch (IOException e) {
            throw new NitrapiHttpException(e);
        }
    }


    public JsonObject dataDelete(String url, String accessToken, Parameter[] parameters) throws NitrapiException {
        String params = prepareParameterString(parameters);

        url += "?locale=" + locale;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            if (additionalHeaders != null) {
                for (Parameter parameter : additionalHeaders) {
                    connection.setRequestProperty(parameter.getKey(), parameter.getValue());
                }
            }

            // write post parameters
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();


            BufferedReader reader;
            if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return parseResult(response, connection);
        } catch (IOException e) {
            throw new NitrapiHttpException(e);
        }
    }

    public InputStream rawGet(String url) throws NitrapiException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            return connection.getInputStream();
        } catch (IOException e) {
            throw new NitrapiHttpException(e);
        }
    }

    public void rawPost(String url, String token, byte[] body) throws NitrapiException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Token", token);
            connection.setRequestProperty("Content-Type", "application/binary");

            // write post parameters
            connection.getOutputStream().write(body);
            connection.getOutputStream().close();


            BufferedReader reader;
            if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuffer response = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            parseResult(response, connection);

        } catch (IOException e) {
            throw new NitrapiHttpException(e);
        }
    }


    private String prepareParameterString(Parameter[] parameters) {
        // create POST parameter string
        StringBuilder params = new StringBuilder();
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                addParameter(params, parameter);
            }
        }
        return params.toString();
    }

    private void addParameter(StringBuilder params, Parameter parameter) {
        if (parameter.getKey() == null) {
            // Add subParameters
            for (Parameter subParameter: parameter.getSubParameters()) {
                addParameter(params, subParameter);
            }
        }

        if (parameter.getValue() != null) {
            params.append("&");
            params.append(parameter.getKey());
            params.append("=");
            try {
                params.append(URLEncoder.encode(parameter.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // everyone should support utf-8 so this should not happen
                e.printStackTrace();
            }
        }
    }

    private JsonObject parseResult(StringBuffer response, HttpURLConnection connection) throws IOException, NitrapiException {

        if (connection.getHeaderField("X-Rate-Limit") != null) {
            rateLimit = Integer.parseInt(connection.getHeaderField("X-RateLimit-Limit"));
            rateLimitRemaining = Integer.parseInt(connection.getHeaderField("X-RateLimit-Remaining"));
            rateLimitReset = Long.parseLong(connection.getHeaderField("X-RateLimit-Reset"));
        }

        String errorId = null;
        if (connection.getHeaderField("X-Raven-Event-ID") != null) {
            errorId = connection.getHeaderField("X-Raven-Event-ID");
        }

        if (response.length() == 0) {
            if (connection.getResponseCode() < 300) { // OK
                return null;
            }
            throw new NitrapiHttpException(new NitrapiErrorException("Empty result. (HTTP " + connection.getResponseCode() + ")", errorId));
        }

        JsonParser parser = new JsonParser();
        JsonObject result;
        try {
            result = (JsonObject) parser.parse(response.toString());
        } catch (JsonSyntaxException e) {
            // invalid json
            result =  new JsonObject();
            result.addProperty("message", "Invalid json: " + response.toString());
        }


        if (connection.getResponseCode() < 300) { // OK
            // return the interesting subobject
            if (result.get("data") != null) {
                return result.get("data").getAsJsonObject();
            }

            return result;
        }

        // Throw appropriate exception

        String message = null;
        if (result.has("message")) {
            message = result.get("message").getAsString();
        }


        switch (connection.getResponseCode()) {
            case 401:
                throw new NitrapiAccessTokenInvalidException(message);
            case 428:
                throw new NitrapiConcurrencyException(message);
            case 503:
                throw new NitrapiMaintenanceException(message);
            default:
                throw new NitrapiErrorException(message, errorId);
        }
    }


    public int getRateLimit() {
        return rateLimit;
    }

    public int getRateLimitRemaining() {
        return rateLimitRemaining;
    }

    public long getRateLimitReset() {
        return rateLimitReset;
    }

    public void setLanguage(String lang) {
        locale = lang;
    }

    public String getLanguage() {
        return locale;
    }
}
