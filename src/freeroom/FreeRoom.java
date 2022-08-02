package freeroom;

import room.Room;
import room.RoomType;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.00, roomType);
    }
}
