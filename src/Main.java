import mockdata.GenerateData;
import service.RoomService;
import service.RoomServiceImpl;

import java.util.Scanner;

public class Main {

    private static final RoomService roomService = RoomServiceImpl.getInstance();
    public static void main(String[] args) {

        GenerateData generateData = new GenerateData();
        Thread thread = new Thread(generateData);
        thread.start();

        boolean isRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                try {

                    Integer choice = Integer.parseInt(scanner.nextLine());

                    roomService.getAllRooms().forEach(room -> {
                        System.out.println(room.toString());
                    });



                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please use only numbers.\n");
                } catch (Throwable t) {
                    System.out.println("Error: " + t.getMessage());
                }
            }
        }
        System.out.println("Goodbye!");
    }
}