package logic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.bean.RoomBean;
import logic.model.bookingstate.StateEnum;
import logic.model.dao.BookingDAO;
import logic.model.dao.BookingDAOImpl;
import logic.model.dao.PersonDAO;
import logic.model.dao.PersonDAOImpl;

/**
 * 
 * @author metal
 *
 *         This class represent a room. It is a part of a rentable place. It
 *         exposes methods for checking the availability of itself for the given
 *         date.
 */
public class Room {
	/**
	 * User for identifying the room in the hotel.
	 */
	private int id;

	/**
	 * The description of the room.
	 */
	private String description;

	/**
	 * The number of beds that the room contain.
	 */
	private int beds;

	/**
	 * The size of the room, measured in square meters.
	 */
	private int size;

	/**
	 * Number of the toilets available.
	 */
	private int toilets;

	/**
	 * A reference to active bookings. Never pass it out of this class.
	 */
	private List<Booking> bookings;

	/**
	 * The constructor of the class.
	 * 
	 * @param description the description.
	 * @param beds        number of beds.
	 * @param size        size, measured in square meters.
	 * @param toilets     number of toilets.
	 */
	public Room(String description, int beds, int size, int toilets) {

		this.description = description;

		this.beds = beds;

		this.size = size;

		this.toilets = toilets;

		this.bookings = new ArrayList<>();

	}

	/**
	 * The constructor that take the bean as parameter.
	 * 
	 * @param roomBean the bean that contain data of the room.
	 */
	public Room(RoomBean roomBean) {
		this(roomBean.getDescription(), roomBean.getBeds(), roomBean.getSize(), roomBean.getToilets());
		this.id = roomBean.getId();

		BookingDAO dao = new BookingDAOImpl();
		List<BookingBean> bookingBeans = dao.getAllBookingOfARoom(roomBean.getId());

		for (BookingBean bookingBean : bookingBeans) {
			this.bookings.add(new Booking(bookingBean));
		}
	}

	/**
	 * Check if the room is available for the given date.
	 * 
	 * @param checkIn  the Check-In date.
	 * @param checkOut the Check-Out date.
	 * @return true if the room is available, false otherwise.
	 */
	public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {

		for (Booking booking : this.getActiveBooking()) {

			/* For each active booking. */
			if (checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn()))

				/* The given date conflicts with another date of a booking. */
				return false;

			if (checkIn.equals(booking.getCheckIn()) && checkOut.equals(booking.getCheckOut()))

				/* The given date conflict exactly with another date. */
				return false;
		}

		return true;

	}

	/**
	 * Get all booking that are in submitted state or accepted state.
	 * 
	 * @return all active booking.
	 */
	public List<Booking> getActiveBooking() {

		List<Booking> activeBooking = new ArrayList<>();
		StateEnum state;

		for (Booking booking : this.bookings) {
			state = booking.getEnumValueOfState();

			switch (state) {
			case SUBMITTED:
				activeBooking.add(booking);
				break;
			case ACCEPTED:
				activeBooking.add(booking);
				break;
			default:
				break;
			}

		}

		return activeBooking;

	}

	/**
	 * Find the booking for the given id, if exist.
	 * 
	 * @param id the id of the booking to find.
	 * @return the booking that match the id, if exist, otherwise null.
	 */
	public Booking getBooking(int id) {

		for (Booking booking : this.bookings)
			if (booking.getId() == id)
				return booking;

		return null;

	}

	/**
	 * Add a new booking to the room, and add a row in the database.
	 * 
	 * @param activeBooking the new booking.
	 */
	public void addActiveBooking(Booking newBooking) {

		this.bookings.add(newBooking);
		BookingBean bookingPOJO = new BookingBean();
		bookingPOJO.setCheckIn(newBooking.getCheckIn());
		bookingPOJO.setCheckOut(newBooking.getCheckOut());
		bookingPOJO.setState(newBooking.getEnumValueOfState());
		bookingPOJO.setUser(newBooking.getUser());

		BookingDAO dao = new BookingDAOImpl();
		int idBooking = dao.createBooking(bookingPOJO, this.id);

		PersonDAO personDAO = new PersonDAOImpl();
		for (Person person : newBooking.getPeople()) {
			personDAO.createPerson(person, idBooking);
		}

	}

	/**
	 * Get the description.
	 * 
	 * @return the description attribute.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the number of beds contained in that room.
	 * 
	 * @return the beds attribute.
	 */
	public int getBeds() {
		return this.beds;
	}

	/**
	 * Get the size of the room, measured in square meters.
	 * 
	 * @return the size attribute.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Get the number of toilets.
	 * 
	 * @return the toilets attribute.
	 */
	public int getToilets() {
		return this.toilets;
	}

	/**
	 * @author Adri
	 * 
	 *         the follow metod set the attribute for a room
	 */
	public void setBeds(int i) {
		this.beds = i;

	}

	public void setSize(int i) {
		this.size = i;

	}

	public void setToilets(int i) {
		this.toilets = i;

	}

	public void setDescription(String s) {
		this.description = s;

	}

	/**
	 * Retrieve all bookings made by the user for this room.
	 * 
	 * @param username
	 * @return list of booking.
	 */
	public List<Booking> getAllBookingOfThisUser(String username) {
		List<Booking> userBookings = new ArrayList<>();

		for (Booking booking : this.bookings) {

			if (booking.getUser().equals(username))
				userBookings.add(booking);

		}

		return userBookings;
	}

}
