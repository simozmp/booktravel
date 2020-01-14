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
	public Hotel(String name, String address, String city, String Owner) {
		
		super(name, address, city, Owner);
		
		this.rooms.add(new Room("Bella camera", 2, 20, 1));
		this.rooms.add(new Room("Bella camera 2", 3, 25, 1));
		this.rooms.add(new Room("Bella camera 3", 4, 30, 2));
		this.rooms.add(new Room("Bella camera 4", 5, 35, 2));

	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param name			name of the hotel.
	 * @param address		address of the hotel.
	 * @param city			city where hotel is located.
	 * @param rooms			rooms available in the hotel.
	 */
	public Hotel(String name, String address, String city, List<Room> rooms, String Owner) {
		
		super(name, address, city, rooms, Owner);
		
	}

}