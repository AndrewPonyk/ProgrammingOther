package com.ap.creational.singleton;

//enum way Singleton
/*public enum Singleton {
	INSTANCE;
	public void execute(String arg) {
		// perform operation here
		System.out.println("executing something");

	}
}*/

public class Singleton{
	private Singleton(){
		System.out.println("Created singleton");
	}
	
	private static class SingletonHolder{
		public static final Singleton INSTANCE = new Singleton();
	}
	
	public void execute(String arg){
		System.out.println("Execute " + arg);
	}
	
	public static Singleton getInstanse(){
		return SingletonHolder.INSTANCE;
	}
}