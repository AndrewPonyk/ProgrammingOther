package com.ap.behavioral.strategy;

/** The classes that implement a concrete strategy should implement this.
* The Context class uses this to call the concrete strategy. */
interface Strategy {
    int execute(int a, int b); 
};
 
/** Implements the algorithm using the strategy interface */
class Add implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Called Add's execute()");
        return a + b;  // Do an addition with a and b
    }
};
 
class Subtract implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Called Subtract's execute()");
        return a - b;  // Do a subtraction with a and b
    }
};
 
class Multiply implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Called Multiply's execute()");
        return a * b;   // Do a multiplication with a and b
    }    
};
 
// Configured with a ConcreteStrategy object and maintains
// a reference to a Strategy object 
class Context {
    private Strategy strategy;
 
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
 
    public int executeStrategy(int a, int b) {
        return this.strategy.execute(a, b);
    }
};
 
/** Tests the pattern */
public class StrategyProgram {
    public static void main(String[] args) {
      Context context = new Context(new Multiply());
      int result = context.executeStrategy(4,4);
      System.out.println(result);
      
      
      context = new Context(new Add());
      result = context.executeStrategy(4,4);
      System.out.println(result);
    }
};
