package menu;

import api.AdminResource;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();

    public void adminMenu (int choice) {
        this.displayAdminMenu();
        switch (choice) {
            case 1 -> this.seeAllCustomers();
            case 2 -> this.seeAllRooms();
            case 3 -> this.seeAllReservations();
            case 4 -> this.addRoom();
            case 5 -> this.addTestData();
            case 6 -> this.backToMainMenu();
            default -> System.out.println("Error: Invalid input. Please enter a number between 1 and 6.\n");
        }
    }

    private void displayAdminMenu() {
        System.out.println("****** Admin Menu ******");
        System.out.println("1. See all customers.");
        System.out.println("2. See all rooms.");
        System.out.println("3. See all reservations.");
        System.out.println("4. Add a room.");
        System.out.println("5. Add test data.");
        System.out.println("6. Back to Main Menu.");
    }

    private void seeAllCustomers() {
        System.out.println("See all customers.");
    }

    private void seeAllRooms() {
        System.out.println("See all rooms.");
    }

    private void seeAllReservations() {
        System.out.println("See all reservations.");
    }

    private void addRoom() {
        System.out.println("Add a room.");
    }

    private void addTestData() {
        System.out.println("Add test data.");
    }

    private void backToMainMenu() {
        System.out.println("Back to Main Menu.");
    }
}
