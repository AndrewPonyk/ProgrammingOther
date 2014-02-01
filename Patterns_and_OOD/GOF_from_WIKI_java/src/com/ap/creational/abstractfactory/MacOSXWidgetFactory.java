package com.ap.creational.abstractfactory;

//ConcreteFactory2
public class MacOSXWidgetFactory implements AbstractWidgetFactory{
	//create a MacOSXWindow
	public Window createWindow(){
	    MacOSXWindow window = new MacOSXWindow();  
	    System.out.println("Creating Mac OS window");
	    return window;
	}
}