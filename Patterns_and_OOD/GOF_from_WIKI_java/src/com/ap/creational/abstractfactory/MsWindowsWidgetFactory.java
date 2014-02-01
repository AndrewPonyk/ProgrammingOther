package com.ap.creational.abstractfactory;


//ConcreteFactory1
public class MsWindowsWidgetFactory implements AbstractWidgetFactory{
	//create an MSWindow
	public Window createWindow(){
	    MSWindow window = new MSWindow();   
	    System.out.println("Creating windows window");
	    return window;
	}
}