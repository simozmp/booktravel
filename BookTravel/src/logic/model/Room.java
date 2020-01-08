 package logic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author metal
 *
 * This class represent a room. It is a part of a rentable place.
 * It exposes methods for checking the availability of itself for the given date.
 */
public class Room {
	
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
	 * A reference to active bookings.
	 * Never pass it out of this class.
	 */
	private List<ActiveBooking> activeBookings = new ArrayList<ActiveBooking>();
	
	/**
	 * The constructor of the class.
	 * 
	 * @param description	the description.
	 * @param beds			number of beds.
	 * @param size			size, measured in square meters.
	 * @param toilets		number of toilets.
	 */
	public Room(String description, int beds, int size, int toilets) {
		
		this.description = description;
		
		this.beds = beds;
		
		this.size = size;
		
		this.toilets = toilets;
		
	}
	

	/**
	 * Check if the room is available for the given date.
	 * 
	 * @param checkIn		the Check-In date.
	 * @param checkOut		the Check-Out date.
	 * @return				true if the room is available, false otherwise.
	 */
	public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
		
		for(ActiveBooking activeBooking : this.activeBookings) {
			
			/* For each active booking. */
			if( checkIn.isBefore(activeBooking.getCheckOut()) && checkOut.isAfter(activeBooking.getCheckIn()) )
				
				/* The given date conflicts with another date of a booking. */
				return false;
			
			if( checkIn.equals(activeBooking.getCheckIn()) && checkOut.equals(activeBooking.checkOut) )
				
				/* The given date conflict exactly with another date. */
				return false;
		}
		
		return true;
		
	}
	
	/**
	 * Add a new booking to the room.
	 * 
	 * @param activeBooking		the new booking.
	 */
	public void addActiveBooking(ActiveBooking activeBooking) {
		
		this.activeBookings.add(activeBooking);
		
	}

	/**
	 * Get the description.
	 * 
	 * @return	the description attribute.
	 */
	public String getDescription() { return this.description; }

	/** 
	 * Get the number of beds contained in that room.
	 * 
	 * @return the beds attribute.
	 */
	public int getBeds() { return this.beds; }

	/**
	 * Get the size of the room, measured in square meters.
	 * 
	 * @return 	the size attribute.
	 */
	public int getSize() { return this.size; }

	/**
	 * Get the number of toilets.
	 * 
	 * @return the toilets attribute.
	 */
	public int getToilets() { return this.toilets; }
	
	
	
	/**
	 * @author Adri
	 * 
	 * the follow metod set the attribute for a room
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
		
}
