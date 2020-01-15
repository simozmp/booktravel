package logic.model;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;
import logic.controller.MainMenuController;

public class ManageRoomList {

	private static ManageRoomList instace = null;
	
	private List<RentablePlace> rooms;
	
	public ManageRoomList() {
		
		this.rooms = new ArrayList<RentablePlace>();
		
		// this.rooms.add(new Room("Bella camera", 2, 20, 1));
		// this.rooms.add(new Room("Bella camera 2", 3, 25, 1));		

	}
	
	
	
	public synchronized static ManageRoomList getInstance() {
		
		if(ManageRoomList.instace== null) 
			
			ManageRoomList.instace= new ManageRoomList();
		
		return ManageRoomList.instace;

	
	
	}
	

	
}
