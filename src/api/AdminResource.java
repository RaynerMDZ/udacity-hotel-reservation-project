package api;

import customer.CustomerService;
import customer.CustomerServiceImpl;
import customer.Customer;
import room.IRoom;
import resevation.Reservation;
import resevation.ReservationService;
import resevation.ReservationServiceImpl;
import room.RoomService;
import room.RoomServiceImpl;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private final RoomService roomService;
    private static AdminResource instance = null;


    private AdminResource(CustomerService customerService, ReservationService reservationService, RoomService roomService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    public static AdminResource getInstance() {
        if (instance == null) {
            instance = new AdminResource(CustomerServiceImpl.getInstance(), ReservationServiceImpl.getInstance(), RoomServiceImpl.getInstance());
        }
        return instance;
    }

    public Customer getCustomer(String email) {
        return null;
    }

    public void addRooms(List<IRoom> rooms) {
        this.roomService.createRooms(rooms);
    }

    public void addRoom(IRoom room) {
        this.roomService.createRoom(room);
    }

    public Collection<IRoom> getAllRooms() {
        return this.roomService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    public Collection<Reservation> displayAllReservations() {
        return this.reservationService.printAllReservations();
    }


}
