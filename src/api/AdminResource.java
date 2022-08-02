package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.ReservationService;
import service.ReservationServiceImpl;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private static AdminResource instance = null;

    private AdminResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static AdminResource getInstance() {
        if (instance == null) {
            instance = new AdminResource(CustomerServiceImpl.getInstance(), ReservationServiceImpl.getInstance());
        }
        return instance;
    }

    public Customer getCustomer(String email) {
        return null;
    }

    public void addRoom(List<IRoom> rooms) {

    }

    public Collection<IRoom> getAllRooms() {
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return null;
    }

    public void displayAllReservations() {

    }

}
