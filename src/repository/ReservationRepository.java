package repository;

import model.Reservation;

import java.util.Collection;

public interface ReservationRepository {
    public Collection<Reservation> getAllReservations();
    public Reservation getAReservation(String email) throws IllegalArgumentException;
    public Reservation addReservation(Reservation reservation);
    public Collection<Reservation> getCustomersReservation(String email);
    public void updateReservation(Reservation reservation);
    public void deleteReservation(Reservation reservation);

}
