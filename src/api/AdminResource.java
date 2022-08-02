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
import java.util.List;

public class AdminResource {
    public static Long adminCount = 0L;

    private final CustomerService customerService = CustomerServiceImpl.getInstance();
    private final ReservationService reservationService = ReservationServiceImpl.getInstance();

    public Customer getCustomer(String email) {
        return null;
    }

    public void addRoom(List<IRoom> rooms) {
        adminCount++;
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
