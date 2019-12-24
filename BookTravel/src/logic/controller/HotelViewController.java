package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;
import logic.model.RentablePlace;
import logic.model.RentablePlacesCatalog;
import logic.view.HotelView;

public class HotelViewController {
	
	private HotelView view;
	private RentablePlace model;
	
	public HotelViewController(HotelView view, RentablePlace model) {
		
		this.view = view;
		this.model = model;
		
		this.view.setName(this.model.getName());
		this.view.setAddress(this.model.getAddress());
		this.view.setInformation("Bell'Hotel proprio!");
		
		this.view.addBackHandler(new BackHandler());
		
	}
	
	private class BackHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			try {
				Main.getInstance().changeToBookHotelListView();
				BookHotelListViewController controller = new BookHotelListViewController
						(Main.getInstance().getBookHotelListView(),	RentablePlacesCatalog.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
