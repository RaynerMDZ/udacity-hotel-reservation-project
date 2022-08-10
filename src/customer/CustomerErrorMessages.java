package customer;

/**
 * Customer error messages.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public enum CustomerErrorMessages {
    CUSTOMER_DOES_NOT_EXIST("Customer with email '%s' does not exist."),
    CUSTOMER_ALREADY_EXISTS("Customer with email '%s' already exists."),
    EMAIL_CANNOT_BE_NULL_OR_EMPTY("Email cannot be null or empty."),
    FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY("First name cannot be null or empty."),
    LAST_NAME_CANNOT_BE_NULL_OR_EMPTY("Last name cannot be null or empty."),
    CUSTOMER_CANNOT_BE_NULL("Customer cannot be null."),
    INVALID_EMAIL("Email '%s' is not properly formatted."),
    EMAIL_CANNOT_CONTAIN_BLANK_SPACES("Email '%s' cannot contain blank spaces."),
    FIRST_NAME_CANNOT_CONTAIN_BLANK_SPACES("First name '%s' cannot contain blank spaces."),
    LAST_NAME_CANNOT_CONTAIN_BLANK_SPACES("Last name '%s' cannot contain blank spaces.");

    private final String message;

    CustomerErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
