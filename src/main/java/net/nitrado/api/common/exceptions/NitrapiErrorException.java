package net.nitrado.api.common.exceptions;

/**
 * This exception is thrown when there was an error using the api.
 * <p>
 * e.g. not enough permissions
 */
public class NitrapiErrorException extends NitrapiException {
    private int httpStatusCode;

    public NitrapiErrorException(String message) {
        this(message, -1);
    }
    public NitrapiErrorException(String message, int httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * Returns the http response code.
     *
     * @return the status code or -1 if there was no http request involved.
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
