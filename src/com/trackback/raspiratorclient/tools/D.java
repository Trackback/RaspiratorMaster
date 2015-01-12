package com.trackback.raspiratorclient.tools;

import com.trackback.raspiratorclient.Settings.Settings;



public abstract class D {
	public static void log(String tag, String event){
		if(Settings.debug){
			System.out.println(tag+": "+event);
		}
	}
	
	public static void log(String event){
		if(Settings.debug){
			System.out.println(event);
		}
	}
}
