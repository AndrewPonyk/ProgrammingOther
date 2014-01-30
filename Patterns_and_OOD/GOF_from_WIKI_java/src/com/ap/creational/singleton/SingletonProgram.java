package com.ap.creational.singleton;

public class SingletonProgram {

	public static void main(String[] args) {
			System.out.println("Using Singleton");
			Singleton singleton = Singleton.INSTANCE;
			singleton.execute("some argument");
			
	}

}
