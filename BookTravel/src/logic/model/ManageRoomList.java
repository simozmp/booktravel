package logic.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;
import logic.controller.MainMenuController;

public class ManageRoomList {

	private static ManageRoomList instace = null;
	
	public ManageRoomList() {
	}
	
	
	
	public synchronized static ManageRoomList getInstance() {
		
		if(ManageRoomList.instace== null) 
			
			ManageRoomList.instace= new ManageRoomList();
		
		return ManageRoomList.instace;

	
	
	}
	

	
}
