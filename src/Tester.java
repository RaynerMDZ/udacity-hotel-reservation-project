import api.AdminResource;
import api.HotelResource;
import resevation.ReservationService;
import resevation.ReservationServiceImpl;
import util.DataGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Tester {

    public static void main(java.lang.String[] args) throws ParseException {
        AdminResource adminResource = AdminResource.getInstance();
        HotelResource hotelResource = HotelResource.getInstance();
        ReservationService reservationService = ReservationServiceImpl.getInstance();

        DataGenerator.generateCustomer();
        DataGenerator.generateRooms();
        DataGenerator.generateReservation();

        System.out.println(adminResource.getAllCustomers());
        adminResource.getAllRooms().forEach(System.out::println);
        hotelResource.getCustomerReservations("rayner@gmail.com").forEach(System.out::println);
        reservationService.findRooms(new SimpleDateFormat("MM/dd/yyyy").parse("02/01/2023"), new SimpleDateFormat("MM/dd/yyyy").parse("02/20/2023")).forEach(System.out::println);


    }
}
