package logic.model;

import java.util.ArrayList;
import java.util.List;

public class RentablePlacesCatalog {
	
	private List<RentablePlace> rentablePlaces;
	
	private static RentablePlacesCatalog instance = null;
	
	public RentablePlacesCatalog() {
		
		this.rentablePlaces = new ArrayList<RentablePlace>();
		
		this.rentablePlaces.add(new Hotel("hotel 1", "indirizzo 1"));
		this.rentablePlaces.add(new Hotel("hotel 2", "indirizzo 2"));
		this.rentablePlaces.add(new Hotel("hotel 3", "indirizzo 3"));
		this.rentablePlaces.add(new Hotel("hotel 4", "indirizzo 4"));
		this.rentablePlaces.add(new Hotel("hotel 5", "indirizzo 5"));
		this.rentablePlaces.add(new Hotel("hotel 6", "indirizzo 6"));
		this.rentablePlaces.add(new Hotel("hotel 7", "indirizzo 7"));
		this.rentablePlaces.add(new Hotel("hotel 8", "indirizzo 8"));
		this.rentablePlaces.add(new Hotel("hotel 9", "indirizzo 9"));
		this.rentablePlaces.add(new Hotel("hotel 10", "indirizzo 10"));
		this.rentablePlaces.add(new Hotel("hotel 11", "indirizzo 11"));
		this.rentablePlaces.add(new Hotel("hotel 12", "indirizzo 12"));
		this.rentablePlaces.add(new Hotel("hotel 13", "indirizzo 13"));
		this.rentablePlaces.add(new Hotel("hotel 14", "indirizzo 14"));
		this.rentablePlaces.add(new Hotel("hotel 15", "indirizzo 15"));
		this.rentablePlaces.add(new Hotel("hotel 16", "indirizzo 16"));
		this.rentablePlaces.add(new Hotel("hotel 17", "indirizzo 17"));
		this.rentablePlaces.add(new Hotel("hotel 18", "indirizzo 18"));
		this.rentablePlaces.add(new Hotel("hotel 19", "indirizzo 19"));
		this.rentablePlaces.add(new Hotel("hotel 20", "indirizzo 20"));
		this.rentablePlaces.add(new Hotel("hotel 21", "indirizzo 21"));
		this.rentablePlaces.add(new Hotel("hotel 22", "indirizzo 22"));
		this.rentablePlaces.add(new Hotel("hotel 23", "indirizzo 23"));
		this.rentablePlaces.add(new Hotel("hotel 24", "indirizzo 24"));
		this.rentablePlaces.add(new Hotel("hotel 25", "indirizzo 25"));
		this.rentablePlaces.add(new Hotel("hotel 26", "indirizzo 26"));
		this.rentablePlaces.add(new Hotel("hotel 27", "indirizzo 27"));
		
	}
	
	public List<RentablePlace> retrieveRentablePlaces() {
		
		List<RentablePlace> copy = new ArrayList<RentablePlace>(this.rentablePlaces);
		
		return copy;
		
	}
	
	public RentablePlace getRentablePlace(String name) { 
		
		for(RentablePlace rentablePlace : this.rentablePlaces) {
			
			if(rentablePlace.getName().equals(name))
				return rentablePlace;
			
		}
		
		return null;				
	}
	
	public synchronized static RentablePlacesCatalog getInstance() {
		
		if(RentablePlacesCatalog.instance == null) 
			
			RentablePlacesCatalog.instance = new RentablePlacesCatalog();
		
		return RentablePlacesCatalog.instance;
		
	}

}
