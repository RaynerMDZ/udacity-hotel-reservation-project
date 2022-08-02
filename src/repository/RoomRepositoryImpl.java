package repository;

import model.IRoom;

import java.util.Collection;
import java.util.HashSet;

public class RoomRepositoryImpl implements RoomRepository {

    private final Collection<IRoom> rooms;
    private static RoomRepositoryImpl instance = null;

    private RoomRepositoryImpl() {
        this.rooms = new HashSet<>();
    }

    public static RoomRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new RoomRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Collection<IRoom> getAllRooms() {
        return this.rooms;
    }

    @Override
    public IRoom getRoom(final String roomNumber) throws IllegalArgumentException {
         return this.rooms.stream()
                 .filter(room -> room.getRoomNumber().equals(roomNumber))
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    @Override
    public void addRoom(final IRoom room) {
        this.rooms.add(room);
    }

    @Override
    public void addRooms(final Collection<IRoom> rooms) throws IllegalArgumentException {
        this.rooms.addAll(rooms);
    }

    @Override
    public void removeRoom(final String roomNumber) throws IllegalArgumentException {
        if (roomExists(roomNumber)) {
            this.rooms.remove(getRoom(roomNumber));
        }
        throw new IllegalArgumentException("Room does not exist.");
    }

    @Override
    public void updateRoom(final IRoom room) throws IllegalArgumentException {
        if (roomExists(room.getRoomNumber())) {
            this.rooms.remove(getRoom(room.getRoomNumber()));
            this.rooms.add(room);
        }
        throw new IllegalArgumentException("Room does not exist.");
    }

    private boolean roomExists(final String roomNumber) {
        return this.rooms.stream()
                .anyMatch(room -> room.getRoomNumber().equals(roomNumber));
    }
}
