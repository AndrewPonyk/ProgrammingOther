package com.ap.creational.builder;

public class BuilderProgram {
	public static void main(String[] args) {
		System.out.println("Using Builder pattern");
		
		User andrew = new  User.UserBuilder("andrew","lastname").age(21).build();
		
		System.out.println(andrew.getFirstName() + " " + andrew.getAge());
	}
}
