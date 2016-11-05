package net.nitrado.api.services.gameservers.customersettings;

/**
 * Exception that is thrown when the specified setting was not found.
 */
public class CustomerSettingsNotFoundException extends Exception {
    public CustomerSettingsNotFoundException(String message) {
        super(message);
    }
}
