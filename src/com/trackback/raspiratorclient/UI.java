package com.trackback.raspiratorclient;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UI {
	private Button buttonCloseConnect;
	private Button buttonSendCommand;
	private TextField inputCommand;
	private ListView<String> list;
	private onInterfaceActionListner listner;
	private ObservableList<String> listAdapter; 
	
	public UI() {
		prepareSources();
	}
	
	private void prepareSources(){
		buttonCloseConnect = Client.gui.getButtonFromScene("button_close_connection");
		buttonSendCommand = Client.gui.getButtonFromScene("button_send_command");
		inputCommand = Client.gui.getTextFieldFromScene("input_command");
		list = Client.gui.getStringifyListFromScene("history");
		showConnectionFrom("", false);
	}
	
	
	public void showConnectionFrom(String message, boolean reconnect){
<<<<<<< HEAD
		final Stage cForm = Client.gui.createWindow("connection_form.fxml");
		final TextField inputAddress = Client.gui.getTextFieldFromScene(cForm, "input_address");
		final Button buttonConnect = Client.gui.getButtonFromScene(cForm, "button_connect"); 
		final Text textviewOutput = Client.gui.getTextFromScene(cForm, "textview_output");
=======
		Stage cForm = Client.gui.createWindow("connection_form.fxml");
		TextField inputAddress = Client.gui.getTextFieldFromScene(cForm, "input_address");
		Button buttonConnect = Client.gui.getButtonFromScene(cForm, "button_connect"); 
		Text textviewOutput = Client.gui.getTextFromScene(cForm, "textview_output");
>>>>>>> 3fcdfd50519f2ecf52e0daac9d4113feedba05ce
		
		textviewOutput.setText(message);
		if(reconnect){
			inputAddress.setText(Client.connection.address+":"+Client.connection.port);
		}
		
		
		buttonConnect.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				String addres = inputAddress.getText();
				String[] args = addres.split(":");
				if(addres.trim().length() < 6){
					textviewOutput.setText("Wrong addrees!");
				}else if (args.length != 2 ) {
					textviewOutput.setText("Wrong input format! Mast be like 8.8.8.8:4000");
				}else if (args.length == 2) {
					if(isListned()){
						listner.connect(args[0], args[1]);
						cForm.hide();
					}else{
						System.out.println("Error! Litner not found");
						textviewOutput.setText("Cant found clien connector!");
					}
				}else{
					textviewOutput.setText("Oops! Something happened and i don't know what");
				}
			}
		});
		
		inputAddress.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				KeyCode key = arg0.getCode();
				if(key ==KeyCode.ENTER){
					String addres = inputAddress.getText();
					String[] args = addres.split(":");
					if(addres.trim().length() < 6){
						textviewOutput.setText("Wrong addrees!");
					}else if (args.length != 2 ) {
						textviewOutput.setText("Wrong input format! Mast be like 8.8.8.8:4000");
					}else if (args.length == 2) {
						if(isListned()){
							listner.connect(args[0], args[1]);
							cForm.hide();
						}else{
							System.out.println("Error! Litner not found");
							textviewOutput.setText("Cant found clien connector!");
						}
					}else{
						textviewOutput.setText("Oops! Something happened and i don't know what");
					}
				}
			}
		});
		cForm.show();
		
	}
	
	
	public boolean isListned(){
		return (listner == null)? false : true;
	}
	
	public void showAtHistory(String data){
		String[] splitedHistory = data.split("\\r?\\n");
		for (String string : splitedHistory) {
			listAdapter.add(string);	
		}
	}
	
	public String getCommand(){
		return inputCommand.getText();
	}
	
	public void clearInputCommand(){
		inputCommand.setText("");
		inputCommand.requestFocus();
	}
	
	public void bindInterface(final onInterfaceActionListner listner){
		this.listner = listner;
		listAdapter = FXCollections.observableArrayList();

		buttonCloseConnect.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				listner.closeConnection();
			}
		});
		
		buttonSendCommand.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				listner.sendCommand();
			}
		});
		
		inputCommand.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				listner.onCommandInputKeyPressed(arg0);
			}
		});
		
		list.setItems(listAdapter);
		
	}

}
