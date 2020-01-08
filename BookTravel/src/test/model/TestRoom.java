package test.model;

import logic.model.ActiveBooking;
import logic.model.Person;
import logic.model.Room;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author metal
 *
 * This is a test class for the logic class Room.
 * It tests the method isAvailable() in different ways.
 */
public class TestRoom {
	
	/**
	 * Setup method for running the test.
	 * 
	 * @return		room with mock data.
	 */
	public Room setup() {
		
		Room room = new Room("prova", 2, 20, 1);
		
		List<Person> people = new ArrayList<Person>();
		
		room.addActiveBooking(new ActiveBooking(LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5), people));
		
		room.addActiveBooking(new ActiveBooking(LocalDate.of(2020, 1, 6), LocalDate.of(2020, 1, 10), people));
		
		room.addActiveBooking(new ActiveBooking(LocalDate.of(2050, 1, 1), LocalDate.of(2050, 1, 7), people));
		
		room.addActiveBooking(new ActiveBooking(LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1), people));
		
		return room;
		
	}
	
	@Test
	public void testIsAvailableNotAvailable() {
		
		Room room = this.setup();
		
		boolean available = room.isAvailable(LocalDate.of(2020, 1, 4), LocalDate.of(2020, 1, 6));
		
		assertEquals(false, available);
		
	}
	
	@Test
	public void testIsAvailableAvailable() {
		
		Room room = this.setup();
		
		boolean available = room.isAvailable(LocalDate.of(2020, 1, 11), LocalDate.of(2020, 12, 31));
		
		assertEquals(true, available);
		
	}
	
	@Test
	public void testIsAvailableLongIntervalDate() {
		
		Room room = this.setup();
		
		boolean available = room.isAvailable(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 10, 1));
		
		assertEquals(false, available);
		
	}
	
	@Test
	public void testIsAvailableSameDateNotAvailable() {
		
		Room room = this.setup();
		
		boolean available = room.isAvailable(LocalDate.of(2020, 1, 5), LocalDate.of(2020, 1, 5));
		
		assertEquals(false, available);
		
	}
	
	@Test
	public void testIsAvailableSameDateAvailable() {
		
		Room room = this.setup();
		
		boolean available = room.isAvailable(LocalDate.of(2020, 1, 11), LocalDate.of(2020, 1, 11));
		
		assertEquals(true, available);
		
	}

}
