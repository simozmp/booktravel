 package logic.model;

import java.util.ArrayList;
import java.util.List;


import logic.bean.RoomBean;

import logic.model.dao.RoomDAO;
import logic.model.dao.RoomDAOImpl;

public class ManageRoomList {

	private static ManageRoomList instace = null;
	private List<RoomBean> rooms;

	public ManageRoomList() {

		this.rooms = new ArrayList<>();
	
	}
	
	
	public List<RoomBean> retrieveRoom(int id) {

		RoomDAO dao = new RoomDAOImpl();
		return dao.getAllRoomOfAnHotel(id);
	}
	
	public List<RoomBean> retrieveRooms() {

		return new ArrayList<>(this.rooms);

	}

	public  List<RoomBean>  retrieveRooms(int hotelId) {
		RoomDAO dao = new RoomDAOImpl();
		return dao.getAllRoomOfAnHotel(hotelId);
		
		
	}
	
	
	
	
	public static synchronized  ManageRoomList getInstance() {

		if (ManageRoomList.instace == null)

			ManageRoomList.instace = new ManageRoomList();

		return ManageRoomList.instace;

	}
	
	public static void createRoom(int id) {
		RoomBean bean1 =new RoomBean("camera", 2, 15, 1, 4);
		
		RoomDAO dao = new RoomDAOImpl();
		dao.createRoom(bean1, id);
	}

}
