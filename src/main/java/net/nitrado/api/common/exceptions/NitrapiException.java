package net.nitrado.api.common.exceptions;

/**
 * This is a superclass of NitrapiHttpException and NitrapiErrorException so that you can catch both at once if you
 * don't want to distinguish between those.
 */
public class NitrapiException extends RuntimeException {
    public NitrapiException(String message) {
        super(message);
    }

    public NitrapiException(Throwable exception) {
        super(exception);
    }
}
