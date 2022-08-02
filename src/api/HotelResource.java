package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public static Long hotelCount = 0L;

    private final CustomerService customerService = CustomerServiceImpl.getInstance();

    public Customer getCustomer(String email) {
        return null;
    }

    public void createACustomer(String email, String firstName, String lastName) {
        hotelCount++;
    }

    public IRoom getRoom(String roomNumber) {
        return null;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return null;
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        return null;
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return null;
    }

}
