package net.nitrado.api.common.exceptions;

/**
 * This exception is thrown when an action is already running.
 */
public class NitrapiConcurrencyException extends NitrapiException {
    public NitrapiConcurrencyException(String message) {
        super(message);
    }
}
