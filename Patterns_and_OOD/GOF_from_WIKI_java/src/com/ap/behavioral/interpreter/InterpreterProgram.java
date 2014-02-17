package com.ap.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

public class InterpreterProgram {
	public static void main(String[] args) {
		
		String expression = "x y z + -";
		Evaluator eval = new Evaluator(expression);
		
		 Map<String,Expression> variables = new HashMap<String,Expression>();
		
		 
		 variables.put("x", new Number(10));
		 variables.put("y", new Number(2));
		 variables.put("z", new Number(1));
		 
		 int result = eval.interpret(variables);
		 System.out.println(result);
	}
}
