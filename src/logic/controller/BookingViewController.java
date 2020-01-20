package logic.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Main;
import logic.bean.CityDateBean;
import logic.bean.RoomBean;
import logic.model.BookHotelController;
import logic.model.LoginController;
import logic.model.Person;
import logic.model.RentablePlace;
import logic.view.BookingView;

/**
 * 
 * @author metal
 *
 * MVC controller for the view BookingView.
 */
public class BookingViewController {
	
	/**
	 * Reference to the view.
	 */
	private BookingView view;
	
	/**
	 * Reference to the model.
	 */
	private RentablePlace model;
	
	/**
	 * Bean that contains input of the user.
	 */
	private CityDateBean fields;
	
	/**
	 * Bean that contain input of the user.
	 */
	private List<RoomBean> roomBeans;
	
	/**
	 * Constructor of the class.
	 * It initialize the state of the controller and set the data to the view.
	 * 
	 * @param view			the view.
	 * @param model			the model.
	 * @param fields		bean that contains input of the user.
	 * @param roomBeans		bean that contains input of the user.
	 */
	public BookingViewController(BookingView view, RentablePlace model, CityDateBean fields, List<RoomBean> roomBeans) {
		
		/* Set the new view and the new model. */
		this.view = view;
		this.model = model;
		this.fields = fields;
		this.roomBeans = new ArrayList<RoomBean>(roomBeans);
		
		if(LoginController.getInstance().isLogged())
			this.view.setUsername(LoginController.getInstance().getUsername());
		
		/* Initialize the form needed for the user input. */
		this.view.populateView(fields.getPersonCount());
		
		/* Set the data with the input of the user. */
		this.view.populateViewLeft(this.roomBeans);
		
		/* Add the handlers to buttons. */
		this.view.addBackHandler(new BackHandler());
		
		this.view.addConfirmHandler(new ConfirmHandler());
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class provides the implementation of the EventHandler interface for the confirm button.
	 */
	private class ConfirmHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			List<BookingView.PersonForm> peopleForm = view.getPeopleList();		/* The input of the user. */
			
			List<Person> people = new ArrayList<Person>();
			
			boolean isEmpty = false;	/* Value to keep track if one or more fields are empty. */
			
			for( BookingView.PersonForm personForm : peopleForm ) {
				
				personForm.setErrorVisible(false);
				
				if( !personForm.getFiscalCode().equals("") && !personForm.getName().equals("") && !personForm.getLastname().equals("") ) {
					
					/* All fields are been filled. */
					people.add( new Person( personForm.getFiscalCode(), personForm.getName(),
							personForm.getLastname() ) );		/* Extract the input and add it to the bean. */
					
				} else {
					
					/* One or more fields are empty. */
					isEmpty = true;
					personForm.setErrorVisible(true);
					
				}
				
				
			}
			
			if(!isEmpty) {
				
				BookHotelController.getInstance().createBooking(fields, people, roomBeans, model);	/* Tell to the use case controller to create a new booking. */
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Booking information.");
				alert.setHeaderText(null);
				alert.setContentText("New Booking Confirmed.");
				
				alert.showAndWait();
				
				try {
					
					/* Change view and set new controller. */
					Main.getInstance().changeToBookHotelListView();
					new BookHotelListViewController(Main.getInstance().getBookHotelListView(),	BookHotelController.getInstance(), fields);
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
	 * This class provides the implementation of the EventHandler interface for the back button.
	 */
	private class BackHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			try {
				
				/* Change the view and set new controller. */
				Main.getInstance().changeToHotelView();
				new HotelViewController(Main.getInstance().getHotelView(), model, fields);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
