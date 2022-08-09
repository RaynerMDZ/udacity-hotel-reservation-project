package room;

import java.util.Collection;

/**
 * Room Repository interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public interface RoomRepository {
    public Collection<IRoom> getAllRooms();
    public IRoom getRoom(String roomNumber) throws IllegalArgumentException;
    public void createRoom(IRoom room) throws IllegalArgumentException;
    public void createRooms(Collection<IRoom> rooms) throws IllegalArgumentException;
    public void removeRoom(String roomNumber) throws IllegalArgumentException;
    public void updateRoom(IRoom room);
}
