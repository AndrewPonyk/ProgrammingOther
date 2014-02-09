package com.ap.behavioral.chainofresponsibility;


public class EmailProcessor {
		
		private EmailHandler successor;
		private EmailHandler first;
		
		public EmailProcessor(){
		}
		
		public void addEmailHandler(EmailHandler emailHandler){
			if(this.first == null){
				this.first = emailHandler;
			}else{
				this.successor.setNext(emailHandler);	
			}
			this.successor = emailHandler;
		}
		
		public void handleRequest(Email email){
			first.handleRequest(email);
		}
		
	}