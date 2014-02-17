package com.ap.behavioral.visitor;

public class VisitorProgram {
	public static void main(String[] args) {
		
		ICarElement car = new Car();
		CarElementPrintVisitor visitor = new CarElementPrintVisitor();
		
		car.accept(visitor);
		
		
		
		
		/*
		 * 
		 * 
		 * 
		 * Visitor pattern - is complcated on first sight , but it ISNT.
		 * 
		 * 
		 * Visitor (Visitor) algorithm allows to separate some of 
			elements on which the algorithm must be executed, so we 
			can easily add or change the algorithm without changes to elements 
			systems. To me, this is one of the most significant advantages of this 
			paternu.46
		 * 
		 * 
		 * Look into Andrii Budaj book - for good explanation
		 * Imagine that you have finally managed to create a
		own company and since it is a decent size, you
		decided to rent it for the whole building. We state
		very good and care about the company. And so in all enterprises
		meet the requirements of varied continuously sent
		inspection. Moreover, the rules by which your check
		enterprise is constantly changing.
		 * 
		 * 
		 * In the near future you will need to take many
		visitors (visitors), such as electrical (electrician), plumber
		(plumber), a tax and so on ... They will check your building
		far and wide , going from floor to floor , from room to room .
		I suspect some kind of class diagram in your head already
		appeared. If so, I have the following question :
		where should live a certain logic building inspection ?
		Does the building know how to check the electrical shields
		, or should know an electrician, or whether the room to know
		how to check the switch , or is it just work electrician ?
		Of course, the electrician , who is the guest ,
		encapsulates the logic verification of certain elements
		(elements) of your building.
		 * */
		
		
		
		
	}
}