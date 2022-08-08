package resevation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the ReservationService interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<String, Collection<Reservation>> reservations;
    private static ReservationRepositoryImpl instance = null;

    private ReservationRepositoryImpl() {
        this.reservations = new HashMap<>();
    }

    public static synchronized ReservationRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (ReservationRepositoryImpl.class) {
                if (instance == null) {
                    instance = new ReservationRepositoryImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Returns all reservations in the repository as a new list of Reservation objects.
     * @return A new list of Reservation objects.
     * @see resevation.ReservationRepository#getAllReservations()
     */
    @Override
    public Collection<Reservation> getAllReservations() {
        return this.reservations.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Returns the reservations for the customer email.
     * @param email The email of the customer.
     * @return The reservations for the customer.
     * @throws IllegalArgumentException If the email is null or empty.
     * @throws IllegalArgumentException If the email does not exist in the repository.
     * @see resevation.ReservationRepository#getReservation(String)
     */
    @Override
    public Collection<Reservation> getReservation(final String email) throws IllegalArgumentException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(String.format(ReservationErrorMessages.EMAIL_CANNOT_BE_NULL_OR_EMPTY.getMessage(), email));
        }

        if (this.reservations.containsKey(email)) {
            throw new IllegalArgumentException(String.format(ReservationErrorMessages.RESERVATION_DOES_NOT_EXISTS.getMessage(), email));
        }
        return this.reservations.get(email);
    }

    /**
     * Adds a reservation to the repository.
     * @param reservation The reservation to add.
     * @throws IllegalArgumentException If the reservation is null.
     * @throws IllegalArgumentException If the reservation already exists in the repository.
     * @see resevation.ReservationRepository#createReservation(resevation.Reservation)
     */
    @Override
    public Collection<Reservation> createReservation(final Reservation reservation) {
        if (this.reservations.containsKey(reservation.getCustomer().getEmail())) {
            this.reservations.get(reservation.getCustomer().getEmail()).add(reservation);
        } else {
            Collection<Reservation> newReservations = new ArrayList<>();
            newReservations.add(reservation);
            this.reservations.put(reservation.getCustomer().getEmail(), newReservations);
        }
        return this.reservations.get(reservation.getCustomer().getEmail());
    }


    /**
     * Updates a reservation from the repository.
     * @param reservation The reservation to update.
     * @throws IllegalArgumentException If the reservation is null.
     * @throws IllegalArgumentException If the reservation does not exist in the repository.
     * @see resevation.ReservationRepository#updateReservation(Reservation)
     */
    @Override
    public void updateReservation(final Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException(String.format(ReservationErrorMessages.RESERVATION_CANNOT_BE_NULL.getMessage(), reservation));
        }

        if (this.reservations.containsKey(reservation.getCustomer().getEmail())) {
            this.reservations.get(reservation.getCustomer().getEmail()).add(reservation);
        } else {
            Collection<Reservation> newReservations = new ArrayList<>();
            newReservations.add(reservation);
            this.reservations.put(reservation.getCustomer().getEmail(), newReservations);
        }
    }

    /**
     * Removes a reservation from the repository.
     * @param reservation The reservation to remove.
     * @throws IllegalArgumentException If the reservation is null.
     * @throws IllegalArgumentException If the reservation does not exist in the repository.
     * @see resevation.ReservationRepository#removeReservation(resevation.Reservation)
     */
    @Override
    public void removeReservation(final Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException(ReservationErrorMessages.RESERVATION_CANNOT_BE_NULL.getMessage());
        }

        if (!this.reservations.containsKey(reservation.getCustomer().getEmail())) {
            throw new IllegalArgumentException(String.format(ReservationErrorMessages.RESERVATION_DOES_NOT_EXISTS.getMessage(), reservation.getCustomer().getEmail()));
        }
        this.reservations.get(reservation.getCustomer().getEmail()).remove(reservation);
    }

    /**
     * Returns all reservations from a customer in the repository as a new list of Reservation objects.
     * @param email The email of the customer.
     * @return A new list of Reservation objects.
     * @throws IllegalArgumentException If the email is null or empty.
     * @throws IllegalArgumentException If the email does not exist in the repository.
     * @see resevation.ReservationRepository#getAllReservations()
     */
    @Override
    public Collection<Reservation> getCustomerReservations(final String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(String.format(ReservationErrorMessages.EMAIL_CANNOT_BE_NULL_OR_EMPTY.getMessage(), email));
        }

        if (!this.reservations.containsKey(email)) {
            throw new IllegalArgumentException(String.format(ReservationErrorMessages.RESERVATION_DOES_NOT_EXISTS.getMessage(), email));
        }
        return this.reservations.get(email);
    }
}
