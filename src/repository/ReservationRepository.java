package repository;

import model.Reservation;

import java.util.Collection;

public interface ReservationRepository {
    public Collection<Reservation> getAllReservations();
    public Reservation getAReservation(String email);
    public void addReservation(Reservation reservation);
    public void updateReservation(Reservation reservation);
    public void deleteReservation(Reservation reservation);
}
