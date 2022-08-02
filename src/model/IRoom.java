package model;

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
    public boolean isReserved();
    public void book();
    public void cancel();

}
