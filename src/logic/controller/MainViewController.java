package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import logic.Main;
import logic.model.BookHotelController;
import logic.model.LoginController;
import logic.view.LoginView;
import logic.view.MainView;

/**
 * 
 * @author metal
 *
 * MVC Controller for the MainView.
 */
public abstract class MainViewController {
	
	/**
	 * Reference to the view
	 */
	protected MainView view;
	
	/**
	 * Reference to the model
	 */
	protected BookHotelController model;
	
	/**
	 * Constructor of the class, it mantains the link with the view and the model.
	 * 
	 * @param view	the view.
	 * @param model	the model.
	 */
	public MainViewController(MainView view, BookHotelController model) {
		
		this.view = view;
		this.model = model;
		
		if(LoginController.getInstance().isLogged())
			this.view.loggedView(LoginController.getInstance().getUsername());
		
		this.view.addLoginListener(new LoginHandler());
		this.view.addUserProfileHandler(new UserProfileHandler());	
		
	}
	
	/**
	 * Constructor, it is implemented by HotelViewController.
	 * 
	 * @param view
	 */
	public MainViewController(MainView view) {
		
		this.view = view;
		
		if(LoginController.getInstance().isLogged())
			this.view.loggedView(LoginController.getInstance().getUsername());
		
		this.view.addLoginListener(new LoginHandler());
		this.view.addUserProfileHandler(new UserProfileHandler());
		
	}
	
	/**
	 * 
	 * @author metal
	 * 
	 * This class implements the EventHandler interface for the login button.
	 */
	private class LoginHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			Stage stage = new Stage();
			try {
				LoginView window = new LoginView();
				new LoginViewController(window, LoginController.getInstance());
				window.start(stage);
				if(LoginController.getInstance().isLogged())
					view.loggedView(LoginController.getInstance().getUsername());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author metal
	 * 
	 * This class provide the implementation of the EventHandler interface for the user profile button.
	*/
	private class UserProfileHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			if(LoginController.getInstance().isLogged()) {
				
				try {
					new UserProfileViewController(Main.getInstance().getUserProfileView(), BookHotelController.getInstance());
					Main.getInstance().changeToUserProfileView();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}

}
