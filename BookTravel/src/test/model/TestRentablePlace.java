package test.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.bean.CityDateBean;
import logic.bean.RoomBean;
import logic.model.ActiveBooking;
import logic.model.Hotel;
import logic.model.Person;
import logic.model.RentablePlace;
import logic.model.Room;

/**
 * 
 * @author metal
 *
 * Test class for logic class RentablePlace.
 */
public class TestRentablePlace {
	
	/**
	 * Setup method used for running the test.
	 * 
	 * @return	RentablePlace with mock data.
	 */
	public RentablePlace setup() {
		
		RentablePlace rentablePlace = new Hotel("nome", "indirizzo", "città", "Proprietario");
		
		List<Person> people = new ArrayList<Person>();
		
		Room room1 = new Room("camera 1", 2, 20, 1);
		room1.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		room1.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 1, 6), LocalDate.of(2020, 2, 10), people));
		rentablePlace.addNewRoom(room1);
		
		Room room2 = new Room("camera 2", 3, 25, 1);
		room2.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		room2.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 2, 5), LocalDate.of(2020, 2, 15), people));
		rentablePlace.addNewRoom(room2);
		
		Room room3 = new Room("camera 3", 4, 30, 1);
		room3.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		room3.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 2, 5), LocalDate.of(2020, 2, 15), people));
		rentablePlace.addNewRoom(room3);
		
		Room room4 = new Room("camera 4", 5, 30, 2);
		room4.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		room4.addActiveBooking(new ActiveBooking(null, null, LocalDate.of(2020, 5, 5), LocalDate.of(2020, 6, 15), people));
		rentablePlace.addNewRoom(room4);
		
		Room room5 = new Room("camera 5", 2, 30, 1);
		Room room6 = new Room("camera 6", 2, 30, 1);
		Room room7 = new Room("camera 7", 2, 30, 1);
		Room room8 = new Room("camera 8", 3, 30, 1);
		rentablePlace.addNewRoom(room5);
		rentablePlace.addNewRoom(room6);
		rentablePlace.addNewRoom(room7);
		rentablePlace.addNewRoom(room8);
		
		return rentablePlace;
		
	}
	
	@Test
	public void testIsAvailableAvailable() {
		
		CityDateBean fields = new CityDateBean();
		
		fields.setCheckIn(LocalDate.of(2020, 1, 11));
		fields.setCheckOut(LocalDate.of(2020, 1, 15));
		fields.setPersonCount(1);
		
		RentablePlace rentablePlace = this.setup();
		
		boolean available = rentablePlace.isAvailable(fields);
		
		assertEquals(true, available);
		
	}
	
	@Test
	public void testIsAvailableBigNumberNotAvailable() {
		
		CityDateBean fields = new CityDateBean();
		
		fields.setCheckIn(LocalDate.of(2020, 1, 11));
		fields.setCheckOut(LocalDate.of(2020, 1, 15));
		fields.setPersonCount(1000);
		
		RentablePlace rentablePlace = this.setup();
		
		boolean available = rentablePlace.isAvailable(fields);
		
		assertEquals(false, available);
		
	}
	
	@Test
	public void testIsAvailableLimitNumberAvailable() {
		
		CityDateBean fields = new CityDateBean();
		
		fields.setCheckIn(LocalDate.of(2020, 1, 1));
		fields.setCheckOut(LocalDate.of(2020, 1, 1));
		fields.setPersonCount(14);	/* The total availability of the hotel is 14. */
		
		RentablePlace rentablePlace = this.setup();
		
		boolean available = rentablePlace.isAvailable(fields);
		
		assertEquals(true, available);
		
	}
	
	@Test
	public void testGetNumberOfRoomsByBedsTwoBedsWithoutActiveBooking() {
		
		CityDateBean fields = new CityDateBean();
		
		fields.setCheckIn(LocalDate.of(2020, 1, 1));
		fields.setCheckOut(LocalDate.of(2020, 1, 2));
		
		RentablePlace rentablePlace = this.setup();
		
		RoomBean actual = rentablePlace.getNumberOfRoomByBeds(2, fields);
		
		assertEquals(2, actual.getBeds());
		assertEquals(4, actual.getAvailability());
		
	}
	
	@Test
	public void testGetNumberOfRoomsByBedsTwoBedsWithActiveBooking() {
		
		CityDateBean fields = new CityDateBean();
		
		fields.setCheckIn(LocalDate.of(2020, 1, 5));
		fields.setCheckOut(LocalDate.of(2020, 1, 10));
		
		RentablePlace rentablePlace = this.setup();
		
		RoomBean actual = rentablePlace.getNumberOfRoomByBeds(2, fields);
		
		assertEquals(2, actual.getBeds());
		assertEquals(3, actual.getAvailability());
		
	}
	
	@Test
	public void testGetRoomsTwoRoomsFiveBeds() {
		
		RentablePlace hotel = this.setup();
		
		Room roomExpected1 = new Room("camera 10", 5, 30, 2);
		Room roomExpected2 = new Room("camera 11", 5, 30, 2);
		
		hotel.getRooms().add(roomExpected1);
		hotel.getRooms().add(roomExpected2);
		
		List<Room> actual = hotel.getRooms(5, 2, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5));
		
		assertEquals(2, actual.size());
		assertEquals(true, actual.contains(roomExpected1));
		assertEquals(true, actual.contains(roomExpected2));
		
	}
	
	@Test
	public void testGetRoomsOneRoomsFiveBeds() {
		
		RentablePlace hotel = this.setup();
		
		Room roomExpected1 = new Room("camera 10", 5, 30, 2);
		
		hotel.getRooms().add(roomExpected1);
		hotel.getRooms().add(new Room("camera 11", 5, 30, 2));
		
		List<Room> actual = hotel.getRooms(5, 1, LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5));
		
		assertEquals(1, actual.size());
		assertEquals(true, actual.contains(roomExpected1));	
		
	}

}
