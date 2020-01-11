package logic.view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.bean.RoomBean;

public class HotelView extends Application {
	
	private Button btnLogin = new Button("Login");
	private Button btnSignIn = new Button("Sign In");
	private Button btnBack = new Button("Back");
	private Button btnBook = new Button("Book");
	private Label name = new Label();
	private Label address = new Label();
	private Label information = new Label();
	private VBox vBoxLeft;
	private List<RoomSelector> roomSelectors;
	private Text txtErr = new Text("You have to select beds that \ncan contain how much people you are.");
	private Button btnUserProfile = new Button("User Profile");
	private HBox hBoxLogin = new HBox(10);

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));
		
		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);
		
		Text title = new Text("TravelBook");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		hBoxTitle.getChildren().add(title);
		
		hBoxLogin.setAlignment(Pos.CENTER_RIGHT);
		hBoxLogin.getChildren().addAll(btnLogin, btnSignIn);
		
		HBox hBoxTop = new HBox();
		HBox.setHgrow(hBoxTitle, Priority.ALWAYS);
		hBoxTitle.setMaxWidth(Double.MAX_VALUE);
		hBoxTop.getChildren().addAll(hBoxTitle, hBoxLogin);
		
		HBox hBoxCenter = new HBox(10);
		hBoxCenter.setPadding(new Insets(0, 20, 20, 20));
		this.name.setFont(Font.font(20));
		this.address.setFont(Font.font(20));
		this.information.setFont(Font.font(20));
		hBoxCenter.getChildren().addAll(this.name, this.address, this.information);
		
		this.vBoxLeft = new VBox(10);
		this.txtErr.setFill(Color.RED);
		this.txtErr.setVisible(false);
		vBoxLeft.getChildren().addAll(this.btnBack, this.txtErr);
		
		HBox hBoxBottom = new HBox(10);
		hBoxBottom.setAlignment(Pos.CENTER_RIGHT);
		hBoxBottom.getChildren().add(this.btnBook);
		
		borderPane.setLeft(vBoxLeft);
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(hBoxCenter);
		borderPane.setBottom(hBoxBottom);
		
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

	
	public void addLoginHandler(EventHandler<ActionEvent> loginHandler) {
		
		this.btnLogin.setOnAction(loginHandler);
		
	}
	
	public void addBackHandler(EventHandler<ActionEvent> backHandler) {
		
		this.btnBack.setOnAction(backHandler);
		
	}
	
	public void addBookHandler(EventHandler<ActionEvent> bookHandler) {
		
		this.btnBook.setOnAction(bookHandler);
		
	}
	
	public void setErrVisible(boolean value) {
		
		this.txtErr.setVisible(value);
		
	}
	
	public void createRoomSelector(List<RoomBean> roomBeans,
			EventHandler<ActionEvent> plusHandler, EventHandler<ActionEvent> minusHandler) {
		
		this.roomSelectors = new ArrayList<RoomSelector>();
		
		for( int i = 0; i < roomBeans.size(); i++ ) {
			
			RoomBean roomBean = roomBeans.get(i);
			
			this.roomSelectors.add( new RoomSelector( String.valueOf(roomBean.getBeds()), 
					String.valueOf(roomBean.getAvailability()), i, plusHandler, minusHandler ) );
			
		}
		
		this.vBoxLeft.getChildren().addAll(this.roomSelectors);
		
	}
	
	public RoomSelector getRoomSelector(int index) { return this.roomSelectors.get(index); }
	
	public List<RoomSelector> getAllRoomSelectors() { return this.roomSelectors; }
	
	public void setName(String name) { this.name.setText(name); }
	
	public void setAddress(String address) { this.address.setText(address); }
	
	public void setInformation(String information) { this.information.setText(information); }
	
	public class RoomSelector extends HBox {
		
		private Label lblBeds = new Label();
		
		private Label lblAvailability = new Label();
		
		private Button btnPlus = new Button("+");
		
		private Label lblRoomChoise = new Label();
		
		private Button btnMinus = new Button("-");
		
		public RoomSelector(String beds, String availability, int index,
				EventHandler<ActionEvent> plusHandler, EventHandler<ActionEvent> minusHandler) {
			
			this.setSpacing(10);
			
			this.lblBeds.setText(beds);
			
			this.lblAvailability.setText(availability);
			
			this.lblRoomChoise.setText("0");
			
			Label lblStringBeds = new Label("#");
			
			Label lblStringAvailability = new Label("Availability :");
			
			this.getChildren().addAll(lblStringBeds, this.lblBeds, lblStringAvailability, 
					this.lblAvailability, this.btnPlus, this.lblRoomChoise, this.btnMinus);
			
			this.btnMinus.setOnAction(minusHandler);
			
			this.btnPlus.setOnAction(plusHandler);
			
			this.btnMinus.setId(String.valueOf(index));
			
			this.btnPlus.setId(String.valueOf(index));
			
			this.disableMinusButton();
			
		}
		
		public String getRoomAvailability() {
			
			return this.lblAvailability.getText();
			
		}
		
		public void setRoomChoise(String choise) {
			
			this.lblRoomChoise.setText(choise);
			
		}
		
		public String getRoomChoise() {
			
			return this.lblRoomChoise.getText();
			
		}
		
		public String getNumberOfBeds() {
			
			return this.lblBeds.getText();
			
		}
		
		public void disableMinusButton() {
			
			this.btnMinus.setDisable(true);
			
		}
		
		public void disablePlusButton() {
			
			this.btnPlus.setDisable(true);
			
		}
		
		public void enableMinusButton() {
			
			this.btnMinus.setDisable(false);
			
		}
		
		public void enablePlusButton() {
			
			this.btnPlus.setDisable(false);
			
		}
		
	}

}
