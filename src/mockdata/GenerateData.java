package mockdata;

import model.IRoom;
import model.Room;
import model.RoomType;
import service.RoomService;
import service.RoomServiceImpl;

import java.text.DecimalFormat;

public class GenerateData implements Runnable {
    private final DecimalFormat decimalFormat = new DecimalFormat("###.##");
    private final RoomService roomService = RoomServiceImpl.getInstance();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            IRoom room = new Room("1" + i, this.getRandomDouble(80.00, 150.00), this.generateRandomRoomType());
            this.roomService.addRoom(room);
        }
    }

    private Double getRandomDouble(Double min, Double max) {
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
