package logic.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import logic.Main;
import logic.bean.HotelBean;

import logic.model.BookHotelController;

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

	public ManageHotelListController(ManageHotelListView view, ManageHotelList model, String owner) {

		this.view = view;
		this.model = model;
		this.owner = owner;

		this.view.addExitHandler(new ExitHandler());
		this.view.addCreateHotelHandler(new CreateHotelHandler());
		this.view.addProfileHandelr(new ProfileHandelr());
		// this.view.add
		/**
		 * if ( this.model.retrieveRentablePlaces(this.fields).isEmpty() )
		 * 
		 * /* The input of the doesn't produce result. this.view.resultNotFound();
		 * 
		 * else
		 **/
		/* Set the data found to the view. */
		this.view.populateView(this.model.retrieveHotelByOwner(this.owner), new MoreInformationHandler(),
				new DeleteHandler());

	}

	public class ExitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			try {
				Main.getInstance().changeToMainMenuView();
				new MainMenuController(Main.getInstance().getMainMenuView(), BookHotelController.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	public class ProfileHandelr implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {

			try {
				new UserProfileViewController(Main.getInstance().getUserProfileView(),
						BookHotelController.getInstance());
				Main.getInstance().changeToUserProfileView();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

	private class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			try {

			
				Main.getInstance().changeToManageRoomListView();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	

	private class CreateHotelHandler implements EventHandler<ActionEvent>{
		HotelBean bean = new HotelBean("hotel 1", "indirizzo 1", "Roma", "owner", owner, 4);
		@Override
		public void handle(ActionEvent event) {
			ManageHotelList.createHotel(bean);
			
			 new ManageHotelListController(view, model, owner);

			
		}
	}
	
	private class DeleteHandler implements EventHandler<ActionEvent>{
	
		@Override
		public void handle(ActionEvent event) {
			 throw new UnsupportedOperationException();
		}
		
	}
}


