package util;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.ReservationService;
import service.ReservationServiceImpl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataGenerator {
    private static final DecimalFormat decimalFormat = new DecimalFormat("###.##");
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final CustomerService customerService = CustomerServiceImpl.getInstance();
    private static final ReservationService reservationService = ReservationServiceImpl.getInstance();

    public static void generateRooms() {
        List<IRoom> rooms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            IRoom room = new Room("1" + i, getRandomDouble(50.00, 150.00), generateRandomRoomType(), false);
            rooms.add(room);
        }
        adminResource.addRooms(rooms);

        System.out.println();
        System.out.println("Rooms added successfully.");
        System.out.println();
    }

    public static void generateCustomer() {
        customerService.addCustomer("rayner", "mendez", "rayner@gmail.com");

        System.out.println();
        System.out.println("Customer added successfully.");
        System.out.println();
    }

    public static void generateReservation() {
        Date checkInDate = null;
        Date checkOutDate = null;
        try {
            checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse("02/01/2023");
            checkOutDate = new SimpleDateFormat("MM/dd/yyyy").parse("02/20/2023");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final IRoom room = adminResource.getAllRooms().iterator().next();
        room.book();

        reservationService.reserveRoom(customerService.getCustomer("rayner@gmail.com"), room, checkInDate, checkOutDate);

        System.out.println();
        System.out.println("Reservation added successfully.");
        System.out.println();
    }

    private static Double getRandomDouble(final Double min, final Double max) {
        return Double.parseDouble(decimalFormat.format(min + (Math.random() * (max - min))));
    }

    private static RoomType generateRandomRoomType() {
        int random = (int) (Math.random() * 2);
        return switch (random) {
            case 0 -> RoomType.SINGLE;
            default -> RoomType.DOUBLE;
        };
    }
}
