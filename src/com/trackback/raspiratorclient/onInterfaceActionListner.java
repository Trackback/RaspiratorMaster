package com.trackback.raspiratorclient;

import javafx.scene.input.KeyEvent;


public interface onInterfaceActionListner {
	public abstract void closeConnection();
	public abstract void sendCommand();
	public abstract void onCommandInputKeyPressed(KeyEvent e);
	public abstract void connect(String address, String port);
}
