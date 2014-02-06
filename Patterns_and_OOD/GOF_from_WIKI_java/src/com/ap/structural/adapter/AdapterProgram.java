package com.ap.structural.adapter;

public class AdapterProgram {
	
	 public static void eat(Object dish){
		 // =)
	  }
	 
	  public static void main(String[] args)
	  {
		  // example of using class adapter (there is also object adapter pattern)
	    Chief ch = new ChiefAdapter();
	    Object dish = ch.makeBreakfast();
	    eat(dish);
	    dish = ch.makeLunch();
	    eat(dish);
	    dish = ch.makeDinner();
	    eat(dish);
	  }
}
