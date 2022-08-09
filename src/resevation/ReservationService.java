package resevation;

import customer.Customer;
import room.IRoom;

import java.util.Collection;
import java.util.Date;

/**
 * Reservation Service interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public interface ReservationService {
    public void addRoom(IRoom room);
    public IRoom getARoom(java.lang.String roomNumber);
    public Collection<Reservation> reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate);
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate);
    public Collection<IRoom> findRoomsForNextWeek(Date checkInDate, Date checkOutDate);
    public Collection<Reservation> getCustomersReservation(String email);
    public Collection<Reservation> printAllReservations();
    public Collection<Reservation> getReservationsForTheGivenWeek(Date checkInDate, Date checkOutDate);
    public Collection<Reservation> getReservationsForAWeekAfterTheGivenDate(Date checkInDate, Date checkOutDate);

}
