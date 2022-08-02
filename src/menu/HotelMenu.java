package menu;

import api.HotelResource;

public class HotelMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();

    public void mainMenu(int choice) {
        this.displayMainMenu();
        switch (choice) {
            case 1 -> this.findAndReserveRoom();
            case 2 -> this.seeMyReservation();
            case 3 -> this.createAnAccount();
            case 4 -> this.adminMenu();
            case 5 -> this.exit();
            default -> System.out.println("Error: Invalid input. Please enter a number between 1 and 5.\n");
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
        System.out.println("Find and reserve a room.");
    }

    public void seeMyReservation() {
        System.out.println("See my reservation.");
    }

    public void createAnAccount() {
        System.out.println("Create an account.");
    }

    public void adminMenu() {
        System.out.println("Admin.");
    }

    public void exit() {
        System.out.println("Exit.");
    }
}
