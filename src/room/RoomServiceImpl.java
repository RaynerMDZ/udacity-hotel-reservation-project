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

    @Override
    public Collection<IRoom> getAllRooms() {
        return this.roomRepository.getAllRooms();
    }

    @Override
    public IRoom getRoom(final String roomNumber) throws IllegalArgumentException {
        return this.roomRepository.getRoom(roomNumber);
    }

    @Override
    public void addRoom(final IRoom room) throws IllegalArgumentException {
        this.roomRepository.createRoom(room);
    }

    @Override
    public void addRooms(final Collection<IRoom> rooms) throws IllegalArgumentException {
        this.roomRepository.createRooms(rooms);
    }

    @Override
    public void removeRoom(final String roomNumber) throws IllegalArgumentException {
        this.roomRepository.removeRoom(roomNumber);
    }

    @Override
    public void updateRoom(final IRoom room) {
        this.roomRepository.updateRoom(room);
    }
}