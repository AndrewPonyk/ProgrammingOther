package com.ap.creational.singleton;

//enum way Singleton
/*public enum Singleton {
	INSTANCE;
	public void execute(String arg) {
		// perform operation here
		System.out.println("executing something");

	}
}*/

// Initialization on demand 
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

// Double checked locking
/*public class Singleton {
    private static volatile Singleton instance = null;
    private Singleton() {    }
 
    public static Singleton getInstance() {
        if (instance == null) {
                        synchronized (Singleton .class){
                    if (instance == null) {
                                        instance = new Singleton();
                                }
                        }
        }
        return instance;
    }
}*/


//Eager initialization
//If the program will always need an instance, or if the cost of creating the instance 
//is not too large in terms of time/resources, the programmer can switch to eager
//initialization, which always creates an instance:
/*public class Singleton {
  private static final Singleton INSTANCE = new Singleton();

  private Singleton() {}

  public static Singleton getInstance() {
      return INSTANCE;
  }
}*/