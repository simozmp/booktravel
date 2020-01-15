package logic.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;

public class CustomsAndTraditionsView extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException{
		
		// Strings declaration
		String country = "Italy";
		@SuppressWarnings("unused")
		String customsAndTraditionsSource = "https://www.livescience.com/44376-italian-culture.html"; // Information purpose only

		double windowWidth = 1000;
		double windowHeight = 700;
		BackgroundFill background_fill;
		
		// Containers declaration
		VBox mainBox;
		HBox upperSide;
		HBox controlButtons;
		HBox sectionBox;
		HBox body;
		
		// Declaration and initialization for..
		
		//..labels,..
		Label logo = new Label("Book Travel");
		Label sectionLabel = new Label("Customs and traditions");
		Label countryLabel = new Label(country);
		Label mainText = new Label();
		
		//..images,..
		Image flagImage = new Image(getClass().getResource("/Italy-Flag.png").toString(),50,50,true,true);
		ImageView flagImageView = new ImageView(flagImage);
		
		//..buttons.
		Button controlHome = new Button("Home");
		Button controlSearch = new Button("Search");
		Button controlDiscover = new Button("Discover");
		Button controlProfile = new Button("Profile");
		
		// main text reading from file
		File file = new File(getClass().getResource("/customs_and_traditions_italy_sample.txt").getFile());
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		mainText.setText(new String(data, "UTF-8"));
		
		// Content wrapping

		sectionBox = new HBox(40,sectionLabel, countryLabel, flagImageView);
		
		controlButtons = new HBox(10, controlHome, controlSearch, controlDiscover, controlProfile);
		upperSide = new HBox(logo, controlButtons);
		
		Pane leftMargin = new Pane(), rightMargin = new Pane();
		leftMargin.setStyle("-fx-background-color: #FFFFFF;");
		rightMargin.setStyle("-fx-background-color: #FFFFFF;");
		ScrollPane mainTextScrollPane = new ScrollPane(mainText);
		mainTextScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		mainTextScrollPane.setFitToWidth(true);
		body = new HBox(mainTextScrollPane);
		mainBox = new VBox(upperSide, sectionBox, body);
		
		
		// Layout setting
		upperSide.setAlignment(Pos.CENTER);
		upperSide.setPadding(new Insets(15, 12, 15, 12));
		logo.setAlignment(Pos.CENTER_LEFT);
		controlButtons.setAlignment(Pos.CENTER_RIGHT);
		upperSide.setPrefWidth(windowWidth);
		HBox.setHgrow(upperSide, Priority.ALWAYS);
		HBox.setHgrow(logo, Priority.ALWAYS);
		HBox.setHgrow(controlButtons, Priority.ALWAYS);

		sectionBox.setAlignment(Pos.BASELINE_CENTER);
		sectionBox.setPadding(new Insets(15, 12, 15, 12));
		sectionLabel.setAlignment(Pos.CENTER_LEFT);
		//flagImageView.setAlignment();
		sectionBox.setPrefWidth(windowWidth);
		HBox.setHgrow(sectionBox, Priority.ALWAYS);
		HBox.setHgrow(sectionLabel, Priority.ALWAYS);
		HBox.setHgrow(flagImageView, Priority.ALWAYS);
		
		mainBox.setPrefWidth(windowWidth);
		
		// Text formatting
		logo.setFont(Font.font("Verdana", 30));
		sectionLabel.setFont(Font.font("Verdana", 20));
		countryLabel.setFont(Font.font("Verdana", 20));
		mainText.setFont(Font.font("Arial", 14));
		mainText.setWrapText(true);
		logo.setTextFill(Color.WHITE);
		sectionLabel.setTextFill(Color.WHITE);
		countryLabel.setTextFill(Color.WHITE);
		
		// Color setting
        background_fill = new BackgroundFill(Color.SEAGREEN, CornerRadii.EMPTY, Insets.EMPTY);
        Background baseBackground = new Background(background_fill); 
        
        mainBox.setBackground(baseBackground);

		// Stage setting
		stage.setTitle("Customs and traditions - " + country);
		
		Scene scene = new Scene(mainBox, windowWidth, windowHeight);
		stage.setScene(scene);
		
		stage.show();
	}
}
