package room;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Implementation of the Room Service interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public class RoomServiceImpl implements RoomService {

    private final Logger logger = Logger.getLogger(RoomRepositoryImpl.class.getName());
    private final RoomRepository roomRepository;
    private static RoomServiceImpl instance = null;

    private RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public static RoomServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomServiceImpl(RoomRepositoryImpl.getInstance());
        }
        return instance;
    }

    /**
     * Returns a collection of all rooms.
     * @return Collection of rooms.
     * @see RoomRepository#getAllRooms()
     */
    @Override
    public Collection<IRoom> getAllRooms() {
        return this.roomRepository.getAllRooms();
    }

    /**
     * Returns the room with the given room number.
     * @param roomNumber The room number of the room.
     * @return The room with the given room number.
     * @throws IllegalArgumentException If the room number is null or empty.
     * @see RoomRepository#getRoom(String)
     */
    @Override
    public IRoom getRoom(final String roomNumber) throws IllegalArgumentException {
        if (roomNumber == null || roomNumber.isEmpty()) {
            throw new IllegalArgumentException(String.format(RoomErrorMessages.ROOM_NUMBER_CANNOT_BE_NULL_OR_EMPTY.getMessage(), roomNumber));
        }
        return this.roomRepository.getRoom(roomNumber);
    }

    /**
     * Add a room.
     * @param room The room to add.
     * @throws IllegalArgumentException If the room is null.
     * @see RoomRepository#createRoom(IRoom)
     */
    @Override
    public void createRoom(final IRoom room) throws IllegalArgumentException {
        if (room == null) {
            throw new IllegalArgumentException(RoomErrorMessages.ROOM_CANNOT_BE_NULL.getMessage());
        }
        this.roomRepository.createRoom(room);
    }

    /**
     * Add multiple rooms.
     * @param rooms The rooms to add.
     * @throws IllegalArgumentException If the rooms are null or empty.
     * @see RoomRepository#createRooms(Collection)
     */
    @Override
    public void createRooms(final Collection<IRoom> rooms) throws IllegalArgumentException {
        if (rooms == null || rooms.isEmpty()) {
            throw new IllegalArgumentException(RoomErrorMessages.ROOMS_SET_CANNOT_BE_NULL_OR_EMPTY.getMessage());
        }
        this.roomRepository.createRooms(rooms);
    }

    /**
     * Removes a room.
     * @param roomNumber The room number of the room.
     * @throws IllegalArgumentException If the room number is null or empty.
     * @see RoomRepository#removeRoom(String)
     */
    @Override
    public void removeRoom(final String roomNumber) throws IllegalArgumentException {
        if (roomNumber == null || roomNumber.isEmpty()) {
            throw new IllegalArgumentException(String.format(RoomErrorMessages.ROOM_NUMBER_CANNOT_BE_NULL_OR_EMPTY.getMessage(), roomNumber));
        }
        this.roomRepository.removeRoom(roomNumber);
    }

    /**
     * Updates a room.
     * @param room The room numbers of the rooms.
     * @throws IllegalArgumentException If the room is null.
     * @see RoomRepository#updateRoom(IRoom)
     */
    @Override
    public void updateRoom(final IRoom room) {
        if (room == null) {
            throw new IllegalArgumentException(RoomErrorMessages.ROOM_CANNOT_BE_NULL.getMessage());
        }
        this.roomRepository.updateRoom(room);
    }
}
