package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import logic.Main;
import logic.model.RentablePlacesCatalog;
import logic.view.BookHotelListView;

public class BookHotelListViewController {
	
	private BookHotelListView view;
	
	private RentablePlacesCatalog model;
	
	public BookHotelListViewController(BookHotelListView view, RentablePlacesCatalog model) {
		
		this.view = view;
		this.model = model;
		
		this.view.populateView(this.model.retrieveRentablePlaces(), new MoreInformationHandler());
		
	}
	
	private class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			try {
				Main.getInstance().changeToHotelView();
				HotelViewController controller = new HotelViewController
						(Main.getInstance().getHotelView(), model.getRentablePlace(((Control)event.getSource()).getId()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
