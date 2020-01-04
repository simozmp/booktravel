package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.bean.CityDateBean;
import logic.bean.RoomBean;

/**
 * 
 * @author metal
 *	
 * Singleton class.
 * This class represent the controller of the use case "Book Hotel".
 */
public class BookHotelController {
	
	/**
	 * Reference to a list of all rentable place.
	 */
	private List<RentablePlace> rentablePlaces;
	
	/**
	 * Reference to an instance of this class.
	 */
	private static BookHotelController instance = null;
	
	/**
	 * Constructor of the class. Creates all the rentable place contained in database.
	 */
	public BookHotelController() {
		
		this.rentablePlaces = new ArrayList<RentablePlace>();
		
		this.rentablePlaces.add(new Hotel("hotel 1", "indirizzo 1", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 2", "indirizzo 2", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 3", "indirizzo 3", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 4", "indirizzo 4", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 5", "indirizzo 5", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 6", "indirizzo 6", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 7", "indirizzo 7", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 8", "indirizzo 8", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 9", "indirizzo 9", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 10", "indirizzo 10", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 11", "indirizzo 11", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 12", "indirizzo 12", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 13", "indirizzo 13", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 14", "indirizzo 14", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 15", "indirizzo 15", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 16", "indirizzo 16", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 17", "indirizzo 17", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 18", "indirizzo 18", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 19", "indirizzo 19", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 20", "indirizzo 20", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 21", "indirizzo 21", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 22", "indirizzo 22","Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 23", "indirizzo 23", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 24", "indirizzo 24", "Milano"));
//		this.rentablePlaces.add(new Hotel("hotel 25", "indirizzo 25","Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 26", "indirizzo 26", "Roma"));
//		this.rentablePlaces.add(new Hotel("hotel 27", "indirizzo 27", "Milano"));
		
	}
	
	/**
	 * Retrieve all rentable places.
	 * 
	 * @return list that contains rentable places.
	 */
	public List<RentablePlace> retrieveRentablePlaces() {
		
		List<RentablePlace> copy = new ArrayList<RentablePlace>(this.rentablePlaces);
		
		return copy;
		
	}
	
	/**
	 * 
	 * @param name 	the name of the rentable place we are searching.
	 * @return		the rentable place called as "name".
	 */
	public RentablePlace getRentablePlace(String name) { 
		
		for(RentablePlace rentablePlace : this.rentablePlaces) {
			
			if(rentablePlace.getName().equals(name))
				return rentablePlace;
			
		}				
		return null;				
	}
	
	/**
	 * Retrieve all rentable place available for the fields passed.
	 * 
	 * @param fields	bean that contains input of the user.
	 * @return			list of rentable place that satisfy the condition.
	 */
	public List<RentablePlace> retrieveRentablePlaces(CityDateBean fields) {
		
		List<RentablePlace> searchResult = new ArrayList<RentablePlace>();
		
		for( RentablePlace rentablePlace : this.rentablePlaces ) {
			
			if( rentablePlace.getCity().equals(fields.getCity()) && rentablePlace.isAvailable(fields) )
				
				/* The rentable place is available and and is located in the specified city. */
				searchResult.add(rentablePlace);
			
		}
		
		return searchResult;
		
	}
	
	/**
	 * Create a new booking and add it to the specified rentable place.
	 * 
	 * @param fields		bean that contains input of the user.
	 * @param people		list of people that want to book.
	 * @param roomBeans		room choice by the user.
	 * @param rentPlace		rentable place choice by the user.
	 */
	public void createBooking(CityDateBean fields, List<Person> people, List<RoomBean> roomBeans, RentablePlace rentPlace) {
		
		Booking booking = BookingFactory.getInstance().createActiveBooking(fields.getCheckIn(), fields.getCheckOut(), people);
		
		rentPlace.addActiveBooking(booking, roomBeans);
		
	}
	
	/**
	 * Get instance method.
	 * 
	 * @return the only instance of this class.
	 */
	public synchronized static BookHotelController getInstance() {
		
		if(BookHotelController.instance == null) 
			
			BookHotelController.instance = new BookHotelController();
		
		return BookHotelController.instance;
		
	}

}
