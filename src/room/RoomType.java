package room;

/**
 * Room Type enum.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");

    private final String action;

    // getter method
    public String getAction()
    {
        return this.action;
    }

    RoomType(String action)
    {
        this.action = action;
    }
}
