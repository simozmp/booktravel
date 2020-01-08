package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Main;
import logic.bean.CityDateBean;
import logic.model.BookHotelController;
import logic.view.MainMenuView;

/**
 * 
 * @author metal
 *
 * MVC controller for the view MainMenuView.
 */
public class MainMenuController {
	
	/**
	 * Reference to the view.
	 */
	private MainMenuView view;
	
	/**
	 * Reference to the model
	 */
	private BookHotelController model;
	
	/**
	 * Constructor of the class.
	 * It initialize the state of the controller.
	 * 
	 * @param view 		the view.
	 * @param model		the model.
	 */
	public MainMenuController(MainMenuView view, BookHotelController model) {
		
		/* Set the new view and new model. */
		this.view = view;
		this.model = model;
		
		/* Add the handlers to buttons.  */
		this.view.addSearchListener(new SearchHandler());
		this.view.addMinusHanlder(new MinusHandler());
		this.view.addPlusHanlder(new PlusHandler());
	//	this.view.addLogInAsOwnerListener(new logInAsOwnerHandler());
		
	
	}
	/**
	 * 
	 * @author metal
	 *
	 * This class provide the implementation of EventHandler interface
	 * for the searchHandler button.
	 */
	private class SearchHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			/* Set invisible all errors. */
			view.setVisibleErrCityField(false);
			view.setVisibleErrCheckInField(false);
			view.setVisibleErrCheckOutField(false);
			view.setVisibleErrPersonCount(false);
			
			if( !view.getCityField().isEmpty() && view.getCheckInDate() != null && 
					view.getCheckOutDate() != null && Integer.parseInt(view.getPersonCount()) != 0 ) {
				
				/* The user filled all the fields. */
				if( view.getCheckInDate().isBefore(view.getCheckOutDate()) || view.getCheckInDate().equals(view.getCheckOutDate()) ) {
					
					/* The check-in date is before or equal to the check-out date.  */
					
					/* Fill the bean fields. */
					CityDateBean fields = new CityDateBean();
					fields.setCity(view.getCityField());
					fields.setCheckIn(view.getCheckInDate());
					fields.setCheckOut(view.getCheckOutDate());
					fields.setPersonCount(Integer.parseInt(view.getPersonCount()));
					
					try {
						
						/* Set the new controller and change the view. */
						new BookHotelListViewController(Main.getInstance().getBookHotelListView(), model, fields);
						
						Main.getInstance().changeToBookHotelListView();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					
					/* The Check-Out date is before the Check-In date. */
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("Check-Out cannot be before Check-In date.");
					
					alert.showAndWait();
					
				}				
				
			} else {
				
				/* The user doesn't fill all the fields. */
				if(view.getCityField().isEmpty())
					view.setVisibleErrCityField(true);
				
				if(view.getCheckInDate() == null)
					view.setVisibleErrCheckInField(true);
				
				if(view.getCheckOutDate() == null)
					view.setVisibleErrCheckOutField(true);
				
				if(Integer.parseInt(view.getPersonCount()) == 0)
					view.setVisibleErrPersonCount(true);
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class implements the EventHandler interface for the minus button.
	 */
	private class MinusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int personCount = Integer.parseInt(view.getPersonCount());
			personCount--;
			if(personCount == 0)
				view.disableMinusButton();
			view.setPersonCountText(String.valueOf(personCount));
			
		}
		
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
			
			int personCount = Integer.parseInt(view.getPersonCount());
			personCount++;
			view.enableMinusButton();
			view.setPersonCountText(String.valueOf(personCount));
			
		}
		
	}
	
	private class LogInAsOwnerHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			try {
			
				/* Set the new controller and change the view. */
			//	new ManageHotelListViewController(Main.getInstance().getManageHotelListView(), model, fields);
			
				Main.getInstance().changeToManageHotelListView();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}

}
