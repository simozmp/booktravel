package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;

import logic.model.LoginController;
import logic.model.ManageHotelList;
import logic.model.ManageRoomList;

import logic.view.ManageRoomListView;

public class ManageRoomListController {

	private ManageRoomList model;

	private ManageRoomListView view;

	public ManageRoomListController(ManageRoomListView view, ManageRoomList model) {

		this.view = view;
		this.model = model;

		this.view.addBackHandler(new BackHandler());
		this.view.populateView(this.model.retrieveRooms(), new MoreInformationHandler(), new DeleteHandler());

	}

	public class BackHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			try {
				Main.getInstance().changeToManageHotelListView();

				new ManageHotelListController(Main.getInstance().getManageHotelListView(),
						ManageHotelList.getInstance(), LoginController.getInstance().getUsername());

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

	private class DeleteHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			throw new UnsupportedOperationException();



		}
	}

}