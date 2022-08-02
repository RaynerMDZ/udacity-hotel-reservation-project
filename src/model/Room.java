package model;

public class Room implements IRoom {
    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;
    private boolean isFree;

    public Room(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Room(String roomNumber, Double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = false;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean isFree() {
        return isFree;
    }

    public String toString() {
        return "Room " + roomNumber + ": $" + roomPrice + ", " + roomType + ", " + (isFree ? "free" : "Not Free");
    }

    // hashCode and equals methods
    @Override
    public int hashCode() {
        return roomNumber.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room other = (Room) obj;
            return this.roomNumber.equals(other.roomNumber);
        }
        return false;
    }
}
