package net.nitrado.api.common.exceptions;

/**
 * This exception is thrown when there was an error using the api.
 * <p>
 * e.g. not enough permissions
 */
public class NitrapiErrorException extends NitrapiException {

    private String errorId;

    public NitrapiErrorException(String message) {
        this(message, null);
    }

    public NitrapiErrorException(String message, String errorId) {
        super(message);
        this.errorId = errorId;
    }

    /**
     * Returns an error id if the error might be on the side of the api. Include this in your support requests.
     *
     * @return error id for support request
     */
    public String getErrorId() {
        return errorId;
    }

}
