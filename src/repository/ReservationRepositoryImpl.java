package repository;

import model.Reservation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReservationRepositoryImpl implements ReservationRepository {
    private static ReservationRepositoryImpl instance = null;

    private ReservationRepositoryImpl() {
    }

    public static ReservationRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ReservationRepositoryImpl();
        }
        return instance;
    }

    private final Map<String, Reservation> reservations = new HashMap<>();

    @Override
    public Collection<Reservation> getAllReservations() {
        return reservations.values();
    }

    @Override
    public Reservation getAReservation(String email) {
        if (reservations.containsKey(email)) {
            return reservations.get(email);
        }
        throw new IllegalArgumentException("No reservation found for " + email);
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getCustomer().getEmail(), reservation);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservations.put(reservation.getCustomer().getEmail(), reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation.getCustomer().getEmail());
    }
}
