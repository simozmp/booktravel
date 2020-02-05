package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.bean.HotelBean;
import logic.model.dao.HotelDAO;
import logic.model.dao.HotelDAOImpl;

/**
 * 
 * @author Adri
 *
 */

public class ManageHotelList {

	private List<RentablePlace> rentablePlaces;

	private static ManageHotelList instace = null;

	public ManageHotelList() {

		this.rentablePlaces = new ArrayList<>();
	}
	
	public List<HotelBean> retrieveHotelByOwner(String owner) {

		HotelDAO dao = new HotelDAOImpl();
		return dao.getAllHotelByOwner(owner);
	}
	
	
	public RentablePlace getRentablePlace(int id) {

		HotelDAO dao = new HotelDAOImpl();
		HotelBean hotelBean = dao.getHotel(id);
		RentablePlace hotel = new Hotel(hotelBean);

		this.rentablePlaces.add(hotel);

		return hotel;

	}


	public RentablePlace getRentablePlace(String name) {

		for (RentablePlace rentablePlace : this.rentablePlaces) {

			if (rentablePlace.getName().equals(name))
				return rentablePlace;

		}
		return null;
	}


	public static synchronized  ManageHotelList getInstance() {

		if (ManageHotelList.instace == null)

			ManageHotelList.instace = new ManageHotelList();

		return ManageHotelList.instace;

	}
	
	public static void CreateHotel(HotelBean bean) {
	
		HotelDAO dao = new HotelDAOImpl();
		 dao.createHotel(bean);
	}
	
	public static void DeleteHotel(int id){
		HotelBean hBean = new HotelDAOImpl().getHotel(id);
		Hotel hotel = new Hotel(hBean);
		HotelDAO dao = new HotelDAOImpl();
		dao.deleteHotel(hotel);
	}


	
}
