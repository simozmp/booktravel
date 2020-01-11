package logic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.Main;
import logic.bean.LoginBean;
import logic.model.BookHotelController;
import logic.model.LoginController;
import logic.view.UserProfileView;

/**
 * 
 * @author metal
 * 
 * Controller MVC of the UserProfileView.
 */
public class UserProfileViewController {
	
	/**
	 * Reference of the view.
	 */
	private UserProfileView view;
	
	/**
	 * Reference of the model.
	 */
	private BookHotelController model;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param view		the view.
	 * @param model		the model.
	 * @param loginBean	bean that contain input of the user.
	 */
	public UserProfileViewController(UserProfileView view, BookHotelController model) {
		
		this.view = view;
		this.model = model;
		
		if(LoginController.getInstance().isLogged())
			view.setUsername(LoginController.getInstance().getUsername());
		
		LoginBean loginBean = new LoginBean(LoginController.getInstance().getUsername(), LoginController.getInstance().getPassword());
		this.view.setBookings(this.model.retrieveBookingOfAnUser(loginBean ));
		
		this.view.addMainMenuHandler(new MainMenuHandler());
		
	}
	
	/**
	 * 
	 * @author metal
	 *
	 * This class implements the EventHandler interface for the MainMenu Button.
	 */
	private class MainMenuHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			try {
				Main.getInstance().changeToMainMenuView();
				new MainMenuController(Main.getInstance().getMainMenuView(), BookHotelController.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}

}
