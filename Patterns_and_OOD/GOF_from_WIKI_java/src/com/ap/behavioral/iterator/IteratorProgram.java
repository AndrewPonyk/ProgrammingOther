package com.ap.behavioral.iterator;

import java.util.ArrayList;

public class IteratorProgram {
	public static void main(String[] args) {
	      ConcreteAggregate a = new ConcreteAggregate();
          a.setThis(0, "Item A");
          a.setThis(1, "Item B");
          a.setThis(2, "Item C");

          // Create Iterator and provide aggregate
          ConcreteIterator i = new ConcreteIterator(a);

          System.out.println("Iterating over collection:");

          Object item = i.First();
          while (item != null)
          {
        	  System.out.println(item);
              item = i.Next();
          }
	}
}
