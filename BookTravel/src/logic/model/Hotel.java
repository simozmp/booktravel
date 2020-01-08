package logic.model;

import java.util.List;

/**
 * 
 * @author metal
 *
 * This class represent an hotel, the concrete class that extends the RentablePlace class.
 */
public class Hotel extends RentablePlace {
	
	/**
	 * Constructor of the class, it just call the super constructor.
	 * 
	 * @param name		name of the hotel.
	 * @param address	address of the hotel.
	 * @param city		city where hotel is located.
	 */
	public Hotel(String name, String address, String city) {
		
		super(name, address, city);

	}
	
	public Hotel(String name, String address, String city, List<Room> rooms) {
		
		super(name, address, city, rooms);
		
	}

}
