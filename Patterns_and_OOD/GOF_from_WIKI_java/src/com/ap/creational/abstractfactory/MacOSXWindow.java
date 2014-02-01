package com.ap.creational.abstractfactory;

//ConcreteProductA2
public class MacOSXWindow implements Window{
	public void setTitle(String arg){
	   //Mac OSX specific behaviour 
	}

	public void repaint(){
	   //Mac OSX specific behaviour 
		System.out.println("Mac OS window repainted");
	}
}