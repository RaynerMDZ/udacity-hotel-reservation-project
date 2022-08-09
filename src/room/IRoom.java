package room;

/**
 * Room interface
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
    public boolean isReserved();
    public void book();
    public void cancel();

}
