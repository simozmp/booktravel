package logic.model;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author metal
 *
 * The concrete class that extends Booking.
 */
public class ActiveBooking extends Booking {

	/**
	 * Constructor of the class, it just call the super constructor.
	 * 
	 * @param checkIn	check in date.
	 * @param checkOut	check out date.
	 * @param people	list of people for this booking.
	 */
	public ActiveBooking(LocalDate checkIn, LocalDate checkOut, List<Person> people) {
		
		super(checkIn, checkOut, people);

	}

}
