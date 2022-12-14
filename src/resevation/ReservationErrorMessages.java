package resevation;

/**
 * Reservation error messages.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public enum ReservationErrorMessages {
    RESERVATION_DOES_NOT_EXISTS("Reservation with email %s does not exist."),
    RESERVATION_CANNOT_BE_NULL("The reservation cannot be null."),
    RESERVATION_ALREADY_EXISTS("Reservation with email %s already exists."),
    EMAIL_CANNOT_BE_NULL_OR_EMPTY("The email cannot be null or empty."),
    CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST("Check in date cannot be in the past."),
    CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE("Check in date cannot be after check out date."),
    CUSTOMER_CANNOT_BE_NULL("The customer cannot be null."),
    ROOM_CANNOT_BE_NULL("The room cannot be null"),
    EMAIL_NOT_VALID("The email %s is not valid.");


    private final String message;

    ReservationErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
