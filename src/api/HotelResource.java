package api;

import customer.Customer;
import room.IRoom;
import resevation.Reservation;
import customer.CustomerService;
import customer.CustomerServiceImpl;
import resevation.ReservationService;
import resevation.ReservationServiceImpl;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private static HotelResource instance = null;

    private HotelResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static HotelResource getInstance() {
        if (instance == null) {
            instance = new HotelResource(CustomerServiceImpl.getInstance(), ReservationServiceImpl.getInstance());
        }
        return instance;
    }

    public Customer getCustomer(String email) {
        return this.customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        this.customerService.createCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return this.reservationService.getARoom(roomNumber);
    }

    public Collection<Reservation> bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        var customer = this.customerService.getCustomer(customerEmail);
        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist. Create an account first.");
        }
        return this.reservationService.reserveRoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String email) {
        return this.reservationService.getCustomersReservation(email);

    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return this.reservationService.findRooms(checkInDate, checkOutDate);
    }

    public Collection<IRoom> checkRoomsForNextWeek(Date checkInDate, Date checkOutDate) {
        return this.reservationService.findRoomsForNextWeek(checkInDate, checkOutDate);
    }

}
