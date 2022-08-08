package customer;

public enum CustomerErrorMessages {
    CUSTOMER_DOES_NOT_EXIST("Customer with email %s does not exist."),
    CUSTOMER_ALREADY_EXISTS("Customer with email %s already exists."),
    EMAIL_CANNOT_BE_NULL_OR_EMPTY("Email cannot be null or empty."),
    FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY("First name cannot be null or empty."),
    LAST_NAME_CANNOT_BE_NULL_OR_EMPTY("Last name cannot be null or empty."),
    CUSTOMER_CANNOT_BE_NULL("Customer cannot be null.");

    private final String message;

    CustomerErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
