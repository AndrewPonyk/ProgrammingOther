package com.ap.creational.abstractfactory;

public class AbstractFactoryProgram {
	
   public static void main(String[] args)   
   {
     GUIBuilder builder = new GUIBuilder();
     AbstractWidgetFactory widgetFactory = null;
     String platform = System.getProperty("os.name").toLowerCase();
     //check what platform we're on 
     if(platform.contains("MACOSX"))
     {
        widgetFactory  = new MacOSXWidgetFactory();
     }
     else 
     {
           widgetFactory  = new MsWindowsWidgetFactory();
     }
     builder.buildWindow(widgetFactory); 
    }
}