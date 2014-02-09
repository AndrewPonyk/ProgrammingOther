package com.ap.behavioral.chainofresponsibility;

public class BusinessMailHandler implements EmailHandler
{
	private EmailHandler next;

	public void setNext(EmailHandler handler){
	    next = handler;
	}
	
	public void handleRequest(Email email){
		if(!email.getFrom().endsWith("@businessaddress.com")){
		    next.handleRequest(email);
		}
		else{
		    //handle request (move to correct folder)
			System.out.println("Business handler");
		}
	}	
}