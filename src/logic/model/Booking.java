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
	 * The hotel name where the booking is located.
	 */
	protected String hotel;
	
	/**
	 * The user who create this booking.
	 */
	protected String user;
	
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
	public Booking(String hotel, String user, LocalDate checkIn, LocalDate checkOut, List<Person> people) {
		
		this.hotel = hotel;
		
		this.user = user;
		
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
	
	public String getHotel() {
		return hotel;
	}

	public String getUser() {
		return user;
	}

	public List<Person> getPeople() {
		return people;
	}

}
