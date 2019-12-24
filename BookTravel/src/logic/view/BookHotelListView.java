package logic.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.model.RentablePlace;

public class BookHotelListView extends Application {
	
	private ScrollPane scrollPane = new ScrollPane();
	
//	private ListView<HBoxCell> listview = new ListView<HBoxCell>();
	
	private Button btnLogin = new Button("Login");
	private Button btnSignIn = new Button("Sign In");
	private TextField txtFieldCity= new TextField();
	private DatePicker dPickerCheckIn = new DatePicker();
	private DatePicker dPickerCheckOut = new DatePicker();
	private Button btnSearch = new Button("Search");

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));
		
		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);
		
		Text title = new Text("TravelBook");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		hBoxTitle.getChildren().add(title);
		
		HBox hBoxLogin = new HBox(10);
		hBoxLogin.setAlignment(Pos.CENTER_RIGHT);
		hBoxLogin.getChildren().addAll(btnLogin, btnSignIn);
		
		HBox hBoxTop = new HBox();
		HBox.setHgrow(hBoxTitle, Priority.ALWAYS);
		hBoxTitle.setMaxWidth(Double.MAX_VALUE);
		hBoxTop.getChildren().addAll(hBoxTitle, hBoxLogin);
		
		txtFieldCity.setPromptText("Where do you want to go?");
		
		dPickerCheckIn.setPromptText("Enter Check-In");
		
		dPickerCheckOut.setPromptText("Enter Check-Out");
		
		VBox vBoxLeft = new VBox(10);
		vBoxLeft.setPadding(new Insets(50, 20, 20, 0));
		vBoxLeft.getChildren().addAll(txtFieldCity, dPickerCheckIn, dPickerCheckOut, btnSearch);
		
		borderPane.setTop(hBoxTop);
		borderPane.setLeft(vBoxLeft);
		borderPane.setCenter(this.scrollPane);
//		borderPane.setCenter(this.listview);
		
		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public void addLoginListener(EventHandler<ActionEvent> loginHandler) {
		
		this.btnLogin.setOnAction(loginHandler);
		
	}
	
	public void addSignInListener(EventHandler<ActionEvent> signInHandler) {
		
		this.btnSignIn.setOnAction(signInHandler);
		
	}
	
	public void addSearchListener(EventHandler<ActionEvent> searchHandler) {
		
		this.btnSearch.setOnAction(searchHandler);
		
	}
	
	public String getCityField() { return this.txtFieldCity.getText();	}
	
	public LocalDate getCheckInDate() { return this.dPickerCheckIn.getValue(); }
	
	public LocalDate getCheckOutDate() { return this.dPickerCheckOut.getValue(); }
	
	public void populateView (List<RentablePlace> rentablePlaces, EventHandler<ActionEvent> buttonHandler) {
		
		VBox vBox = new VBox(10);		
		List<HBoxCell> list = new ArrayList<HBoxCell>();
		
		for(int i = 0; i < rentablePlaces.size(); i++)
			list.add(new HBoxCell(rentablePlaces.get(i).getName(), rentablePlaces.get(i).getAddress(), buttonHandler));
		
		vBox.getChildren().addAll(list);
		vBox.setMaxWidth(Double.MAX_VALUE);
		
		this.scrollPane.setContent(vBox);
		this.scrollPane.setFitToWidth(true);
		
	}
	
//	public void populateListView (List<RentablePlace> rentablePlaces) {
//		
//		List<HBoxCell> list = new ArrayList<HBoxCell>();
//		
//		for(int i = 0; i < rentablePlaces.size(); i++)
//			list.add(new HBoxCell(rentablePlaces.get(i).getName(), rentablePlaces.get(i).getAddress()));
//		
//		ObservableList<HBoxCell> observableList = FXCollections.observableList(list);
//		
//		this.listview.setItems(observableList);
//		
//	}
	
	public class HBoxCell extends HBox {
		
		private Label name = new Label();
		private Label address = new Label();
		private Button button = new Button();
		
		public HBoxCell(String name, String address, EventHandler<ActionEvent> buttonHandler) {
			
			super();
			
			this.name.setText(name);
			this.name.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(this.name, Priority.ALWAYS);
			
			this.address.setText(address);
			this.address.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(this.address, Priority.ALWAYS);
			
			this.button.setText("Detailed Information");
			this.button.setId(name);
			this.button.setOnAction(buttonHandler);
			
			this.getChildren().addAll(this.name, this.address, this.button);
			
		}
		
		public String getName() { return this.name.getText(); }
		
		public String getAddress() { return this.address.getText(); }
		
	}

}
