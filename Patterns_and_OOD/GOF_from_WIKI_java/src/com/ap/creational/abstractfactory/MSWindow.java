package com.ap.creational.abstractfactory;

//ConcreteProductA1
public class MSWindow implements Window{
	public void setTitle(String text){
	   //MS Windows specific behaviour 
	}

	public void repaint(){
	   //MS Windows specific behaviour 
		System.out.println("Windows window painted");
	}
}