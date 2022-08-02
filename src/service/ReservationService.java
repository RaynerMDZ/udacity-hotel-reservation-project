package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public interface ReservationService {
    public void addRoom(IRoom room);
    public IRoom getARoom(java.lang.String roomNumber);
    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate);
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate);
    public Collection<Reservation> getCustomersReservation(String email);
    public Collection<Reservation> printAllReservations();

}
