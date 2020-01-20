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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.bean.RoomBean;

public class BookingView extends Application {
	
	private ScrollPane scrollPane = new ScrollPane();
	
	private VBox vBoxLeft = new VBox(10);
	
	private Label lblUsername = new Label();
	
	private Button btnBook = new Button("Confirm");
	private Button btnBack = new Button("Cancel");
	
	private List<PersonForm> people;
	
	private List<RoomBox> roomsBox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));
		
		this.scrollPane.setPadding(new Insets(20, 20, 20, 20));
		
		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);
		
		Text title = new Text("TravelBook");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		Region region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		hBoxTitle.getChildren().addAll(title, region, lblUsername);
		
		HBox hBoxBottom = new HBox(10);
		hBoxBottom.setPadding(new Insets(10, 0, 0, 0));
		hBoxBottom.setAlignment(Pos.CENTER_RIGHT);
		this.btnBook.setFont(Font.font(20));
		this.btnBack.setFont(Font.font(20));
		hBoxBottom.getChildren().addAll(this.btnBack, this.btnBook);
		
		borderPane.setTop(hBoxTitle);
		borderPane.setCenter(this.scrollPane);
		borderPane.setBottom(hBoxBottom);
		borderPane.setLeft(this.vBoxLeft);
		
		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();		
		
	}
	
	public void setUsername(String username) {
		
		this.lblUsername.setText(username);
		
	}
		
	public void addBackHandler(EventHandler<ActionEvent> backHandler) {
		
		this.btnBack.setOnAction(backHandler);
		
	}
	
	public void addConfirmHandler(EventHandler<ActionEvent> bookHandler) {
		
		this.btnBook.setOnAction(bookHandler);
		
	}
	
	public void populateView(int numberOfPerson) {
		
		VBox vBox = new VBox(10);
		this.people = new ArrayList<>();
		
		for(int i = 0; i < numberOfPerson; i++)
			people.add(new PersonForm(String.valueOf(i+1)));
		
		Label lbl = new Label("Enter the following information for each person.");
		lbl.setFont(Font.font(20));
		
		vBox.getChildren().add(lbl);
		vBox.getChildren().addAll(people);
		this.scrollPane.setContent(vBox);
		this.scrollPane.setFitToWidth(true);
		
	}
	
	public List<PersonForm> getPeopleList() { return this.people; }
	
	public void populateViewLeft(List<RoomBean> roomBeans) {		
		this.vBoxLeft.getChildren().clear();
		this.vBoxLeft.setPadding(new Insets(20, 20, 20, 20));
		
		this.roomsBox = new ArrayList<>();
		
		for(RoomBean roomBean : roomBeans) {
			
			roomsBox.add( new RoomBox( String.valueOf(roomBean.getBeds()), String.valueOf(roomBean.getRoomChoise()) ) );
			
		}
		
		this.vBoxLeft.getChildren().addAll(roomsBox);
		
	}
	
	public List<RoomBox> getRoomsBox() { return this.roomsBox; }
	
	public class RoomBox extends HBox {
		
		public RoomBox(String beds, String numberOfRooms) {
			super(10);
			
			Label lblNumberOfBeds = new Label("#" + beds);
			
			Label lblNumberOfRooms = new Label("Number of rooms choise: " + numberOfRooms);
			
			this.getChildren().addAll(lblNumberOfBeds, lblNumberOfRooms);			
			
		}
		
	}

	public class PersonForm extends VBox {
		
		private TextField name = new TextField();
		private TextField lastname = new TextField();
		private TextField fiscalCode = new TextField();
		private Text txtErr = new Text("You have to fill all the fields.");
		
		public PersonForm(String num) {
			super(10);
			
			Label number = new Label("#" + num);
			number.setFont(Font.font(18));
			this.name.setPromptText("Enter name");
			this.lastname.setPromptText("Enter lastname");
			this.fiscalCode.setPromptText("Enter fiscal code");
			this.txtErr.setFill(Color.RED);
			this.txtErr.setFont(Font.font(18));
			this.txtErr.setVisible(false);
			
			this.getChildren().addAll(number, this.name, this.lastname, this.fiscalCode, this.txtErr);
			
		}
		
		public void setErrorVisible(boolean value) {
			
			this.txtErr.setVisible(value);
			
		}
		
		public String getName() { return this.name.getText(); }
		
		public String getLastname() { return this.lastname.getText(); }
		
		public String getFiscalCode() { return this.fiscalCode.getText(); }
		
	}

}
