package repository;

import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<String, Collection<Reservation>> reservations;
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
        return reservations.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public Collection<Reservation> getAReservation(final String email) throws IllegalArgumentException {
        if (reservations.containsKey(email)) {
            return reservations.get(email);
        }
        throw new IllegalArgumentException("No reservation found for " + email);
    }

    @Override
    public Collection<Reservation> addReservation(final Reservation reservation) {
        if (reservations.containsKey(reservation.getCustomer().getEmail())) {
            reservations.get(reservation.getCustomer().getEmail()).add(reservation);
        } else {
            Collection<Reservation> newReservations = new ArrayList<>();
            newReservations.add(reservation);
            reservations.put(reservation.getCustomer().getEmail(), newReservations);
        }
        return reservations.get(reservation.getCustomer().getEmail());
    }

    @Override
    public void updateReservation(final Reservation reservation) {
        if (reservations.containsKey(reservation.getCustomer().getEmail())) {
            reservations.get(reservation.getCustomer().getEmail()).remove(reservation);
            reservations.get(reservation.getCustomer().getEmail()).add(reservation);
        }
    }

    @Override
    public void deleteReservation(final Reservation reservation) {
        reservations.remove(reservation.getCustomer().getEmail());
    }

    @Override
    public Collection<Reservation> getCustomersReservation(final String email) {
        return reservations.get(email);
    }
}
