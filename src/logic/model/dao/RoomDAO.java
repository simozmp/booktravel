package logic.model.dao;

import java.util.List;

import logic.bean.RoomBean;

public interface RoomDAO {

	/**
	 * Find all rooms contained in an hotel.
	 * 
	 * @param hotelId the id of the hotel.
	 * @return the list of the room contained in the hotel.
	 */
	public List<RoomBean> getAllRoomOfAnHotel(int hotelId);

	/**
	 * Create a new room in the hotel with the specific id.
	 * 
	 * @param room    the new room.
	 * @return the id of the new room.
	 */
	public int createRoom(RoomBean room,int hotelId);

	/**
	 * Update the room with the new data.
	 * 
	 * @param room the room with the new data.
	 * @return true if update go well, false otherwise.
	 */
	public boolean updateRoom(RoomBean room);


}
