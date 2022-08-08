package room;

public enum RoomErrorMessages {
    ROOM_DOES_NOT_EXIST("Room with room number %s does not exist."),
    ROOM_ALREADY_EXISTS("Room with room number %s already exists."),
    ROOM_NUMBER_CANNOT_BE_NULL_OR_EMPTY("Room number cannot be null or empty."),
    ROOM_CANNOT_BE_NULL("Room cannot be null."),
    ROOMS_SET_CANNOT_BE_NULL_OR_EMPTY("Rooms set cannot be null or empty.");

    private final String message;

    private RoomErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
