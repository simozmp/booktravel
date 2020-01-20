package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.Main;
import logic.bean.CityDateBean;
import logic.model.BookHotelController;
import logic.model.LoginController;
import logic.model.ManageHotelList;
import logic.view.LoginView;
import logic.view.MainMenuView;

/**
 * 
 * @author metal
 *
 * MVC controller for the view MainMenuView.
 */
public class MainMenuController extends MainViewController {
	
	/**
	 * Reference to the current view.
	 */
	private MainMenuView mainMenuView;
	
	/**
	 * Constructor of the class.
	 * It initialize the state of the controller.
	 * 
	 * @param view 		the view.
	 * @param model		the model.
	 */
	public MainMenuController(MainMenuView view, BookHotelController model) {
		
		super(view, model);
		
		this.mainMenuView = (MainMenuView) super.view;
		
		/* Add the handlers to buttons.  */
		this.mainMenuView.addSearchListener(new SearchHandler());
		this.mainMenuView.addMinusHanlder(new MinusHandler());
		this.mainMenuView.addPlusHanlder(new PlusHandler());
		this.mainMenuView.addLogInAsOwnerListener(new LogInAsOwnerHandler());
		
	
	}
	/**
	 * 
	 * @author metal
	 *
	 * This class provide the implementation of EventHandler interface
	 * for the searchHandler button.
	 */
	private class SearchHandler implements EventHandler<ActionEvent> {
		
		private boolean fieldsAreFilled() {			
			boolean fieldsAreFilled = !mainMenuView.getCityField().isEmpty() && mainMenuView.getCheckInDate() != null && 
					mainMenuView.getCheckOutDate() != null && Integer.parseInt(mainMenuView.getPersonCount()) != 0;
			
			return fieldsAreFilled;
		}
		
		private boolean checkInDateIsBeforeCheckOutDate() {
			boolean isBefore = mainMenuView.getCheckInDate().isBefore(mainMenuView.getCheckOutDate()) ||
					mainMenuView.getCheckInDate().equals(mainMenuView.getCheckOutDate());
			
			return isBefore;
		}
		
		private void checkEmptyFields() {
			if(mainMenuView.getCityField().isEmpty())
				mainMenuView.setVisibleErrCityField(true);
			
			if(mainMenuView.getCheckInDate() == null)
				mainMenuView.setVisibleErrCheckInField(true);
			
			if(mainMenuView.getCheckOutDate() == null)
				mainMenuView.setVisibleErrCheckOutField(true);
			
			if(Integer.parseInt(mainMenuView.getPersonCount()) == 0)
				mainMenuView.setVisibleErrPersonCount(true);
		}

		@Override
		public void handle(ActionEvent event) {
			
			/* Set invisible all errors. */
			mainMenuView.setVisibleErrCityField(false);
			mainMenuView.setVisibleErrCheckInField(false);
			mainMenuView.setVisibleErrCheckOutField(false);
			mainMenuView.setVisibleErrPersonCount(false);
			
			if( this.fieldsAreFilled() ) {
				
				/* The user filled all the fields. */
				if( this.checkInDateIsBeforeCheckOutDate() ) {
					
					/* The check-in date is before or equal to the check-out date.  */
					
					/* Fill the bean fields. */
					CityDateBean fields = new CityDateBean();
					fields.setCity(mainMenuView.getCityField());
					fields.setCheckIn(mainMenuView.getCheckInDate());
					fields.setCheckOut(mainMenuView.getCheckOutDate());
					fields.setPersonCount(Integer.parseInt(mainMenuView.getPersonCount()));
					
					try {
						
						/* Set the new controller and change the view. */
						Main.getInstance().changeToBookHotelListView();
						new BookHotelListViewController(Main.getInstance().getBookHotelListView(), model, fields);
						
					} catch (Exception e) {
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
				this.checkEmptyFields();
				
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
			
			int personCount = Integer.parseInt(mainMenuView.getPersonCount());
			personCount--;
			if(personCount == 0)
				mainMenuView.disableMinusButton();
			mainMenuView.setPersonCountText(String.valueOf(personCount));
			
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
			
			int personCount = Integer.parseInt(mainMenuView.getPersonCount());
			personCount++;
			mainMenuView.enableMinusButton();
			mainMenuView.setPersonCountText(String.valueOf(personCount));
			
		}
		
	}
	
	private class LogInAsOwnerHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {

			Stage stage = new Stage();
			try {
				LoginView window = new LoginView();
				new LoginViewOwnerController(window, LoginController.getInstance());
				window.start(stage);
				if(LoginController.getInstance().isLogged()) {
					Main.getInstance().changeToManageHotelListView();
					
					new ManageHotelListController(Main.getInstance().getManageHotelListView(), ManageHotelList.getInstance(), LoginController.getInstance().getUsername());

				}	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
	
		
		}

	}
}
