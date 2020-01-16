package logic.model;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;
import logic.controller.MainMenuController;

public class ManageRoomList {

	private static ManageRoomList instace = null;
	private  List<Room> rooms;
	
	public ManageRoomList() {

		this.rooms = new ArrayList<Romm>();
		this.rooms. addAll(new Room("Bella camera", 2, 20, 1));
	}
	
	public List<Room> retrieveRooms() {
		
		List<Room> copy = new ArrayList<Room>(this.rooms);
		
		return copy;
		
	}
	
	public RentablePlace getRoom(String name) { 
		
		for(Room rentablePlace : this.rooms) {
			
			if(rentablePlace.getName().equals(name))
				return rentablePlace;
			
		}				
		return null;				
	}
	
	public List<RentablePlace> retrieveRooms(String owner) {
		
		List<RentablePlace> searchResult = new ArrayList<RentablePlace>();
		
		for( Room rentablePlace : this.rooms ) {
			
			if( rentablePlace.getOwner().equals(owner))
				
				searchResult.add(rentablePlace);
			
		}
		
		return searchResult;
		
	}
	
	public synchronized static ManageRoomList getInstance() {
		
		if(ManageRoomList.instace== null) 
			
			ManageRoomList.instace= new ManageRoomList();
		
		return ManageRoomList.instace;

	
	
	}
	

	
}
