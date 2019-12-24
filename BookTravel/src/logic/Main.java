package logic;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.controller.MainMenuController;
import logic.model.RentablePlacesCatalog;
import logic.view.BookHotelListView;
import logic.view.HotelView;
import logic.view.MainMenuView;

public class Main extends Application{
	
	private MainMenuView mainMenuView;
	private BookHotelListView bookHotelListView;
	private HotelView hotelView;
	private static Stage primaryStage;
	
	private static Main instance = null;
	
	public Main() {
		
		this.mainMenuView = new MainMenuView();
		this.bookHotelListView = new BookHotelListView();
		this.hotelView = new HotelView();
		
	}

	public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Main.primaryStage = primaryStage;
		mainMenuView.start(Main.primaryStage);
		
		MainMenuController controller = new MainMenuController(this.mainMenuView, RentablePlacesCatalog.getInstance());
		
	}
	
	public void changeToBookHotelListView() throws Exception {
		
		this.bookHotelListView.start(primaryStage);
		
	}
	
	public void changeToHotelView( ) throws Exception {
		
		this.hotelView.start(primaryStage);
		
	}
	
	public synchronized static Main getInstance() {
		
		if(Main.instance == null) 
			
			Main.instance = new Main();
		
		return Main.instance;
		
	}
	
	public BookHotelListView getBookHotelListView() { return this.bookHotelListView; }
	
	public HotelView getHotelView() { return this.hotelView; }
	
}
