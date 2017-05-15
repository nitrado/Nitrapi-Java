package net.nitrado.api.common.exceptions;

/**
 * This exception is thrown when the Nitrapi is down for maintenance.
 */
public class NitrapiMaintenanceException extends NitrapiException {
    public NitrapiMaintenanceException(String message) {
        super(message);
    }
}
