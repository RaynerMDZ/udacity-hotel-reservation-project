package menu;

import api.HotelResource;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class HotelMenu {
    
    private static HotelMenu instance = null;
    private final static Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    private HotelMenu() {
    }

    public static HotelMenu getInstance() {
        if (instance == null) {
            instance = new HotelMenu();
        }
        return instance;
    }
    
    private final HotelResource hotelResource = HotelResource.getInstance();

    public void mainMenu() {
        this.displayMainMenu();
        while (this.isRunning) {
            try {
                Integer choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> this.findAndReserveRoom();
                    case 2 -> this.seeMyReservation();
                    case 3 -> this.createAnAccount();
                    case 4 -> this.adminMenu();
                    case 5 -> this.exit();
                    default -> System.out.println("Error: Invalid input. Please enter a number between 1 and 5.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please use only numbers.\n");
            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        }
    }

    public void displayMainMenu() {
        System.out.println("****** Main Menu ******");
        System.out.println("1. Find and reserve a room.");
        System.out.println("2. See my reservation.");
        System.out.println("3. Create an account.");
        System.out.println("4. Admin.");
        System.out.println("5. Exit.");
    }

    public void findAndReserveRoom() throws ParseException {
        try {
            System.out.println("Enter checkIn date mm/dd/yyyy example (12/31/2019): ");
            String checkInDate = scanner.nextLine();

            System.out.println("Enter checkOut date mm/dd/yyyy example (12/31/2019): ");
            String checkOutDate = scanner.nextLine();

            System.out.println("Enter your email: ");
            String email = scanner.nextLine();

            System.out.println();
            this.hotelResource.findARoom(new SimpleDateFormat("MM/dd/yyyy").parse(checkInDate), new SimpleDateFormat("MM/dd/yyyy").parse(checkOutDate)).forEach(System.out::println);
            System.out.println();

            System.out.println("Enter the room number: ");
            String roomNumber = scanner.nextLine();

            var room = this.hotelResource.getRoom(roomNumber);
            Reservation reservation = null;

            if (room.isReserved()) {
                throw new IllegalArgumentException("Room is already reserved.");
            } else {
                room.book();
                reservation = this.hotelResource.bookARoom(
                        email,
                        room,
                        new SimpleDateFormat("MM/dd/yyyy").parse(checkInDate),
                        new SimpleDateFormat("MM/dd/yyyy").parse(checkOutDate)
                );
            }

            System.out.println();
            System.out.println("Room reserved successfully.\n");
            System.out.println();
            System.out.println("Your reservation is: " + reservation);
            System.out.println();

            HotelMenu.getInstance().mainMenu();
        } catch (Throwable t) {
            System.out.println();
            System.out.println("Error: " + t.getMessage());
            System.out.println();
            HotelMenu.getInstance().mainMenu();
        }
    }

    public void seeMyReservation() {

        System.out.println();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println();
        System.out.println(this.hotelResource.getCustomerReservations(email));
        System.out.println();
        HotelMenu.getInstance().mainMenu();
    }

    public void createAnAccount() {

        String firstName = null, lastName = null, email = null;
        try {
            System.out.println("Enter your first name: ");
            firstName = scanner.nextLine();
            System.out.println("Enter your last name: ");
            lastName = scanner.nextLine();
            System.out.println("Enter your email: ");
            email = scanner.nextLine();

            this.hotelResource.createACustomer(firstName, lastName, email);
            System.out.println();
            System.out.println("Account created successfully.");
            System.out.println();
            HotelMenu.getInstance().mainMenu();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            HotelMenu.getInstance().mainMenu();
        }
    }

    public void adminMenu() {
        AdminMenu.getInstance().adminMenu();
    }

    public void exit() {
        this.isRunning = false;
    }
}
