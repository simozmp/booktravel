package logic.view;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HotelView extends Application {
	
	private Button btnLogin = new Button("Login");
	private Button btnSignIn = new Button("Sign In");
	private Button btnBack = new Button("Back");
	private Label name = new Label();
	private Label address = new Label();
	private Label information = new Label();

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
		
		HBox hBoxCenter = new HBox(10);
		hBoxCenter.setPadding(new Insets(0, 20, 20, 20));
		this.name.setFont(Font.font(20));
		this.address.setFont(Font.font(20));
		this.information.setFont(Font.font(20));
		hBoxCenter.getChildren().addAll(this.name, this.address, this.information);
		
		VBox vBoxLeft = new VBox();
		vBoxLeft.getChildren().add(this.btnBack);
		
		borderPane.setLeft(vBoxLeft);
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(hBoxCenter);
		
		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public void addBackHandler(EventHandler<ActionEvent> backHandler) {
		
		this.btnBack.setOnAction(backHandler);
		
	}
	
	public void setName(String name) { this.name.setText(name); }
	
	public void setAddress(String address) { this.address.setText(address); }
	
	public void setInformation(String information) { this.information.setText(information); }

}
