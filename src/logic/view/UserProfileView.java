package logic.view;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.bean.BookingBean;
import logic.model.Person;

public class UserProfileView extends Application {
	
	private ScrollPane scrollPane = new ScrollPane();
	
	private Label lblUsername = new Label("Username");
	
	private Button btnMainMenu = new Button("Main Menu");

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));
		
		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);	
		
		Text title = new Text("TravelBook");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		
		Region regionCenterLeft = new Region();
		HBox.setHgrow(regionCenterLeft, Priority.ALWAYS);
		Region regionCenterRight = new Region();
		HBox.setHgrow(regionCenterRight, Priority.ALWAYS);
		hBoxTitle.getChildren().addAll(title, regionCenterLeft, btnMainMenu, regionCenterRight, lblUsername);
		
		borderPane.setTop(hBoxTitle);
		borderPane.setCenter(this.scrollPane);
		
		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public void addMainMenuHandler(EventHandler<ActionEvent> handler) {
		
		btnMainMenu.setOnAction(handler);
		
	}
	
	public void setUsername(String username) {
		
		lblUsername.setText(username);
		
	}
	
	public void setBookings(List<BookingBean> bookingsList) {

		VBox vBox = new VBox(10);
		
		for(BookingBean bean : bookingsList) {
			
			vBox.getChildren().add( new BookingBox(bean.getHotel(), 
					bean.getCheckIn().toString(), bean.getCheckOut().toString(), bean.getPeople()) );
			
		}
		
		this.scrollPane.setContent(vBox);
		
	}
	
	private class BookingBox extends HBox {
		
		private Label lblHotel = new Label();
		
		private Label lblCheckIn = new Label();
		
		private Label lblCheckOut = new Label();
		
		public BookingBox(String hotel, String checkIn, String checkOut, List<Person> people) {
			
			super(10);
			
			GridPane gridPane = new GridPane();
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			
			this.lblHotel.setText(hotel);
			this.lblCheckIn.setText(checkIn);
			this.lblCheckOut.setText(checkOut);
			
			gridPane.add(new Label("Hotel Booked"), 0, 0);
			gridPane.add(lblHotel, 0, 1);
			gridPane.add(new Label("Check-In"), 1, 0);
			gridPane.add(lblCheckIn, 1, 1);
			gridPane.add(new Label("Check-Out"), 2, 0);
			gridPane.add(lblCheckOut, 2, 1);
			
			GridPane peopleGrid = new GridPane();
			peopleGrid.setHgap(5);
			peopleGrid.setVgap(5);
			
			peopleGrid.add(new Label("First Name"), 0, 0);
			peopleGrid.add(new Label("Last Name"), 1, 0);
			
			int row = 1;
			
			for( Person person : people ) {
				peopleGrid.add(new Label(person.getName()), 0, row);
				peopleGrid.add(new Label(person.getLastname()), 1, row);
				row++;
			}
			
			this.getChildren().addAll(gridPane, peopleGrid);
			
		}
		
	}

}
