package logic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author metal
 *
 * This class represent a booking. It is abstract, so it cannot be instantiated.
 * You have to instantiate the concrete class.
 */
public abstract class Booking {
	
	/**
	 * The Check-In date.
	 */
	protected LocalDate checkIn;
	
	/**
	 * The Check-Out date.
	 */
	protected LocalDate checkOut;
	
	/**
	 * A reference to a list of people which the booking is composed.
	 * Never pass it out of this class.
	 */
	protected List<Person> people;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param checkIn	the check in date.
	 * @param checkOut	the check out date.
	 * @param people	the list of people for this booking
	 */
	public Booking(LocalDate checkIn, LocalDate checkOut, List<Person> people) {
		
		this.checkIn = checkIn;
		
		this.checkOut = checkOut;
		
		this.people = new ArrayList<Person>(people);
		
	}

	/**
	 * Get the check in date.
	 * 
	 * @return	check in attribute.
	 */
	public LocalDate getCheckIn() {	return checkIn; }

	/**
	 * Get the check out date.
	 * 
	 * @return	check out attribute.
	 */
	public LocalDate getCheckOut() { return checkOut; }	

}
