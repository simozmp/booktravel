package logic.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import logic.Main;
import logic.bean.CityDateBean;
import logic.bean.RoomBean;
import logic.model.RentablePlace;
import logic.model.BookHotelController;
import logic.view.HotelView;

/**
 * 
 * @author metal
 *
 * MVC controller for the view HotelView.
 */
public class HotelViewController {
	
	/**
	 * Reference to the view.
	 */
	private HotelView view;
	
	/**
	 * Reference to the model.
	 */
	private RentablePlace model;
	
	/**
	 * Reference to the bean that contain data input of the user.
	 */
	private CityDateBean fields;
	
	/**
	 * Constructor of the class.
	 * It initialize the state of the controller. Also set the data to the view.
	 * 
	 * @param view		the view.
	 * @param model		the model.
	 * @param fields	bean that contains data input of the user.
	 */
	public HotelViewController(HotelView view, RentablePlace model, CityDateBean fields) {
		
		/* Set the new view and the new model. */
		this.view = view;
		this.model = model;
		this.fields = fields;
		
		/* Set the data to the view. */
		this.view.setName(this.model.getName());
		this.view.setAddress(this.model.getAddress());
		this.view.setInformation("Bell'Hotel proprio!");
		
		/* Set the view to represent how much rooms are available. */
		this.setRoomAvailability();
		
		/* Add handlers to button. */
		this.view.addBackHandler(new BackHandler());
		this.view.addBookHandler(new BookHandler());
		
	}
	
	/**
	 * Set the view to represent how much rooms are available
	 */
	public void setRoomAvailability() {
		
		List<RoomBean> roomsAvailability = new ArrayList<RoomBean>();
		
		if( this.fields.getPersonCount() == 1 ) {
			
			roomsAvailability.add(this.model.getNumberOfRoomByBeds(2, this.fields));
			
		} else {
		
			for( int i = 1; i <= this.fields.getPersonCount(); i++ ) {
				
				RoomBean roomBean = this.model.getNumberOfRoomByBeds(i, this.fields);
				
				if(roomBean.getAvailability() != 0)
				
					roomsAvailability.add(roomBean);
				
			}
			
		}
		
		this.view.createRoomSelector(roomsAvailability, new PlusHandler(), new MinusHandler());
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class implements the EventHandler interface for the plus button.
	 */
	private class PlusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int index = Integer.parseInt( ((Control)event.getSource()).getId() ); 	/* The id of the button clicked. */
			
			HotelView.RoomSelector roomSelector = view.getRoomSelector(index);		/* The component of the view that contains the button clicked. */
			
			int numberOfRooms = Integer.parseInt( roomSelector.getRoomChoise() );	/* Number of room entered by the user. */
			
			if( numberOfRooms < Integer.parseInt(roomSelector.getRoomAvailability()) ) {
			
				/* Number of rooms entered are less than the availability. */
				
				numberOfRooms++;		/* Increase the value. */
				
				roomSelector.setRoomChoise( String.valueOf( numberOfRooms ) );	/* Set the new value. */
				
				roomSelector.enableMinusButton();		/* After the press of the plus button the minus button can be enabled. */
				
				if( numberOfRooms == Integer.parseInt(roomSelector.getRoomAvailability()) )
					
					/* The number of rooms selected by the user reached the total availability. */
					roomSelector.disablePlusButton();
				
			}
			
			int totalBeds = 0;		/* Counter for the beds. */
			
			for( HotelView.RoomSelector roomSel : view.getAllRoomSelectors() ) {
				
				numberOfRooms = Integer.parseInt( roomSel.getRoomChoise() );	/* Number of rooms choice for this beds. */
				
				int numberOfBeds = Integer.parseInt( roomSel.getNumberOfBeds() );	/* Number of beds for this type of room. */
				
				totalBeds = totalBeds + numberOfRooms*numberOfBeds;			/* Update total beds. */
				
			}
			
			if( totalBeds >= fields.getPersonCount() ) {
				
				/* Total beds for the rooms selected reached the person count, so all the plus buttons can be disabled. */
				for (HotelView.RoomSelector roomSel : view.getAllRoomSelectors() ) {
					
					roomSel.disablePlusButton();
					
				}
				
			}			
			
		}		
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class provides the implementation of EventHandler interface for the minus button.
	 */
	private class MinusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int index = Integer.parseInt( ((Control)event.getSource()).getId() );  	/* The id of the button clicked. */
			
			HotelView.RoomSelector roomSelector = view.getRoomSelector(index);		/* The corresponding part of the view for the button clicked. */
			
			int numberOfRooms = Integer.parseInt( roomSelector.getRoomChoise() );	/* Number of rooms selected by the user. */
			
			numberOfRooms--;	/* Decrease the counter. */
			
			if( numberOfRooms == 0 )
				
				/* The counter reached 0, so the minus button can be disabled */
				roomSelector.disableMinusButton();
			
			roomSelector.setRoomChoise( String.valueOf( numberOfRooms ) );		/* Update with new value. */
			
			for (HotelView.RoomSelector roomSel : view.getAllRoomSelectors() ) {
				
				/* After a decrease all the plus button can be enabled. */
				roomSel.enablePlusButton();
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class provides the implementation of EventHandler interface for the book button.
	 */
	private class BookHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			List<RoomBean> roomBeans = new ArrayList<RoomBean>();	/* List of beans. */
			
			for(HotelView.RoomSelector roomSelector : view.getAllRoomSelectors()) {
				
				if( Integer.parseInt(roomSelector.getRoomChoise()) > 0) {
				
					/* The user has selected this type of room.  */
					RoomBean roomBean = new RoomBean();
					
					roomBean.setBeds(roomSelector.getNumberOfBeds());
					
					roomBean.setRoomChoise(roomSelector.getRoomChoise());
					
					roomBeans.add(roomBean);
					
				}
				
			}
			
			try {
				
				/* Change view and start new controller. */
				Main.getInstance().changeToBookingView();
				new BookingViewController(Main.getInstance().getBookingView(), model, fields, roomBeans);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class provides the implementation of EventHandler interface for the back button.
	 */
	private class BackHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			try {
				
				/* Change the view and start new controller. */
				Main.getInstance().changeToBookHotelListView();
				new BookHotelListViewController(Main.getInstance().getBookHotelListView(),	BookHotelController.getInstance(), fields);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
