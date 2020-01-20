package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.bean.CityDateBean;
import logic.bean.HotelBean;
import logic.bean.LoginBean;
import logic.bean.RoomBean;
import logic.model.dao.BookingDAO;
import logic.model.dao.BookingDAOImpl;
import logic.model.dao.HotelDAO;
import logic.model.dao.HotelDAOImpl;
import logic.model.dao.PersonDAO;
import logic.model.dao.PersonDAOImpl;

/**
 * 
 * @author metal
 *	
 * Singleton class.
 * This class represent the controller of the use case "Book Hotel".
 */
public class BookHotelController {
	
	/**
	 * Reference to a list of all rentable place.
	 */
	private List<RentablePlace> rentablePlaces;
	
	/**
	 * Reference to an instance of this class.
	 */
	private static BookHotelController instance = null;
	
	/**
	 * Constructor of the class. Creates all the rentable place contained in database.
	 */
	private BookHotelController() {
		
		this.rentablePlaces = new ArrayList<RentablePlace>();
		
	}
	
	/**
	 * Retrieve all hotel placed in a city.
	 * 
	 * @param city	the name of the city.
	 * @return		the list of bean that contain data.
	 */
	public List<HotelBean> retrieveHotelByCity(String city) {
		List<HotelBean> hotels = new ArrayList<HotelBean>();
		
		HotelDAO dao = new HotelDAOImpl();
		hotels = dao.getAllHotelByCity(city);		
		
		return hotels;
	}
	
	/**
	 * 
	 * @param id	the id of the rentable place we are searching.
	 * @return		the rentable place that match with the id.
	 */
	public RentablePlace getRentablePlace(int id) { 
		
		HotelDAO dao = new HotelDAOImpl();
		HotelBean hotelBean = dao.getHotel(id);
		RentablePlace hotel = new Hotel(hotelBean);
		
		this.rentablePlaces.add(hotel);
		
		return hotel;
						
	}
	
	/**
	 * Retrieve the booking by the id and change its state to delete.
	 * 
	 * @param id	the booking to delete.
	 */
	public void deleteBooking(int id) {
		
		BookingBean bookingBean = new BookingDAOImpl().getBooking(id);
		
		Booking booking = new Booking(bookingBean);
		booking.delete();
		
	}
	
	public void resubmitBooking(int id) {
		BookingBean bookingBean = new BookingDAOImpl().getBooking(id);
		
		Booking booking = new Booking(bookingBean);
		booking.resubmit();
	}
	
	/**
	 * Retrieve all rentable place available for the fields passed.
	 * 
	 * @param fields	bean that contains input of the user.
	 * @return			list of rentable place that satisfy the condition.
	 */
	public List<RentablePlace> retrieveRentablePlaces(CityDateBean fields) {
		
		List<RentablePlace> searchResult = new ArrayList<RentablePlace>();
		
		for( RentablePlace rentablePlace : this.rentablePlaces ) {
			
			if( rentablePlace.getCity().equals(fields.getCity()) && rentablePlace.isAvailable(fields) )
				
				/* The rentable place is available and and is located in the specified city. */
				searchResult.add(rentablePlace);
			
		}
		
		return searchResult;
		
	}
	
	/**
	 * Create a new booking and add it to the specified rentable place.
	 * 
	 * @param loginBean
	 * @param fields
	 * @param people
	 * @param roomBeans
	 * @param rentPlace
	 */
	public void createBooking(CityDateBean fields, List<Person> people, List<RoomBean> roomBeans, RentablePlace rentPlace) {
		
		Booking booking = BookingFactory.getInstance().createBooking
				(rentPlace.getName(), LoginController.getInstance().getUsername(), fields.getCheckIn(), fields.getCheckOut(), people);
		
		rentPlace.addActiveBooking(booking, roomBeans);
		
	}
	
	/**
	 * Retrieve all booking made by an user in the system.
	 * 
	 * @param loginBean	input of the user.
	 * @return	
	 */
	public List<BookingBean> retrieveBookingOfAnUser(LoginBean loginBean) {
		
		List<BookingBean> bookings = new ArrayList<BookingBean>();
		
		BookingDAO dao = new BookingDAOImpl();
		bookings.addAll(dao.getAllBookingOfAUser(loginBean.getUsername()));
		
		PersonDAO daoPerson = new PersonDAOImpl();
		for(BookingBean bean : bookings) {
			bean.setPeople(daoPerson.getAllPeopleOfABooking(bean.getBookingId()));
		}
		
		return bookings;
		
	}
	
	/**
	 * Get instance method.
	 * 
	 * @return the only instance of this class.
	 */
	public synchronized static BookHotelController getInstance() {
		
		if(BookHotelController.instance == null) 
			
			BookHotelController.instance = new BookHotelController();
		
		return BookHotelController.instance;
		
	}

}
