package com.trackback.raspiratorclient.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.trackback.raspiratorclient.Client;
import com.trackback.raspiratorclient.tools.D;

public class Gui {

	private Stage primaryStage;
	private final String TAG = "GUI";
	public Gui(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public Stage createWindow(String template){
		Stage stage = new Stage();
		stage.setScene(new Scene(getTemplate(template)));
		stage.initModality(Modality.WINDOW_MODAL);
		return stage;
	}
	
	public void buildStage(String stage){
		try{
			primaryStage.setScene(new Scene(getTemplate(stage)));
		}catch(Exception e){
			e.printStackTrace();
			D.log(TAG, e.getMessage());
		}
	}
	
	public void show(){
		 primaryStage.show();
	}
	
	public Stage getCurrentStage(){
		return primaryStage;
	}
	
	public Text getTextFromScene(String node) {
		return (Text) getCurrentStage().getScene().lookup("#" + node);
	}
	
	public TextField getTextFieldFromScene(String node) {
		return (TextField)  getCurrentStage().getScene().lookup("#" + node);
	}
	
	public Button getButtonFromScene(String node) {
		return (Button) getCurrentStage().getScene().lookup("#" + node);
	}
	
	public ListView<String> getStringifyListFromScene(String node) {
		return (ListView<String>) getCurrentStage().getScene().lookup("#" + node);
	}
	
	public AnchorPane getAnchorPaneFromScene(String node) {
		return (AnchorPane) getCurrentStage().getScene().lookup("#" + node);
	}
	
	public ProgressIndicator getProgressIndicatorFromScene(String node) {
		return (ProgressIndicator) getCurrentStage().getScene().lookup("#" + node);
	}
	
	public Text getTextFromScene(Stage stage, String node) {
		return (Text) stage.getScene().lookup("#" + node);
	}
	
	public AnchorPane getAnchorPaneFromScene(Stage stage, String node) {
		return (AnchorPane) stage.getScene().lookup("#" + node);
	}
	
	public TextField getTextFieldFromScene(Stage stage, String node) {
		return (TextField) stage.getScene().lookup("#" + node);
	}

	public ListView<String> getStringifyListFromScene(Stage stage, String node) {
		return (ListView<String>) stage.getScene().lookup("#" + node);
	}
	
	public Button getButtonFromScene(Stage stage, String node) {
		return (Button) stage.getScene().lookup("#" + node);
	}

	public ProgressIndicator getProgressIndicatorFromScene(Stage stage, String node) {
		return (ProgressIndicator) stage.getScene().lookup("#" + node);
	}
	
	public Parent getTemplate(String stage) {
		try {
			return FXMLLoader.load(Client.class.getResource("resources/layouts/"+ stage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
