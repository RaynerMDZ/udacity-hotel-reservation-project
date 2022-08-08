package room;

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
