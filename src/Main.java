import menu.AdminMenu;
import menu.HotelMenu;


public class Main {

    private static final HotelMenu hotelMenu = HotelMenu.getInstance();
    private static final AdminMenu adminMenu = AdminMenu.getInstance();

    public static void main(String[] args) {
        hotelMenu.mainMenu();
        System.out.println("Goodbye!");
    }
}