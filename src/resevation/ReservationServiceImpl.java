package resevation;

import customer.Customer;
import room.IRoom;
import room.RoomService;
import room.RoomServiceImpl;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Implementation of the Reservation Service interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public class ReservationServiceImpl implements ReservationService {

    private final Logger logger = Logger.getLogger(ReservationServiceImpl.class.getName());
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private static ReservationServiceImpl instance = null;

    ReservationServiceImpl(ReservationRepository reservationRepository, RoomService roomService) {
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
    public IRoom getARoom(final String roomNumber) {
        return this.roomService.getRoom(roomNumber);
    }

    @Override
    public Collection<Reservation> reserveRoom(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate) {
        if (customer == null) {
            throw new IllegalArgumentException(ReservationErrorMessages.CUSTOMER_CANNOT_BE_NULL.getMessage());
        }

        if (room == null) {
            throw new IllegalArgumentException(ReservationErrorMessages.ROOM_CANNOT_BE_NULL.getMessage());
        }

        if (checkInDate.before(new Date())) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST.getMessage());
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE.getMessage());
        }
        return this.reservationRepository.createReservation(new Reservation(customer, room, checkInDate, checkOutDate));
    }

    @Override
    public Collection<IRoom> findRooms(final Date checkInDate, final Date checkOutDate) {
        if (checkInDate.before(new Date())) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST.getMessage());
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE.getMessage());
        }

        Collection<Reservation> reservations = this.reservationRepository.getAllReservations();
        Collection<IRoom> rooms = this.roomService.getAllRooms();
        Collection<IRoom> availableRooms = rooms.stream()
                .filter(room -> !reservations.stream()
                        .anyMatch(reservation -> reservation.getRoom().getRoomNumber().equals(room.getRoomNumber())
                                && reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)))
                .collect(Collectors.toList());
        return availableRooms;
    }

    @Override
    public Collection<IRoom> findRoomsForNextWeek(Date checkInDate, Date checkOutDate) {
        if (checkInDate.before(new Date())) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST.getMessage());
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE.getMessage());
        }

        Date checkInDateNextWeek = new Date(checkInDate.getTime() + 604800000);
        Date checkOutDateNextWeek = new Date(checkOutDate.getTime() + 604800000);
        return this.findRooms(checkInDateNextWeek, checkOutDateNextWeek);
    }

    @Override
    public Collection<Reservation> getCustomersReservation(final String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(ReservationErrorMessages.EMAIL_CANNOT_BE_NULL_OR_EMPTY.getMessage());
        }
        return this.reservationRepository.getCustomerReservations(email);
    }

    @Override
    public Collection<Reservation> printAllReservations() {
        return this.reservationRepository.getAllReservations();
    }

    @Override
    public Collection<Reservation> getReservationsForTheGivenWeek(Date checkInDate, Date checkOutDate) {
        if (checkInDate.before(new Date())) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST.getMessage());
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE.getMessage());
        }

        Collection<Reservation> reservations = this.reservationRepository.getAllReservations();
        Collection<Reservation> reservationsForTheGivenWeek = reservations.stream()
                .filter(reservation -> reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate))
                .collect(Collectors.toList());
        return reservationsForTheGivenWeek;
    }

    @Override
    public Collection<Reservation> getReservationsForAWeekAfterTheGivenDate(Date checkInDate, Date checkOutDate) {
        if (checkInDate.before(new Date())) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST.getMessage());
        }

        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException(ReservationErrorMessages.CHECK_IN_DATE_CANNOT_BE_AFTER_CHECK_OUT_DATE.getMessage());
        }

        Date checkInDateNextWeek = new Date(checkInDate.getTime() + 604800000);
        Date checkOutDateNextWeek = new Date(checkOutDate.getTime() + 604800000);
        return this.getReservationsForTheGivenWeek(checkInDateNextWeek, checkOutDateNextWeek);
    }
}
