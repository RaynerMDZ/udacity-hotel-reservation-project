package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import repository.ReservationRepository;
import repository.ReservationRepositoryImpl;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

public class ReservationServiceImpl implements ReservationService {

    private final Logger logger = Logger.getLogger(ReservationServiceImpl.class.getName());
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private static ReservationServiceImpl instance = null;

    private ReservationServiceImpl(ReservationRepository reservationRepository, RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
    }

    public static ReservationServiceImpl getInstance() {
        if (instance == null) {
            instance = new ReservationServiceImpl(ReservationRepositoryImpl.getInstance(), RoomServiceImpl.getInstance());
        }
        return instance;
    }

    @Override
    public void addRoom(final IRoom room) {
//        this.logger.info("Adding room: " + room.getRoomNumber());
        this.roomService.addRoom(room);
    }

    @Override
    public IRoom getARoom(final String roomNumber) {
//        this.logger.info("Getting room: " + roomNumber);
        return this.roomService.getRoom(roomNumber);
    }

    @Override
    public Reservation reserveRoom(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate) {;
        return this.reservationRepository.addReservation(new Reservation(customer, room, checkInDate, checkOutDate));
    }

    @Override
    public Collection<IRoom> findRooms(final Date checkInDate, final Date checkOutDate) {
//        this.logger.info("Finding rooms");
//        return this.roomService.findRooms(checkInDate, checkOutDate);
        return null;
    }

    @Override
    public Collection<Reservation> getCustomersReservation(final String email) {
        return this.reservationRepository.getCustomersReservation(email);
    }

    @Override
    public Collection<Reservation> printAllReservations() {
//        this.logger.info("Printing all reservations");
        return this.reservationRepository.getAllReservations();
    }
}
