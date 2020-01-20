package logic.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Alert.AlertType;
import logic.Main;
import logic.bean.CityDateBean;
import logic.bean.HotelBean;
import logic.model.BookHotelController;
import logic.view.BookHotelListView;

/**
 * 
 * @author metal
 *
 * MVC controller of the view BookHotelListView.
 */
public class BookHotelListViewController extends MainViewController {
	
	/**
	 * Reference to the view.
	 */
	private BookHotelListView view;
	
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
		super(view, model);
		
		this.view = (BookHotelListView) super.view;
		this.fields = fields;
		
		/* Add handlers to buttons. */
		this.view.addSearchListener(new SearchHandler());
		this.view.addMinusHanlder(new MinusHandler());
		this.view.addPlusHanlder(new PlusHandler());
		
		/* Set the text fields with the input provided by the user. */
		this.view.setCityField(this.fields.getCity());
		this.view.setCheckInDate(this.fields.getCheckIn());
		this.view.setCheckOutDate(this.fields.getCheckOut());
		this.view.setPersonCountText(String.valueOf(this.fields.getPersonCount()));
		
		if(Integer.parseInt(this.view.getPersonCount()) == 0)
			
			/* The minus button has to be disabled */
			this.view.disableMinusButton();
		
		List<HotelBean> hotels = new ArrayList<HotelBean>();
		hotels = this.model.retrieveHotelByCity(this.fields.getCity());
		
		if ( hotels.isEmpty() )
			
			/* The input of the doesn't produce result. */
			this.view.resultNotFound();
		
		else
			
			/* Set the data found to the view. */
			this.view.populateView(hotels, new MoreInformationHandler());
		
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
				
					List<HotelBean> rentablePlaces = model.retrieveHotelByCity(fields.getCity());
					
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
	public class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			/* Fill the bean with the data provided by the user. */
			fields.setCity(view.getCityField());
			fields.setCheckIn(view.getCheckInDate());
			fields.setCheckOut(view.getCheckOutDate());
			fields.setPersonCount(Integer.parseInt(view.getPersonCount()));
			
			int id = Integer.parseInt( ((Control)event.getSource()).getId() );	// The id of the hotel selected.
			
			try {
				
				/* Change the view to HotelView and initialize the new controller. */
				Main.getInstance().changeToHotelView();
				new HotelViewController(Main.getInstance().getHotelView(), model.getRentablePlace(id), fields);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
