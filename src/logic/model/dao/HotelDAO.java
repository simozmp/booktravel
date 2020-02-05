package logic.model.dao;

import java.util.List;

import logic.bean.HotelBean;
import logic.model.Hotel;

public interface HotelDAO {

	/**
	 * Get all the hotel placed in a city.
	 * 
	 * @param city the name of the city.
	 * @return the list of the hotel placed in a specific city.
	 */
	public List<HotelBean> getAllHotelByCity(String city);

	/**
	 * Get all the hotel of an owner.
	 * 
	 * @param owner the owner of the hotel.
	 * @return the list of the hotel of the owner.
	 */
	public List<HotelBean> getAllHotelByOwner(String owner);

	/**
	 * Find the hotel with the specific id.
	 * 
	 * @param id the id of the hotel.
	 * @return the hotel that match the id.
	 */
	public HotelBean getHotel(int id);

	/**
	 * Create a new hotel in the db.
	 * 
	 * @param hotel the bean with the new data.
	 * @return the id of the hotel created.
	 */
	public int createHotel(HotelBean hotel);

	/**
	 * Update the hotel with the new data.
	 * 
	 * @param hotel the bean that contain new data.
	 * @return true if the hotel is modified, false otherwise.
	 */
	public boolean updateHotel(HotelBean hotel);

	public Hotel deleteHotel(Hotel hotel);

}
