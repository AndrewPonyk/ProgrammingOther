package com.ap.behavioral.observer;

import java.util.Observable;
import java.util.Observer;  /* this is Event Handler */
 
public class ResponseHandler implements Observer {
	String header;
	
	ResponseHandler(String responseHeader){
		this.header = responseHeader;
	}
    private String resp;
    public void update(Observable obj, Object arg) {
        if (arg instanceof String) {
            resp = (String) arg;
            System.out.println("\n"+header +" : Received Response: " + resp );
        }
    }
}