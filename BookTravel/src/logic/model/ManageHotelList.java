package logic.model;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author Adri
 *
 */

public class ManageHotelList {
	
	private  List<RentablePlace> rentablePlaces;
	
	private static ManageHotelList instace = null;

	
	
	public ManageHotelList() {
		
		
		this.rentablePlaces = new ArrayList<RentablePlace>();
		
		this.rentablePlaces.add(new Hotel("hotel 1", "indirizzo 1", "Roma", "owner"));
		
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
	
	public List<RentablePlace> retrieveRentablePlaces(String owner) {
		
		List<RentablePlace> searchResult = new ArrayList<RentablePlace>();
		
		for( RentablePlace rentablePlace : this.rentablePlaces ) {
			
			if( rentablePlace.getOwner().equals(owner))
				
				searchResult.add(rentablePlace);
			
		}
		
		return searchResult;
		
	}
	public synchronized static ManageHotelList getInstance() {
		
		if(ManageHotelList.instace== null) 
			
			ManageHotelList.instace= new ManageHotelList();
		
		return ManageHotelList.instace;

	
	
}
}
