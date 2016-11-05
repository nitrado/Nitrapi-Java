package net.nitrado.api.common.exceptions;

/**
 * This exception is thrown if there was an error connecting to the api.
 * <p>
 * e.g. the pc has no internet connection
 */
public class NitrapiHttpException extends NitrapiException {
    public NitrapiHttpException(Throwable e) {
        super(e);
    }

}
