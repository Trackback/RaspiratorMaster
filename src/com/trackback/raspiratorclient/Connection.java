package com.trackback.raspiratorclient;

import java.io.InputStream;
import java.net.Socket;

import javafx.application.Platform;

import com.trackback.raspiratorclient.tools.D;

public class Connection extends Thread {
	private Socket socket;
	private onServerResponse listener;
	private InputStream in;
	public String address = "127.0.0.1";
	public int port = 4000;
	
	public Connection() {
		setDaemon(true);
		setPriority(NORM_PRIORITY);
		start();
	}
	
	public Connection(String address, String port) {
		this.address = address;
		this.port = Integer.parseInt(port);
		
		setDaemon(true);
		setPriority(NORM_PRIORITY);
		start();
	}

	public void run() {
		try {
			socket = new Socket(this.address, this.port);
			socket.setKeepAlive(true);
			in = socket.getInputStream();
			keepALive();
		} catch (Exception e) {
			requestReconnection();
			D.log("Connection", "Socket is down!");
			e.printStackTrace();
		} 
	}
	
	public void request(String requestData){
		try{
			if(socket != null && socket.getOutputStream() != null){
				socket.getOutputStream().write(requestData.getBytes());
			}else{
				requestReconnection();
			}
			
		}catch(Exception e){
			requestReconnection();
			D.log("Connection", "Command send failed!");
			e.printStackTrace();
		}

	}
	
	public void keepALive() {
		try {
			boolean alow = true;
			if(isListned() && in != null){
				listener.serverResponse("Connection successful! Welcome!");
			}else{
				alow = false;
			}
			while (alow) {
				byte buf[] = new byte[64 * 1024];
				int r = in.read(buf);
				String data = new String(buf, 0, r);
				if (isListned() && !data.equals(null)) {
					listener.serverResponse(data);
				}
				if (data.equals("exit")) {
					break;
				}
			}
		} catch (Exception e) {
			requestReconnection();
			D.log("Connection", "Reading is fail");
			e.printStackTrace();
		}
	}
	
	public boolean isListned(){
		return (listener == null)? false : true;
	}
	
	public void setListner(onServerResponse listner){
		this.listener = listner;
	}
	
	public void requestReconnection(){
        if (Platform.isFxApplicationThread()) {  
			Client.ui.showConnectionFrom("Raspirator is lost! Try to recconect!", true);
        } else {  
            Platform.runLater(new Runnable() {  
                @Override  
                public void run() {  
        			Client.ui.showConnectionFrom("Raspirator is lost! Try to recconect!", true);
                }  
            });  
        } 
	}

}
