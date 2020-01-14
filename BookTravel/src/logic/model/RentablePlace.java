package logic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.bean.CityDateBean;
import logic.bean.RoomBean;


/**
 * 
 * @author metal
 *
 * This class represent a rentable place. It is abstract, then if you want to use its 
 * methods you have to instantiate its concrete subclasses.
 * This class exposes methods to manage the rooms of which it is composed.
 */
public abstract class RentablePlace {
	
	/**
	 * A reference to rooms of which it is composed.
	 * Never pass this reference out of this class.
	 */
	protected List<Room> rooms;
	
	/**
	 * The name of the rentable place.
	 */
	protected String name;
	
	/**
	 * The address of the rentable place.	
	 */
	protected String address;
	
	/**
	 * The city where the rentable place is located.
	 */
	protected String city;
	
	/**
	 * The description of the rentable place.
	 */
	protected String description;
	
	
	protected String Owner;
	
	/**
	 * Constructor of the class. It initialize the state of the object and
	 * creates the rooms of which is composed, retrieving them from database.
	 * 
	 * @param name
	 * @param address
	 * @param city
	 */
	public RentablePlace(String name, String address, String city, String Owner) {
		
		this.rooms = new ArrayList<Room>();
		
		this.name = name;
		this.address = address;
		this.city = city;
		this.Owner = Owner;
		
	}
	
	/**
	 * Another constructor, it take as a parameter the list of the rooms.
	 * @param name
	 * @param address
	 * @param city
	 * @param rooms
	 */
	public RentablePlace(String name, String address, String city, List<Room> rooms, String Owner) {
		
		this.name = name;
		this.address = address;
		this.city = city;
		this.Owner = Owner;
		
		this.rooms = new ArrayList<Room>(rooms);
		
	}
	
	/**
	 * Another constructor, it also set the description.
	 * 
	 * @param name
	 * @param address
	 * @param city
	 * @param description
	 */
	public RentablePlace(String name, String address, String city, String description, String Owner) {
		
		this.name = name;
		
		this.address = address;
		
		this.city = city;
		
		this.description = description;
		
		this.Owner = Owner;
		
	}
	
	/**
	 * Add a new room to the rentable place.
	 * 
	 * @param description	description.
	 * @param beds			number of beds.
	 * @param size			size, measured in square meters.
	 * @param toilets		number of toilets.
	 */
	public void addNewRoom(String description, int beds, int size, int toilets) {
		
		this.rooms.add(new Room(description, beds, size, toilets));
		
	}
	
	/**
	 * Method used for testing.
	 * 
	 * @param newRoom	the new room.
	 */
	public void addNewRoom(Room newRoom) {
		
		this.rooms.add(newRoom);
		
	}
	
	/**
	 * Check if there is a room available for the data contained in fields.
	 * 
	 * @param fields	bean that contain Check-In date, Check-Out date and number of people that want book.
	 * @return			true if there is rooms available for containing all people, false otherwise.
	 */
	public boolean isAvailable(CityDateBean fields) {
		
		int availability = 0;	/* Counter to keep track of how much beds are available. */
		
		for( Room room : this.rooms ) {
			
			if( room.isAvailable(fields.getCheckIn(), fields.getCheckOut()) )
				
				/* The room is available for the given date. */
				availability += room.getBeds();
			
		}
		
		if( availability >= fields.getPersonCount() )
			
			/* There are sufficient beds to contain all people. */
			return true;
		
		else
			
			/* There are not sufficient beds to contain all people. */
			return false;
		
	}
	
	/**
	 * Check how much rooms of a specified number of beds are available.
	 * 
	 * @param beds		the number of beds
	 * @param fields	bean that contain fields, such as Check-In date and Check-Out date
	 * @return			the bean RoomBean, that represent how much rooms are available for the specified number of beds.
	 */
	public RoomBean getNumberOfRoomByBeds(int beds, CityDateBean fields) {
		
		RoomBean roomBean = new RoomBean();
		
		int availability = 0; 	/* Counter to keep track of availability. */
		
		for( Room room : this.rooms ) {
			
			if( room.isAvailable(fields.getCheckIn(), fields.getCheckOut()) && room.getBeds() == beds )
				
				/* The room is available and the number of beds it contains are equal to the one specified in formal parameter. */
				availability++;
			
		}
		
		roomBean.setBeds(String.valueOf(beds));
		
		roomBean.setAvailability(String.valueOf(availability));
		
		return roomBean;
		
	}
	
	/**
	 * It finds an amount of rooms specified by "number" that contain an amount of "beds" and that 
	 * are available for the given date.
	 * 
	 * @param beds			the number of beds that the rooms must contain.
	 * @param number		the number of the rooms necessary.
	 * @param checkIn		the Check-In date.
	 * @param checkOut		the Check-Out date.
	 * @return				list of rooms that satisfy the condition.
	 */
	public List<Room> getRooms(int beds, int number, LocalDate checkIn, LocalDate checkOut) {
		
		List<Room> rooms = new ArrayList<Room>();
		
		int i = 0;	/* Counter for sliding the list. */
		
		while(number > 0) {
			
			/* While the number of rooms added are not satisfied. */
			Room room = this.rooms.get(i);
			
			if( room.isAvailable(checkIn, checkOut) && room.getBeds() == beds ) {
				
				/* The room is available for the given date and contain the specified amount of beds. */
				rooms.add(room);
				
				number--;
				
			}
			
			i++;
			
		}
		
		return rooms;
		
	}
	
	/**
	 * Add a new booking to the rooms specified in roomBeans.
	 * 
	 * @param activeBooking		the new booking.
	 * @param roomBeans			the list of RoomBean. Each RoomBean must contain the number of beds required
	 * 							and the number of room required for this specified beds.
	 */
	public void addActiveBooking(Booking activeBooking, List<RoomBean> roomBeans) {
		
		LocalDate checkIn = activeBooking.getCheckIn();
		
		LocalDate checkOut = activeBooking.getCheckOut();
		
		for( RoomBean roomBean : roomBeans ) {
			
			for( Room room : this.getRooms(roomBean.getBeds(), roomBean.getRoomChoise(), checkIn, checkOut) ) {
				
				/* For each room specified in roomBean. */
				room.addActiveBooking((ActiveBooking) activeBooking);
				
			}
			
		}
		
	}
	
	/**
	 * Set the new description.
	 * 
	 * @param description the new description.
	 */
	public void setDescription(String description) { this.description = description; }
	
	/**
	 * Retrieve the city where the rentable place is located.
	 * 
	 * @return	the city attribute.
	 */
	public String getCity() { return this.city; }

	/**
	 * Retrieve the name of the rentable place.
	 * 
	 * @return	the name attribute.
	 */
	public String getName() { return this.name; }
	
	/**
	 * Retrieve the address of the rentable place.
	 * 
	 * @return	the address attribute.
	 */
	public String getAddress() { return this.address; }
	
	/**
	 * Never call this method. It needs for testing.
	 * 
	 * @return
	 */
	public List<Room> getRooms() { return this.rooms; }
	
	/**
	 * @author Adri
	 * @return
	 */
	
	public String getOwner() { return this.Owner;}

	/**
	 * Retrieve all bookings of an user in this rentablePlace.
	 * 
	 * @param username
	 * @return			return list of bookings of this user.
	 */
	public List<Booking> getBookingsByUser(String username) {
		
		List<Booking> bookings = new ArrayList<Booking>();
		
		for(Room room : this.rooms) {
			
			bookings.addAll(room.getAllBookingOfThisUser(username));
			
		}
		
		return bookings;
	}

}
