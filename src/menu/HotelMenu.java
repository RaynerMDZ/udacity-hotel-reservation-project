package menu;

import api.HotelResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HotelMenu {
    
    private static HotelMenu instance = null;
    private final Scanner scanner = new Scanner(System.in);
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
                Integer choice = Integer.parseInt(this.scanner.nextLine());
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
            String checkInDate = this.scanner.nextLine();

            System.out.println("Enter checkOut date mm/dd/yyyy example (12/31/2019): ");
            String checkOutDate = this.scanner.nextLine();

            System.out.println("Enter your email: ");
            String email = this.scanner.nextLine();

            System.out.println("Enter the room number: ");
            String roomNumber = this.scanner.nextLine();

            var reservation = this.hotelResource.bookARoom(
                    email,
                    this.hotelResource.getRoom(roomNumber),
                    new SimpleDateFormat("MM/dd/yyyy").parse(checkInDate),
                    new SimpleDateFormat("MM/dd/yyyy").parse(checkOutDate)
            );

            System.out.println();
            System.out.println("Room reserved successfully.\n");
            System.out.println();
            System.out.println("Your reservation is: " + reservation);
            System.out.println();

            HotelMenu.getInstance().displayMainMenu();
        } catch (Throwable t) {
            System.out.println("Error: " + t.getMessage());
            System.out.println();
            HotelMenu.getInstance().displayMainMenu();
        }
    }

    public void seeMyReservation() {

        System.out.println();
        System.out.println("Enter your email: ");
        String email = this.scanner.nextLine();

        System.out.println(this.hotelResource.getCustomerReservations(email));
        System.out.println();
        HotelMenu.getInstance().mainMenu();
    }

    public void createAnAccount() {

        String firstName = null, lastName = null, email = null;
        try {
            System.out.println("Enter your first name: ");
            firstName = this.scanner.nextLine();
            System.out.println("Enter your last name: ");
            lastName = this.scanner.nextLine();
            System.out.println("Enter your email: ");
            email = this.scanner.nextLine();

            this.hotelResource.createACustomer(firstName, lastName, email);
            System.out.println();
            System.out.println("Account created successfully.");
            System.out.println();
            HotelMenu.getInstance().displayMainMenu();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            HotelMenu.getInstance().displayMainMenu();
        }
    }

    public void adminMenu() {
        AdminMenu.getInstance().adminMenu();
    }

    public void exit() {
        this.isRunning = false;
    }
}
