package model;

public class Room implements IRoom {
    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;
    private boolean isFree;
    private boolean isReserved;

    public Room(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Room(String roomNumber, Double roomPrice, RoomType roomType, boolean isReserved) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = false;
        this.isReserved = isReserved;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }

    @Override
    public boolean isFree() {
        return this.isFree;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public void book() {
        this.isReserved = true;
    }

    @Override
    public void cancel() {
        this.isReserved = false;
    }

    public String toString() {
        return "Room " + this.roomNumber + ": $" + this.roomPrice + ", " + this.roomType + ", " + (this.isFree ? "free" : "Not Free");
    }

    @Override
    public int hashCode() {
        return this.roomNumber.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room other) {
            return this.roomNumber.equals(other.roomNumber);
        }
        return false;
    }
}
