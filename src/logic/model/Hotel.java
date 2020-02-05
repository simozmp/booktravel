package logic.model;

import java.util.List;

import logic.bean.HotelBean;
import logic.bean.RoomBean;
import logic.model.dao.RoomDAO;
import logic.model.dao.RoomDAOImpl;

/**
 * 
 * @author metal
 *
 *         This class represent an hotel, the concrete class that extends the
 *         RentablePlace class.
 */
public class Hotel extends RentablePlace {

	/**
	 * Constructor of the class, it just call the super constructor.
	 * 
	 * @param name    name of the hotel.
	 * @param address address of the hotel.
	 * @param city    city where hotel is located.
	 */
	public Hotel(String name, String address, String city, String owner) {

		super(name, address, city, owner);

	}

	/**
	 * Constructor of the class.
	 * 
	 * @param name    name of the hotel.
	 * @param address address of the hotel.
	 * @param city    city where hotel is located.
	 * @param rooms   rooms available in the hotel.
	 */
	public Hotel(String name, String address, String city, List<Room> rooms, String owner) {

		super(name, address, city, rooms, owner);

	}

	public Hotel(HotelBean bean) {

		super(bean.getName(), bean.getAddress(), bean.getCity(), bean.getDescription(), bean.getOwner());

		RoomDAO dao = new RoomDAOImpl();
		List<RoomBean> roomBeans = dao.getAllRoomOfAnHotel(bean.getId());

		for (RoomBean roomBean : roomBeans) {
			super.rooms.add(new Room(roomBean));
		}
	}

}