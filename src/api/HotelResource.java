package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.ReservationService;
import service.ReservationServiceImpl;

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
        this.customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return this.reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return this.reservationService.reserveRoom(this.customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
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
