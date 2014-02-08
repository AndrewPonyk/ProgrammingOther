package com.ap.structural.facade;

public class FacadeProgram {
	
	public static void main(String[] args) {
		ComputerFacade computer = new ComputerFacade();
		computer.start();
	}
}
