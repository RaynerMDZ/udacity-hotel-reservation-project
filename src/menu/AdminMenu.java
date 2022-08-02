package menu;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AdminMenu {

    private final AdminResource adminResource = AdminResource.getInstance();
    private final CustomerService customerService = CustomerServiceImpl.getInstance();
    private final DecimalFormat decimalFormat = new DecimalFormat("###.##");
    private final Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;


    private static AdminMenu instance = null;

    private AdminMenu() {
    }

    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }

    public void adminMenu () {
        this.displayAdminMenu();

        while (this.isRunning) {
            try {
                Integer choice = Integer.parseInt(this.scanner.nextLine());
                switch (choice) {
                    case 1 -> this.seeAllCustomers();
                    case 2 -> this.seeAllRooms();
                    case 3 -> this.seeAllReservations();
                    case 4 -> this.addRoom();
                    case 5 -> this.addTestData();
                    case 6 -> this.backToMainMenu();
                    default -> System.out.println("Error: Invalid input. Please enter a number between 1 and 6.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please use only numbers.\n");
            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        }

        HotelMenu.getInstance().mainMenu();
    }

    public void displayAdminMenu() {
        System.out.println("****** Admin Menu ******");
        System.out.println("1. See all customers.");
        System.out.println("2. See all rooms.");
        System.out.println("3. See all reservations.");
        System.out.println("4. Add a room.");
        System.out.println("5. Add test data.");
        System.out.println("6. Back to Main Menu.");
        System.out.println();
    }

    private void seeAllCustomers() {
        System.out.println();
        this.adminResource.getAllCustomers().forEach(System.out::println);
        System.out.println();
        AdminMenu.getInstance().adminMenu();
    }

    private void seeAllRooms() {
        System.out.println();
        this.adminResource.getAllRooms().forEach(System.out::println);
        System.out.println();
        AdminMenu.getInstance().adminMenu();
    }

    private void seeAllReservations() {
        System.out.println();
        System.out.println(this.adminResource.displayAllReservations());
        System.out.println();
        AdminMenu.getInstance().displayAdminMenu();
    }

    private void addRoom() {

        String roomNumber, roomType, roomPrice;
        try {
            System.out.println("Enter room number: ");
            roomNumber = this.scanner.nextLine();

            System.out.println("Enter room price: ");
            roomPrice = this.scanner.nextLine();

            System.out.println("Enter room type with numbers: ");
            System.out.println("1. Single");
            System.out.println("2. Double");
            roomType = this.scanner.nextLine();

            IRoom room;

            if (RoomType.SINGLE.getAction().equals(roomType)) {
                room = new Room(roomNumber, Double.parseDouble(roomPrice), RoomType.SINGLE);
            } else if (RoomType.DOUBLE.getAction().equals(roomType)) {
                room = new Room(roomNumber, Double.parseDouble(roomPrice), RoomType.DOUBLE);
            } else {
                throw new Exception("Invalid room type.");
            }

            this.adminResource.addRoom(room);
            System.out.println();
            System.out.println("Room created successfully.");
            System.out.println();
            AdminMenu.getInstance().displayAdminMenu();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            AdminMenu.getInstance().addRoom();
        }
    }

    private void addTestData() {
        List<IRoom> rooms = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            IRoom room = new Room("1" + i, this.getRandomDouble(80.00, 150.00), this.generateRandomRoomType());
            rooms.add(room);
        }
        this.adminResource.addRooms(rooms);

        this.customerService.addCustomer("rayner", "mendez", "rayner@gmail.com");

        System.out.println();
        System.out.println("Test data added successfully.");
        System.out.println();
        AdminMenu.getInstance().displayAdminMenu();
    }

    private void backToMainMenu() {
        this.isRunning = false;
    }

    private Double getRandomDouble(final Double min, final Double max) {
        return Double.parseDouble(decimalFormat.format(min + (Math.random() * (max - min))));
    }

    private RoomType generateRandomRoomType() {
        int random = (int) (Math.random() * 2);
        return switch (random) {
            case 0 -> RoomType.SINGLE;
            default -> RoomType.DOUBLE;
        };
    }
}
