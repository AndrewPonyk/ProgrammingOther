package com.ap.behavioral.chainofresponsibility;

//email client 

public class ChainOfResponsibilityProgram
{
	private EmailProcessor processor; 
	
	public ChainOfResponsibilityProgram(){
	   createProcessor();
	}

	private void createProcessor(){
		processor = new EmailProcessor();
		processor.addEmailHandler(new BusinessMailHandler());
		processor.addEmailHandler(new GMailHandler());
	}
	


	public void emailReceived(Email email){
		processor.handleRequest(email);
	}
	

	public static void main(String[] args){
		ChainOfResponsibilityProgram client = new ChainOfResponsibilityProgram();
		Email helloEmail = new Email();
		helloEmail.setFrom("andrew9999@gmail.com");
		client.emailReceived(helloEmail);
	}

}
