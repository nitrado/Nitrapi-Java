package net.nitrado.api.services;

import com.google.gson.JsonElement;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiErrorException;

/**
 * A class to create a specific service-class from json data.
 */
public class ServiceFactory {
    /**
     * Returns an object of the specific subclass for the given service.
     *
     * @param api  handle of the api
     * @param data json-data containing a single service object
     * @return an object of a subclass of service
     */
    public static Service factory(Nitrapi api, JsonElement data) {

        // build the class name for the service
        String type = data.getAsJsonObject().get("type").getAsString();
        String className = "net.nitrado.api.services." + type.toLowerCase().replace("_", "") + "s." + toCamelCase(type);

        try {

            // create the base service object first
            Service service = (Service) api.fromJson(data, Class.forName(className));
            service.init(api);

            return service;

        } catch (ClassNotFoundException e) {
            throw new NitrapiErrorException("Service " + className + " not supported.");
        } catch (ClassCastException e) {
            throw new NitrapiErrorException(className + " has to be a subclass of Service.");
        }
    }

    private static String toCamelCase(String s) {
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    private static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}
