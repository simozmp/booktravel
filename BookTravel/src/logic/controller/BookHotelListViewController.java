package logic.controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.Main;
import logic.bean.CityDateBean;
import logic.model.BookHotelController;
import logic.model.LoginController;
import logic.model.RentablePlace;
import logic.view.BookHotelListView;
import logic.view.LoginView;

/**
 * 
 * @author metal
 *
 * MVC controller of the view BookHotelListView.
 */
public class BookHotelListViewController {
	
	/**
	 * Reference to the view.
	 */
	private BookHotelListView view;
	
	/**
	 * Reference to the model.
	 */
	private BookHotelController model;
	
	/**
	 * Reference to the bean, that will contain the input of the user.
	 */
	private CityDateBean fields;
	
	/**
	 * Constructor of the class.
	 * It initialize the state of the object and initialize the view with the data of the model.
	 * 
	 * @param view		the view.
	 * @param model		the model.
	 * @param fields	bean, input of the user.
	 */
	public BookHotelListViewController(BookHotelListView view, BookHotelController model, CityDateBean fields) {
		
		this.view = view;
		this.model = model;
		this.fields = fields;
		
		if ( this.model.retrieveRentablePlaces(this.fields).isEmpty() )
			
			/* The input of the doesn't produce result. */
			this.view.resultNotFound();
		
		else
			
			/* Set the data found to the view. */
			this.view.populateView(this.model.retrieveRentablePlaces(this.fields), new MoreInformationHandler());
		
		if(LoginController.getInstance().isLogged())
			this.view.loggedView(LoginController.getInstance().getUsername());
		
		/* Add handlers to buttons. */
		this.view.addSearchListener(new SearchHandler());
		this.view.addMinusHanlder(new MinusHandler());
		this.view.addPlusHanlder(new PlusHandler());
		this.view.addLoginHandler(new LoginHandler());
		this.view.addUserProfileHandler(new UserProfile());
		
		/* Set the text fields with the input provided by the user. */
		this.view.setCityField(this.fields.getCity());
		this.view.setCheckInDate(this.fields.getCheckIn());
		this.view.setCheckOutDate(this.fields.getCheckOut());
		this.view.setPersonCountText(String.valueOf(this.fields.getPersonCount()));
		
		if(Integer.parseInt(this.view.getPersonCount()) == 0)
			
			/* The minus button has to be disabled */
			this.view.disableMinusButton();
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class implements the EventHandler interface for the user profile button.
	 */
	private class UserProfile implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			if(LoginController.getInstance().isLogged()) {
				
				try {
					new UserProfileViewController(Main.getInstance().getUserProfileView(), BookHotelController.getInstance());
					Main.getInstance().changeToUserProfileView();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class implements the EventHandler interface providing the handle method
	 * for login button. 
	 */
	private class LoginHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			Stage stage = new Stage();
			try {
				LoginView window = new LoginView();
				new LoginViewController(window, LoginController.getInstance());
				window.start(stage);
				view.loggedView(LoginController.getInstance().getUsername());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

	/**
	 * 
	 * @author metal
	 *
	 * This class implements the EventHandler interface providing the handle method
	 * for minus button.
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
	 * This class implements the EventHandler interface providing the handle method
	 * for plus button.
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
	
	/**
	 *
	 * @author metal
	 *
	 * This class implements the EventHandler interface providing the handler method
	 * for search button.
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
					fields.setCity(view.getCityField()); 
					fields.setCheckIn(view.getCheckInDate());
					fields.setCheckOut(view.getCheckOutDate());
					fields.setPersonCount(Integer.parseInt(view.getPersonCount()));
				
					List<RentablePlace> rentablePlaces = model.retrieveRentablePlaces(fields);
					
					if( rentablePlaces.isEmpty() )
						
						/* The input provided by the user doesn't provide result */
						view.resultNotFound();
					
					else
						
						view.populateView(rentablePlaces, new MoreInformationHandler());
					
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
	 * This class implements the EventHandler interface providing the handle for
	 * the MoreinformationButton.
	 */
	private class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			/* Fill the bean with the data provided by the user. */
			fields.setCity(view.getCityField());
			fields.setCheckIn(view.getCheckInDate());
			fields.setCheckOut(view.getCheckOutDate());
			fields.setPersonCount(Integer.parseInt(view.getPersonCount()));
			
			try {
				
				/* Change the view to HotelView and initialize the new controller. */
				Main.getInstance().changeToHotelView();
				new HotelViewController(Main.getInstance().getHotelView(),
						model.getRentablePlace(((Control)event.getSource()).getId()), fields);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
