package logic.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import logic.Main;
import logic.bean.CityDateBean;
import logic.bean.RoomBean;
import logic.model.RentablePlace;
import logic.model.BookHotelController;
import logic.model.LoginController;
import logic.view.HotelView;

/**
 * 
 * @author metal
 *
 * MVC controller for the view HotelView.
 */
public class HotelViewController extends MainViewController {
	
	/**
	 * Reference to the view.
	 */
	private HotelView hotelView;
	
	/**
	 * Reference to the model.
	 */
	private RentablePlace hotelModel;
	
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
		
		super(view);
		
		/* Set the new view and the new model. */
		this.hotelView = (HotelView) super.view;
		this.hotelModel = model;
		this.fields = fields;
		
		/* Set the data to the view. */
		this.hotelView.setName(this.hotelModel.getName());
		this.hotelView.setAddress(this.hotelModel.getAddress());
		this.hotelView.setInformation(this.hotelModel.getDescription());
		
		/* Set the view to represent how much rooms are available. */
		this.setRoomAvailability();
		
		/* Add handlers to button. */
		this.hotelView.addBackHandler(new BackHandler());
		this.hotelView.addBookHandler(new BookHandler());
		
	}
	
	/**
	 * Set the view to represent how much rooms are available
	 */
	public void setRoomAvailability() {
		
		List<RoomBean> roomsAvailability = hotelModel.getAvailableRooms(fields);
		
		this.hotelView.createRoomSelector(roomsAvailability, new PlusHandler(), new MinusHandler());
		
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
			
			HotelView.RoomSelector roomSelector = hotelView.getRoomSelector(index);		/* The component of the view that contains the button clicked. */
			
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
			
			for( HotelView.RoomSelector roomSel : hotelView.getAllRoomSelectors() ) {
				
				numberOfRooms = Integer.parseInt( roomSel.getRoomChoise() );	/* Number of rooms choice for this beds. */
				
				int numberOfBeds = Integer.parseInt( roomSel.getNumberOfBeds() );	/* Number of beds for this type of room. */
				
				totalBeds = totalBeds + numberOfRooms*numberOfBeds;			/* Update total beds. */
				
			}
			
			if( totalBeds >= fields.getPersonCount() ) {
				
				/* Total beds for the rooms selected reached the person count, so all the plus buttons can be disabled. */
				for (HotelView.RoomSelector roomSel : hotelView.getAllRoomSelectors() ) {
					
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
			
			HotelView.RoomSelector roomSelector = hotelView.getRoomSelector(index);		/* The corresponding part of the view for the button clicked. */
			
			int numberOfRooms = Integer.parseInt( roomSelector.getRoomChoise() );	/* Number of rooms selected by the user. */
			
			numberOfRooms--;	/* Decrease the counter. */
			
			if( numberOfRooms == 0 )
				
				/* The counter reached 0, so the minus button can be disabled */
				roomSelector.disableMinusButton();
			
			roomSelector.setRoomChoise( String.valueOf( numberOfRooms ) );		/* Update with new value. */
			
			for (HotelView.RoomSelector roomSel : hotelView.getAllRoomSelectors() ) {
				
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
			
			if(LoginController.getInstance().isLogged()) {
			
				List<RoomBean> roomBeans = new ArrayList<>();	/* List of beans. */
				
				hotelView.setErrVisible(false);
				
				int totalBeds = 0;
				
				for(HotelView.RoomSelector roomSelector : hotelView.getAllRoomSelectors()) {
					
					if( Integer.parseInt(roomSelector.getRoomChoise()) > 0) {
					
						/* The user has selected this type of room.  */
						RoomBean roomBean = new RoomBean();
						
						roomBean.setBeds(roomSelector.getNumberOfBeds());
						
						roomBean.setRoomChoise(roomSelector.getRoomChoise());
						
						roomBeans.add(roomBean);
						
						totalBeds += Integer.parseInt(roomSelector.getNumberOfBeds()) * Integer.parseInt(roomSelector.getRoomChoise());
						
					}
					
				}
				
				if( totalBeds >= fields.getPersonCount() ) {
					
					try {
						
						/* Change view and start new controller. */
						Main.getInstance().changeToBookingView();
						new BookingViewController(Main.getInstance().getBookingView(), hotelModel, fields, roomBeans);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else {
					
					hotelView.setErrVisible(true);
					
				}
			
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("You have to login before booking!");
				
				alert.showAndWait();
				
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
				e.printStackTrace();
			}
			
		}
		
	}

}



