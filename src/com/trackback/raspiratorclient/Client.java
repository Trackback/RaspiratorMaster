package com.trackback.raspiratorclient;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import com.trackback.raspiratorclient.gui.Gui;
import com.trackback.raspiratorclient.tools.BaseFunction;

public class Client implements onServerResponse, onInterfaceActionListner {
	public static BaseFunction bf;
	public static Gui gui;
	public static UI ui;
	public static Connection connection;
	
	
	public Client(Stage stage) {
		bf = new BaseFunction();
		gui = new Gui(stage);
		gui.buildStage("interface.fxml");
		gui.show();
		ui = new UI();
		ui.bindInterface(this);

	}

	@Override
	public void serverResponse(final String data) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
            	ui.showAtHistory(data);
            }
        });
	}

	@Override
	public void closeConnection() {
		ui.showAtHistory("\n Closing...");
		connection.request("exit");
		ui.showAtHistory("exit");
		System.exit(0);
	}

	@Override
	public void sendCommand() {
		connection.request(ui.getCommand());
		ui.clearInputCommand();
	}

	@Override
	public void onCommandInputKeyPressed(KeyEvent e) {
		KeyCode key = e.getCode();
		if(key ==KeyCode.ENTER){
			sendCommand();
		}
		
	}

	@Override
	public void connect(String address, String port) {
		connection = new Connection(address, port);
		connection.setListner(this);		
	}

}
