package resevation;

import java.util.Collection;

/**
 * Reservation Repository interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public interface ReservationRepository {
    public Collection<Reservation> getAllReservations();
    public Collection<Reservation> getReservation(String email) throws IllegalArgumentException;
    public Collection<Reservation> createReservation(Reservation reservation);
    public void updateReservation(Reservation reservation);
    public void removeReservation(Reservation reservation);
    public Collection<Reservation> getCustomerReservations(String email);
}
