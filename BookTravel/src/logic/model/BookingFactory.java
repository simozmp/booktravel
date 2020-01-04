package logic.model;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author metal
 * Singleton class.
 * Factory class. It has the responsibility of creating classes that specialize the Booking class.
 */
public class BookingFactory {
	
	/**
	 * Reference to an instance of this class.
	 */
	private static BookingFactory instance = null;
	
	/**
	 * Create a new ActiveBooking.
	 * 
	 * @param checkIn	check in date.
	 * @param checkOut	check out date.
	 * @param people	list of people for this booking.
	 * @return			a new ActiveBooking.
	 */
	public Booking createActiveBooking(LocalDate checkIn, LocalDate checkOut, List<Person> people) {
		
		return new ActiveBooking(checkIn, checkOut, people);
		
	}
	
	/**
	 * Get instance method. 
	 * @return	The instance of this class.
	 */
	public synchronized static BookingFactory getInstance() {
		
		if(BookingFactory.instance == null) 
			
			BookingFactory.instance = new BookingFactory();
		
		return BookingFactory.instance;
		
	}

}
