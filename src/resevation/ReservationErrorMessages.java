package resevation;

public enum ReservationErrorMessages {
    RESERVATION_DOES_NOT_EXISTS("Reservation with email %s does not exist."),
    RESERVATION_CANNOT_BE_NULL("The reservation cannot be null."),
    RESERVATION_ALREADY_EXISTS("Reservation with email %s already exists."),
    EMAIL_CANNOT_BE_NULL_OR_EMPTY("The email cannot be null or empty."),
    CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST("Check in date cannot be in the past."),
    CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE("Check in date cannot be after check out date.");


    private final String message;

    ReservationErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
