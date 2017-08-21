package net.nitrado.api.common.exceptions;

/**
 * This is a superclass of NitrapiHttpException, NitrapiConcurrencyException, NitrapiMaintenanceException and
 * NitrapiErrorException so that you can catch all at once if you don't want to distinguish between those.
 */
public abstract class NitrapiException extends Exception {
    public NitrapiException(String message) {
        super(message);
    }

    public NitrapiException(Throwable exception) {
        super(exception);
    }
}