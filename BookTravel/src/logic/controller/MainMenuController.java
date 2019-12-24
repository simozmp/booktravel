package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;
import logic.model.RentablePlacesCatalog;
import logic.view.MainMenuView;

public class MainMenuController {
	
	private MainMenuView view;
	private RentablePlacesCatalog model;
	
	public MainMenuController(MainMenuView view, RentablePlacesCatalog model) {
		
		this.view = view;
		this.model = model;
		
		this.view.addSearchListener(new SearchHandler());
	}
	
	private class SearchHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			try {
				Main.getInstance().changeToBookHotelListView();
				BookHotelListViewController controller = new BookHotelListViewController(Main.getInstance().getBookHotelListView(), model);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
