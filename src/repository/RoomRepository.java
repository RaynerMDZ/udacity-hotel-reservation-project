package repository;

import model.IRoom;

import java.util.Collection;

public interface RoomRepository {
    public Collection<IRoom> getAllRooms();
    public IRoom getRoom(String roomNumber) throws IllegalArgumentException;
    public void addRoom(IRoom room) throws IllegalArgumentException;
    public void addRooms(Collection<IRoom> rooms) throws IllegalArgumentException;
    public void removeRoom(String roomNumber) throws IllegalArgumentException;
    public void updateRoom(IRoom room);
}
