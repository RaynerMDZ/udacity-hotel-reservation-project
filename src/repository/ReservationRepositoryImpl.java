package repository;

import model.Customer;
import model.Reservation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<String, Reservation> reservations;
    private static ReservationRepositoryImpl instance = null;

    private ReservationRepositoryImpl() {
        reservations = new HashMap<>();
    }

    public static ReservationRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ReservationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Collection<Reservation> getAllReservations() {
        return reservations.values();
    }

    @Override
    public Reservation getAReservation(final String email) throws IllegalArgumentException {
        if (reservations.containsKey(email)) {
            return reservations.get(email);
        }
        throw new IllegalArgumentException("No reservation found for " + email);
    }

    @Override
    public Reservation addReservation(final Reservation reservation) {
        reservations.put(reservation.getCustomer().getEmail(), reservation);
        return getAReservation(reservation.getCustomer().getEmail());
    }

    @Override
    public void updateReservation(final Reservation reservation) {
        reservations.put(reservation.getCustomer().getEmail(), reservation);
    }

    @Override
    public void deleteReservation(final Reservation reservation) {
        reservations.remove(reservation.getCustomer().getEmail());
    }

    @Override
    public Collection<Reservation> getCustomersReservation(final String email) {
        return reservations.values().stream()
                .filter(reservation -> reservation.getCustomer().getEmail().equals(email))
                .collect(Collectors.toList());
    }
}
