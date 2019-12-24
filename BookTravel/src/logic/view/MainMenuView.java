package logic.view;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuView extends Application {
	
	private Button btnLogin = new Button("Login");
	private Button btnSignIn = new Button("Sign In");
	private TextField txtFieldCity= new TextField();
	private DatePicker dPickerCheckIn = new DatePicker();
	private DatePicker dPickerCheckOut = new DatePicker();
	private Button btnSearch = new Button("Search");
	private Button btnGetStarted = new Button("Get Started");
	

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		primaryStage.setTitle("TravelBook");
		
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
		
		HBox hBoxSearch = new HBox(5);
		hBoxSearch.setPadding(new Insets(20, 20, 20, 20));
		hBoxSearch.setPrefWidth(Double.MAX_VALUE);
		
		txtFieldCity.setPromptText("Where do you want to go?");
		
		dPickerCheckIn.setPromptText("Enter Check-In");
		
		dPickerCheckOut.setPromptText("Enter Check-Out");
		
		HBox.setHgrow(txtFieldCity, Priority.ALWAYS);
		txtFieldCity.setMaxWidth(Double.MAX_VALUE);
		hBoxSearch.getChildren().addAll(txtFieldCity, dPickerCheckIn, dPickerCheckOut, btnSearch);
		
		VBox vBoxGetStarted = new VBox(10);
		vBoxGetStarted.setPadding(new Insets(20, 20, 20, 20));
		vBoxGetStarted.setAlignment(Pos.CENTER);
		
		Text txtGetStarted = new Text("Do you need a travel but you don't know where?"
				+ "\nDon't worry, we will find it for you!");
		txtGetStarted.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
		btnGetStarted.setFont(new Font(20));
		
		vBoxGetStarted.getChildren().addAll(txtGetStarted, btnGetStarted);
		
		VBox vBoxMain = new VBox(20);
		vBoxMain.setPadding(new Insets(20, 20, 20, 20));
		vBoxMain.getChildren().addAll(hBoxSearch, vBoxGetStarted);
		
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(vBoxMain);
		
		Scene scene = new Scene(borderPane, 800, 400);
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
	
	public void addGetStartedListener(EventHandler<ActionEvent> getStartedHandler) {
		
		this.btnGetStarted.setOnAction(getStartedHandler);
		
	}
	
	public String getCityField() { return this.txtFieldCity.getText();	}
	
	public LocalDate getCheckInDate() { return this.dPickerCheckIn.getValue(); }
	
	public LocalDate getCheckOutDate() { return this.dPickerCheckOut.getValue(); }

}
