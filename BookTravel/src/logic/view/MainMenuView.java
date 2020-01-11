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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.mydatecell.MyCallback;

public class MainMenuView extends Application {
	
	private Button btnLogin = new Button("Login");
	private Button btnSignIn = new Button("Sign In");
	private Button btnLoginAsOwner = new Button("Login as Owner");
	
	private Text txtErrCity = new Text("You have to fill this field!");
	private Text txtErrCheckIn = new Text("You have to fill this field!");
	private Text txtErrCheckOut = new Text("You have to fill this field!");
	private Text txtErrPersonCount = new Text("You have select how much you are!");
	
	private TextField txtFieldCity= new TextField();
	private DatePicker dPickerCheckIn = new DatePicker();
	private DatePicker dPickerCheckOut = new DatePicker();
	private Button btnSearch = new Button("Search");
	private Button btnGetStarted = new Button("Get Started");	
	private Label lblPersonCount = new Label("0");
	private Button btnPlus = new Button("+");
	private Button btnMinus = new Button("-");
	private HBox hBoxLogin = new HBox(10);
	private Button btnUserProfile = new Button("User Profile");

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
		
		hBoxLogin.setAlignment(Pos.CENTER_RIGHT);
		hBoxLogin.getChildren().addAll(btnLogin, btnSignIn, btnLoginAsOwner);
	 
		HBox hBoxTop = new HBox();
		HBox.setHgrow(hBoxTitle, Priority.ALWAYS);
		hBoxTitle.setMaxWidth(Double.MAX_VALUE);
		hBoxTop.getChildren().addAll(hBoxTitle, hBoxLogin);
		
		HBox hBoxSearch = new HBox(20);
		hBoxSearch.setPadding(new Insets(20, 20, 20, 20));
		hBoxSearch.setPrefWidth(Double.MAX_VALUE);
		
		Label lblcity = new Label("Where do you want to go?");
		Label lblCheckIn = new Label("Enter Check-In");
		Label lblCheckOut = new Label("Enter Check-Out");
		Label lblPerson = new Label("Select how much people you are");
		
		txtFieldCity.setPromptText("e.g. Rome");		
		dPickerCheckIn.setPromptText("Pick a date");
		dPickerCheckIn.setDayCellFactory(MyCallback.getDayCellFactory());
		dPickerCheckIn.setEditable(false);
		
		dPickerCheckOut.setPromptText("Pick a date");
		dPickerCheckOut.setDayCellFactory(MyCallback.getDayCellFactory());
		dPickerCheckOut.setEditable(false);
		
		this.txtErrCity.setFill(Color.RED);
		this.txtErrCity.setVisible(false);
		this.txtErrCheckIn.setFill(Color.RED);
		this.txtErrCheckIn.setVisible(false);
		this.txtErrCheckOut.setFill(Color.RED);
		this.txtErrCheckOut.setVisible(false);
		this.txtErrPersonCount.setFill(Color.RED);
		this.txtErrPersonCount.setVisible(false);
	
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.add(lblcity, 0, 0);
		gridPane.add(txtFieldCity, 0, 1);
		gridPane.add(lblCheckIn, 1, 0);
		gridPane.add(dPickerCheckIn, 1, 1);
		gridPane.add(lblCheckOut, 2, 0);
		gridPane.add(dPickerCheckOut, 2, 1);
		gridPane.add(lblPerson, 3, 0);
		gridPane.add(this.txtErrCity, 0, 2);
		gridPane.add(this.txtErrCheckIn, 1, 2);
		gridPane.add(this.txtErrCheckOut, 2, 2);
		gridPane.add(this.txtErrPersonCount, 3, 2);
		
		HBox personCountHBox = new HBox(10);
		personCountHBox.setAlignment(Pos.CENTER);
		personCountHBox.getChildren().addAll(btnPlus, lblPersonCount, btnMinus);
		
		gridPane.add(personCountHBox, 3, 1);		
		
		this.disableMinusButton();
		this.btnSearch.setFont(Font.font(18));
		hBoxSearch.setAlignment(Pos.CENTER);
		btnSearch.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		hBoxSearch.getChildren().addAll(gridPane, btnSearch);
		
		VBox vBoxGetStarted = new VBox(10);
		vBoxGetStarted.setPadding(new Insets(20, 20, 20, 20));
		vBoxGetStarted.setAlignment(Pos.CENTER);
		
		Text txtGetStarted = new Text("Do you need a travel but you don't know where?"
				+ "\nDon't worry, we will find it for you!");
		txtGetStarted.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
		btnGetStarted.setFont(new Font(20));
		
		vBoxGetStarted.getChildren().addAll(txtGetStarted, btnGetStarted);
		
		VBox vBoxMain = new VBox(50);
		vBoxMain.setPadding(new Insets(20, 20, 20, 20));
		vBoxMain.getChildren().addAll(hBoxSearch, vBoxGetStarted);
		
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(vBoxMain);
		
		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public void addUserProfileHandler(EventHandler<ActionEvent> handler) {
		
		this.btnUserProfile.setOnAction(handler);
		
	}
	
	public void loggedView(String username) {
		
		this.hBoxLogin.getChildren().clear();
		this.hBoxLogin.getChildren().addAll(new Text(username), this.btnUserProfile);
		
	}
	
	public void setVisibleErrCityField(boolean value) {
		
		this.txtErrCity.setVisible(value);
		
	}
	
	public void setVisibleErrCheckInField(boolean value) {
		
		this.txtErrCheckIn.setVisible(value);
		
	}
	
	public void setVisibleErrCheckOutField(boolean value) {
		
		this.txtErrCheckOut.setVisible(value);
		
	}
	
	public void setVisibleErrPersonCount(boolean value) {
		
		this.txtErrPersonCount.setVisible(value);
		
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
	
	public void resetPersonCount() {
		
		this.lblPersonCount.setText("0");
		
	}
	
	public void disableMinusButton() {
		
		this.btnMinus.setDisable(true);
		
	}
	
	public void enableMinusButton() {
		
		this.btnMinus.setDisable(false);
		
	}
	
	public void addPlusHanlder(EventHandler<ActionEvent> addHandler) {
		
		this.btnPlus.setOnAction(addHandler);
		
	}
	
	public void addMinusHanlder(EventHandler<ActionEvent> minusHandler) {
		
		this.btnMinus.setOnAction(minusHandler);
		
	}
	
	public void setPersonCountText(String value) { this.lblPersonCount.setText(value); }
	
	public String getPersonCount() { return this.lblPersonCount.getText(); }
	
	public String getCityField() { return this.txtFieldCity.getText();	}
	
	public LocalDate getCheckInDate() { return this.dPickerCheckIn.getValue(); }
	
	public LocalDate getCheckOutDate() { return this.dPickerCheckOut.getValue(); }
	
	/**
	 * @author Adri
	 * @param signInAsOwnerHandler
	 */
	
	public void addLogInAsOwnerListener(EventHandler<ActionEvent> logInAsOwnerHandler) {
		
		this.btnSignIn.setOnAction(logInAsOwnerHandler);
	}

}
