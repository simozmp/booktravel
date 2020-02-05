package logic.model.dao;

import java.util.List;

import logic.bean.BookingBean;

public interface BookingDAO {

	/**
	 * Retrieve all booking of a specific Room.
	 * 
	 * @param roomId the id of the room.
	 * @return the list of bookings that match with the room id.
	 */
	public List<BookingBean> getAllBookingOfARoom(int roomId);

	/**
	 * Retrieve all bokkings made by an user.
	 * 
	 * @param username the username.
	 * @return list of bookings made by an user.
	 */
	public List<BookingBean> getAllBookingOfAUser(String username);

	/**
	 * Retrieve a booking that match the current id.
	 * 
	 * @param id the id of the booking.
	 * @return the booking that match with the id.
	 */
	public BookingBean getBooking(int id);

	/**
	 * Create a new booking.
	 * 
	 * @param booking the new booking
	 * @return the id of the new booking.
	 */
	public int createBooking(BookingBean booking, int roomId);

	/**
	 * Update the booking on the db with the new data.
	 * 
	 * @param booking the booking with updated field.
	 * @return true if update go well, false otherwise.
	 */
	public boolean updateBooking(BookingBean booking);

}
