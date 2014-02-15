package com.ap.behavioral.observer;

public class ObserverProgram {
	public static void main(String[] args) {
		 System.out.println("Enter Text >");
		 
//		 // create an event source - reads from stdin
		 final EventSource eventSource = new EventSource();
//	 
//		// create an observer
		final ResponseHandler responseHandler = new ResponseHandler("handler1");
		final ResponseHandler responseHandler2 = new ResponseHandler("handler2");
//		 
//		// subscribe the observer to the event source
		eventSource.addObserver(responseHandler);
		eventSource.addObserver(responseHandler2);
//		 
//		// starts the event thread
		Thread thread = new Thread(eventSource);
		thread.start();
	}
}
