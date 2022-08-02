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
//        this.logger.info("Getting all rooms");
        return this.roomRepository.getAllRooms();
    }

    @Override
    public IRoom getRoom(String roomNumber) throws IllegalArgumentException {
//        this.logger.info("Getting room with number: " + roomNumber);
        return this.roomRepository.getRoom(roomNumber);
    }

    @Override
    public void addRoom(IRoom room) throws IllegalArgumentException {
//        this.logger.info("Adding room: " + room);
        this.roomRepository.addRoom(room);
    }

    @Override
    public void addRooms(Collection<IRoom> rooms) throws IllegalArgumentException {
        this.roomRepository.addRooms(rooms);
    }

    @Override
    public void removeRoom(String roomNumber) throws IllegalArgumentException {
//        this.logger.info("Removing room with number: " + roomNumber);
        this.roomRepository.removeRoom(roomNumber);
    }

    @Override
    public void updateRoom(IRoom room) {
//        this.logger.info("Updating room: " + room);
        this.roomRepository.updateRoom(room);
    }
}
