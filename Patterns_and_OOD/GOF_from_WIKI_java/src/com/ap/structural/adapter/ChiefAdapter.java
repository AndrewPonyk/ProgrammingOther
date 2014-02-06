package com.ap.structural.adapter;

//Adapter
public class ChiefAdapter extends Plumber implements Chief
{
	public Object makeBreakfast() {
		return getGasket();
	}
	public Object makeLunch() {
		return getPipe();
	}
	public Object makeDinner() {
		return getScrewNut();
	}
}