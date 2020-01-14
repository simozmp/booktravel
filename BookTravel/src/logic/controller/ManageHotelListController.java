package logic.controller;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Alert.AlertType;
import logic.Main;
import logic.controller.BookHotelListViewController.MoreInformationHandler;
import logic.model.ManageHotelList;
import logic.view.ManageHotelListView;

/**
 * 
 * @author Adri
 *
 */

public class ManageHotelListController {
	
	private ManageHotelListView view;
	
	private ManageHotelList model;

	private String owner;
	
	
	public ManageHotelListController(ManageHotelListView view, ManageHotelList model, String owner ) {
		
		this.view = view;
		this.model = model;
		this.owner = owner;
		
		/**
		if ( this.model.retrieveRentablePlaces(this.fields).isEmpty() )
			
			/* The input of the doesn't produce result. 
			this.view.resultNotFound();
		
		else
			**/
			/* Set the data found to the view. */
			this.view.populateView(this.model.retrieveRentablePlaces(this.owner), new MoreInformationHandler(), new DeleteHandler());
		
	}
	
	private class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
		
	
			
			try {
				
				/* Change the view to HotelView and initialize the new controller. */
				Main.getInstance().changeToHotelView();
			//	new HotelViewController(Main.getInstance().getHotelView(),
				//		model.getRentablePlace(((Control)event.getSource()).getId()), fields);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
	
	private class DeleteHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}	

}