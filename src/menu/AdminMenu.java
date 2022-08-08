package menu;

import api.AdminResource;
import room.IRoom;
import room.Room;
import room.RoomType;
import util.DataGenerator;

import java.util.Scanner;

public class AdminMenu {

    private final AdminResource adminResource = AdminResource.getInstance();
    private final static Scanner scanner = new Scanner(System.in);

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

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> this.seeAllCustomers();
                    case 2 -> this.seeAllRooms();
                    case 3 -> this.seeAllReservations();
                    case 4 -> this.addRoom();
                    case 5 -> this.addTestData();
                    case 6 -> HotelMenu.getInstance().mainMenu();
                    default -> System.out.println("Error: Invalid input. Please enter a number between 1 and 6.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please use only numbers.\n");
            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        }
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
        AdminMenu.getInstance().adminMenu();
    }

    private void addRoom() {

        String roomNumber, roomType, roomPrice;
        try {
            System.out.println("Enter room number: ");
            roomNumber = scanner.nextLine();

            System.out.println("Enter room price: ");
            roomPrice = scanner.nextLine();

            System.out.println("Enter room type with numbers: ");
            System.out.println("1. Single");
            System.out.println("2. Double");
            roomType = scanner.nextLine();

            IRoom room;

            if (RoomType.SINGLE.getAction().equals(roomType)) {
                room = new Room(roomNumber, Double.parseDouble(roomPrice), RoomType.SINGLE, false);
            } else if (RoomType.DOUBLE.getAction().equals(roomType)) {
                room = new Room(roomNumber, Double.parseDouble(roomPrice), RoomType.DOUBLE, false);
            } else {
                throw new Exception("Invalid room type.");
            }

            this.adminResource.addRoom(room);
            System.out.println();
            System.out.println("Room created successfully.");
            System.out.println();
            AdminMenu.getInstance().adminMenu();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            AdminMenu.getInstance().adminMenu();
        }
    }

    public void addTestData() {
        DataGenerator.generateCustomer();
        DataGenerator.generateRooms();
        DataGenerator.generateReservation();
        AdminMenu.getInstance().adminMenu();
    }
}
