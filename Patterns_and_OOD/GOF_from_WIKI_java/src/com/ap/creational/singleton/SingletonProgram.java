package com.ap.creational.singleton;


public class SingletonProgram {

	public static void main(String[] args) {
			System.out.println("Using Singleton");
			
			///Enum way
			//Singleton singleton = Singleton.INSTANCE;
			//singleton.execute("some argument");
			
			
			/// Initialization On Demand Holder Idiom
			Singleton singleton = Singleton.getInstanse();
			singleton.execute("123");
	}

}


