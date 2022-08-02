package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private final CustomerService customerService;

    private static HotelResource instance = null;

    private HotelResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    public static HotelResource getInstance() {
        if (instance == null) {
            instance = new HotelResource(CustomerServiceImpl.getInstance());
        }
        return instance;
    }

    public Customer getCustomer(String email) {
        return this.customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        this.customerService.addCustomer(email, firstName, lastName);
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
