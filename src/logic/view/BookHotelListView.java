package logic.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.model.RentablePlace;
import logic.mydatecell.MyCallback;

public class BookHotelListView extends MainView {
	
	private ScrollPane scrollPane = new ScrollPane();
	
	private Button btnPlus = new Button("+");
	private Button btnMinus = new Button("-");
	private Button btnSearch = new Button("Search");
	
	private TextField txtFieldCity= new TextField();
	
	private DatePicker dPickerCheckIn = new DatePicker();
	private DatePicker dPickerCheckOut = new DatePicker();
	
	private Label lblPersonCount = new Label("0");
	
	private Text txtErrCity = new Text("You have to fill this field!");
	private Text txtErrCheckIn = new Text("You have to fill this field!");
	private Text txtErrCheckOut = new Text("You have to fill this field!");
	private Text txtErrPersonCount = new Text("You have select how much you are!");

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		super.start(primaryStage);
		
		Label lblCity = new Label("Where do you want to go");
		Label lblCheckIn = new Label("Enter Check-In");
		Label lblCheckOut = new Label("Enter Check-Out");
		
		txtErrCity.setFill(Color.RED);
		txtErrCity.setVisible(false);
		txtErrCheckIn.setFill(Color.RED);
		txtErrCheckIn.setVisible(false);
		txtErrCheckOut.setFill(Color.RED);
		txtErrCheckOut.setVisible(false);
		txtErrPersonCount.setFill(Color.RED);
		txtErrPersonCount.setVisible(false);
		
		txtFieldCity.setPromptText("e.g. Rome");	
		
		dPickerCheckIn.setPromptText("Pick a date");
		dPickerCheckIn.setDayCellFactory(MyCallback.getDayCellFactory());
		dPickerCheckIn.setEditable(false);
		
		dPickerCheckOut.setPromptText("Pick a date");
		dPickerCheckOut.setDayCellFactory(MyCallback.getDayCellFactory());
		dPickerCheckOut.setEditable(false);
		
		HBox personCountHBox = new HBox(10);
		personCountHBox.setAlignment(Pos.CENTER);
		personCountHBox.getChildren().addAll(btnPlus, lblPersonCount, btnMinus);
		
		VBox vBoxLeft = new VBox(5);
		vBoxLeft.setPadding(new Insets(50, 20, 20, 0));
		vBoxLeft.getChildren().addAll(lblCity, txtFieldCity, txtErrCity,
				lblCheckIn, dPickerCheckIn, txtErrCheckIn,
				lblCheckOut, dPickerCheckOut, txtErrCheckOut, 
				personCountHBox, txtErrPersonCount, btnSearch);
		
		super.borderPane.setLeft(vBoxLeft);
		super.borderPane.setCenter(this.scrollPane);
		
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
	
	public void setCityField(String city) {		
		this.txtFieldCity.setText(city);		
	}
	
	public void setCheckInDate(LocalDate checkIn) {		
		this.dPickerCheckIn.setValue(checkIn);		
	}
	
	public void setCheckOutDate(LocalDate checkOut) {		
		this.dPickerCheckOut.setValue(checkOut);		
	}	
	
	public void addSearchListener(EventHandler<ActionEvent> searchHandler) {		
		this.btnSearch.setOnAction(searchHandler);		
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
	
	public void setPersonCountText(String value) {
		this.lblPersonCount.setText(value); 
	}
	
	public String getPersonCount() { 
		return this.lblPersonCount.getText(); 
	}
	
	public String getCityField() { 
		return this.txtFieldCity.getText();	
	}
	
	public LocalDate getCheckInDate() { 
		return this.dPickerCheckIn.getValue(); 
	}
	
	public LocalDate getCheckOutDate() {
		return this.dPickerCheckOut.getValue(); 
	}
	
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
	
	public void resultNotFound() {
		
		Label lblResultNotFound = new Label("No result for your search");
		this.scrollPane.setContent(lblResultNotFound);
		
	}
	
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
		
		public String getName() { 
			return this.name.getText();
		}
		
		public String getAddress() { 
			return this.address.getText(); 
		}
		
	}

}