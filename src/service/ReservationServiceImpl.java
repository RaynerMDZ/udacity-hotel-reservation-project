package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import repository.ReservationRepository;
import repository.ReservationRepositoryImpl;

import java.util.Collection;
import java.util.Date;

public class ReservationServiceImpl implements ReservationService {
    private static ReservationServiceImpl instance = null;

    private ReservationServiceImpl() {
    }

    public static ReservationServiceImpl getInstance() {
        if (instance == null) {
            instance = new ReservationServiceImpl();
        }
        return instance;
    }

    private final ReservationRepository reservationRepository = ReservationRepositoryImpl.getInstance();

    @Override
    public void addRoom(IRoom room) {
    }

    @Override
    public IRoom getARoom(String roomNumber) {
        return null;
    }

    @Override
    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return null;
    }

    @Override
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;
    }

    @Override
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return null;
    }

    @Override
    public void printAllReservations() {

    }
}
