package room;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Implementation of the RoomService interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public class RoomRepositoryImpl implements RoomRepository {

    private final Map<String, IRoom> rooms;
    private static RoomRepositoryImpl instance = null;

    private RoomRepositoryImpl() {
        this.rooms = new HashMap<>();
    }

    public static RoomRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (RoomRepositoryImpl.class) {
                if (instance == null) {
                    instance = new RoomRepositoryImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Returns all rooms in the repository as a new Hashset of IRoom objects.
     * @return A new Hashset of IRoom objects.
     * @see room.RoomRepository#getAllRooms()
     */
    @Override
    public Collection<IRoom> getAllRooms() {
        return new HashSet<>(this.rooms.values());
    }

    /**
     * Returns the room with the given room number.
     * @param roomNumber The room number of the room to return.
     * @return The room with the given room number.
     * @throws IllegalArgumentException If the room number is null or empty.
     * @throws IllegalArgumentException If the room with the given room number does not exist.
     * @see room.RoomRepository#getRoom(String)
     */
    @Override
    public IRoom getRoom(final String roomNumber) throws IllegalArgumentException {
        if (roomNumber == null || roomNumber.isEmpty()) {
            throw new IllegalArgumentException(RoomErrorMessages.ROOM_NUMBER_CANNOT_BE_NULL_OR_EMPTY.getMessage());
        }

        if (!this.roomExists(roomNumber)) {
            throw new IllegalArgumentException(RoomErrorMessages.ROOM_DOES_NOT_EXIST.getMessage());
        }
        return this.rooms.get(roomNumber);
    }

    /**
     * Adds a room to the repository.
     * @param room The room to add.
     * @throws IllegalArgumentException If the room is null.
     * @throws IllegalArgumentException If the room already exists.
     * @see room.RoomRepository#createRoom(IRoom)
     */
    @Override
    public void createRoom(final IRoom room) {
        if (room == null) {
            throw new IllegalArgumentException(RoomErrorMessages.ROOM_CANNOT_BE_NULL.getMessage());
        }

        if (this.roomExists(room.getRoomNumber())) {
            throw new IllegalArgumentException(String.format(RoomErrorMessages.ROOM_ALREADY_EXISTS.getMessage(), room.getRoomNumber()));
        }
        this.rooms.put(room.getRoomNumber(), room);
    }

    /**
     * Adds a collection of rooms to the repository.
     * @param rooms The rooms to add.
     * @throws IllegalArgumentException If the rooms are null or empty.
     * @see room.RoomRepository#createRooms(Collection)
     */
    @Override
    public void createRooms(final Collection<IRoom> rooms) throws IllegalArgumentException {
        if (rooms == null || rooms.isEmpty()) {
            throw new IllegalArgumentException(RoomErrorMessages.ROOMS_SET_CANNOT_BE_NULL_OR_EMPTY.getMessage());
        }

        rooms.forEach(this::createRoom);
    }

    /**
     * Removes a room from the repository.
     * @param roomNumber The room number of the room to remove.
     * @throws IllegalArgumentException If the room with the given room number does not exist.
     * @see room.RoomRepository#removeRoom(String)
     */
    @Override
    public void removeRoom(final String roomNumber) throws IllegalArgumentException {
        if (!roomExists(roomNumber)) {
            throw new IllegalArgumentException(String.format(RoomErrorMessages.ROOM_DOES_NOT_EXIST.getMessage(), roomNumber));
        }
        this.rooms.remove(roomNumber);
    }


    /**
     * Updates a room in the repository.
     * @param room The rooms to update.
     * @throws IllegalArgumentException If the room with the given room number does not exist.
     * @see room.RoomRepository#updateRoom(IRoom)
     */
    @Override
    public void updateRoom(final IRoom room) throws IllegalArgumentException {
        if (roomExists(room.getRoomNumber())) {
            throw new IllegalArgumentException(String.format(RoomErrorMessages.ROOM_DOES_NOT_EXIST.getMessage(), room.getRoomNumber()));
        }
        this.rooms.put(room.getRoomNumber(), room);
    }

    /**
     * Checks if a room with the given room number exists in the repository.
     * @param roomNumber The room number to check.
     * @return True if a room with the given room number exists in the repository, false otherwise.
     */
    private boolean roomExists(final String roomNumber) {
        return this.rooms.containsKey(roomNumber);
    }
}
