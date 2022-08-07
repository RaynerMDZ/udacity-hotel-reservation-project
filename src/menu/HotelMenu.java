package menu;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
                int choice = Integer.parseInt(scanner.nextLine());
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

    public void findAndReserveRoom() {
        try {
            System.out.println("Enter checkIn date mm/dd/yyyy example (12/31/2022): ");
            String checkIn = scanner.nextLine();
            Date checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkIn);

            System.out.println("Enter checkOut date mm/dd/yyyy example (12/31/2022): ");
            String checkOut = scanner.nextLine();
            Date checkOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkOut);

            System.out.println("Enter your email: ");
            String email = scanner.nextLine();

            if (hotelResource.getCustomer(email) == null) {
                throw new IllegalArgumentException("Customer does not exist. Create an account first.");
            }

            System.out.println();
            Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);

            if (rooms.isEmpty()) {
                System.out.println("No rooms available for the given dates.");
                System.out.println();
                System.out.println("Would you like to check next week (y/n)");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("y")) {

                    Date checkInDateNextWeek = new Date(checkInDate.getTime() + 604800000);
                    Date checkOutDateNextWeek = new Date(checkOutDate.getTime() + 604800000);
                    rooms = hotelResource.checkRoomsForNextWeek(checkInDateNextWeek, checkOutDateNextWeek);

                    if (rooms.isEmpty()) {
                        throw new IllegalArgumentException("No rooms available for next week");
                    } else {
                        System.out.println();
                        System.out.println("Rooms available the week of " + new SimpleDateFormat("MM/dd/yyyy").format(checkInDateNextWeek) + " to " + new SimpleDateFormat("MM/dd/yyyy").format(checkOutDateNextWeek));
                        System.out.println();
                        rooms.forEach(System.out::println);
                        System.out.println();
                    }
                }

            } else {
                rooms.forEach(System.out::println);
                    System.out.println();
            }

            System.out.println("Enter the room number: ");
            String roomNumber = scanner.nextLine();

            var room = this.hotelResource.getRoom(roomNumber);
            Collection<Reservation> reservations = null;

            if (room.isReserved()) {
                System.out.println("Room is already reserved.");
                System.out.println();
                System.out.println("Would you like to check next week (y/n)");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("y")) {

                    Date checkInDateNextWeek = new Date(checkInDate.getTime() + 604800000);
                    Date checkOutDateNextWeek = new Date(checkOutDate.getTime() + 604800000);

                    System.out.println();
                    System.out.println("Rooms available the week of " + new SimpleDateFormat("MM/dd/yyyy").format(checkInDateNextWeek) + " to " + new SimpleDateFormat("MM/dd/yyyy").format(checkOutDateNextWeek));
                    System.out.println();
                    rooms = hotelResource.checkRoomsForNextWeek(checkInDateNextWeek, checkOutDateNextWeek);
                    if (rooms.isEmpty()) {
                        throw new IllegalArgumentException("No rooms available for the given dates.");
                    } else {
                        rooms.forEach(System.out::println);
                    }
                    System.out.println();

                    System.out.println("Enter the room number: ");
                    roomNumber = scanner.nextLine();
                    room = this.hotelResource.getRoom(roomNumber);
                    reservations = this.hotelResource.bookARoom(email, room, checkInDateNextWeek, checkOutDateNextWeek);
                }

                System.out.println();
                System.out.println("Room reserved successfully.\n");
                System.out.println();
                System.out.println("Your reservations are:");
                assert reservations != null;
                reservations.forEach(System.out::println);
                System.out.println();

                HotelMenu.getInstance().mainMenu();
            } else {
                room.book();
                reservations = this.hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
            }

            System.out.println();
            System.out.println("Room reserved successfully.\n");
            System.out.println();
            System.out.println("Your reservations are:");
            assert reservations != null;
            reservations.forEach(System.out::println);
            System.out.println();

            HotelMenu.getInstance().mainMenu();
        } catch (ParseException e) {
            System.out.println();
            System.out.println("Error: Invalid date format. Please use mm/dd/yyyy. Numbers only.");
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
