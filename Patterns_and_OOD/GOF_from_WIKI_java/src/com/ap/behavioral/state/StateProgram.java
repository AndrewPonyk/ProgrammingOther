package com.ap.behavioral.state;

import com.ap.behavioral.state.OrderState;

public class StateProgram {
	public static void main(String[] args) throws Exception {
		System.out.println("Using State pattern");
		
		OrderState order = new OrderState();
		order.Register();
		order.Approve();
		
		order.Dispatch();
	}
}
