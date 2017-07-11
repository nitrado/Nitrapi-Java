package net.nitrado.api.common.exceptions;

/**
 * This exception is thrown when the used access token is invalid.
 */
public class NitrapiAccessTokenInvalidException extends NitrapiException {
    public NitrapiAccessTokenInvalidException(String message) {
        super(message);
    }
}
