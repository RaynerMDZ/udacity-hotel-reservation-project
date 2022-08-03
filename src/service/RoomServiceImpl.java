package service;

import model.IRoom;
import repository.RoomRepository;
import repository.RoomRepositoryImpl;

import java.util.Collection;
import java.util.logging.Logger;

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
        this.roomRepository.addRoom(room);
    }

    @Override
    public void addRooms(final Collection<IRoom> rooms) throws IllegalArgumentException {
        this.roomRepository.addRooms(rooms);
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
