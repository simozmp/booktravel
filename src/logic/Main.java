package logic;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.controller.MainMenuController;
import logic.model.BookHotelController;
import logic.view.BookHotelListView;
import logic.view.BookingView;
import logic.view.HotelView;
import logic.view.MainMenuView;
import logic.view.ManageHotelListView;
import logic.view.ManageRoomListView;
import logic.view.UserProfileView;

/**
 * 
 * @author metal
 *
 * Main class of the program. The application starts from here.
 * This class manage the switching between views.
 */
public class Main extends Application{
	
	/**
	 * Reference to the main menu.
	 */
	private MainMenuView mainMenuView;
	
	/**
	 * Reference to the user profile view.
	 */
	private UserProfileView userProfileView;
	
	/**
	 * Reference to the list view.
	 */
	private BookHotelListView bookHotelListView;
	
	/**
	 * Reference to hotel view.
	 */
	private HotelView hotelView;
	
	/**
	 * Reference to booking view.
	 */
	private BookingView bookingView;

	private ManageHotelListView manageHotelListView;
	
	private ManageRoomListView manageRoomListView;
	
	/**
	 * The primary stage of the javafx application.
	 */
	private static Stage primaryStage;
	
	/**
	 * Reference to instance of this class.
	 */
	private static Main instance = null;
	
	/**
	 * Constructor of this class. It initialize every view.
	 */
	public Main() {
		
		this.mainMenuView = new MainMenuView();
		this.bookHotelListView = new BookHotelListView();
		this.hotelView = new HotelView();
		this.bookingView = new BookingView();
		this.userProfileView = new UserProfileView();
		this.manageHotelListView = new ManageHotelListView();
		this.manageRoomListView = new ManageRoomListView();
	}
	
	/**
	 * The application starts from here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		launch(args);

	}

	/**
	 * start methods. It call the start of the main menu and creates the controller of the main menu.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		Main.primaryStage = primaryStage;
		mainMenuView.start(Main.primaryStage);
		
		new MainMenuController(this.mainMenuView, BookHotelController.getInstance());
		
	}
	
	/**
	 * Change the view to user profile view.
	 * 
	 * @throws Exception
	 */
	public void changeToUserProfileView() throws Exception {
		
		this.userProfileView.start(primaryStage);
		
	}
	
	/**
	 * Return the User profile view.
	 * 
	 * @return user profile view.
	 */
	public UserProfileView getUserProfileView() {
		
		return this.userProfileView;
		
	}
	
	public void changeToMainMenuView() throws Exception {
		
		this.mainMenuView.start(primaryStage);
		
	}
	
	/**
	 * Change the view to BookHotelListView.
	 * 
	 * @throws Exception
	 */
	public void changeToBookHotelListView() throws Exception {
		
		this.bookHotelListView.start(primaryStage);
		
	}
	
	/**
	 * Change the view to HotelView.
	 * 
	 * @throws Exception
	 */
	public void changeToHotelView() throws Exception {
		
		this.hotelView.start(primaryStage);
		
	}
	
	/**
	 * Change the view to BookingView.
	 * 
	 * @throws Exception
	 */
	public void changeToBookingView() throws Exception {
		
		this.bookingView.start(primaryStage);
		
	}
	
	/**
	 * Get Instance method.
	 * 
	 * @return the only instance of this class.
	 */
	public synchronized static Main getInstance() {
		
		if(Main.instance == null) 
			
			Main.instance = new Main();
		
		return Main.instance;
		
	}
	
	/**
	 * Get method.
	 * 
	 * @return MainMenuView.
	 */
	public MainMenuView getMainMenuView() { return this.mainMenuView; }
	
	/**
	 * Get method.
	 * 
	 * @return	BookHotelListView.
	 */
	public BookHotelListView getBookHotelListView() { return this.bookHotelListView; }
	
	/**
	 * Get method.
	 * 
	 * @return HotelView.
	 */
	public HotelView getHotelView() { return this.hotelView; }
	
	/**
	 * Get method.
	 * 
	 * @return BookingView.
	 */
	public BookingView getBookingView() { return this.bookingView; }
	
	
	
	/**
	 * @author Adri
	 * 
	 * @throws Exception
	 */
	
	public void changeToManageHotelListView() throws Exception {
		this.manageHotelListView.start(primaryStage);
	
		
	}
	
	public ManageHotelListView getManageHotelListView() {return this.manageHotelListView;}
	
	
	public void changeToManageRoomListView() throws Exception {
		this.manageRoomListView.start(primaryStage);
	
		
	}
	
	public ManageRoomListView getManageRoomListView() {return this.manageRoomListView;}
	
}
