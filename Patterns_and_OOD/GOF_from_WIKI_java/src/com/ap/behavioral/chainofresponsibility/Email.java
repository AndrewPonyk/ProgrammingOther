package com.ap.behavioral.chainofresponsibility;

public class Email {

	private String text;
	private String from;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
