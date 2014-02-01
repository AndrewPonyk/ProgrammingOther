package com.ap.creational.singleton;

public class SingletonProgram {

	public static void main(String[] args) {
			System.out.println("Using Singleton");
			
			///Enum way
			//Singleton singleton = Singleton.INSTANCE;
			//singleton.execute("some argument");
			
			
			/// Initialization On Demand Holder Idiom
			Singleton singleton = Singleton.getInstanse();
			singleton.execute("123");
	}

}

/*
 * Initiliazation on demand
 * WIKI:
University of Maryland Computer Science researcher Bill Pugh has written about the code
 issues underlying the Singleton pattern when implemented in Java.[10] Pugh's efforts on the
 "Double-checked locking" idiom led to changes in the Java memory model in Java 5 and to what is
 generally regarded as the standard method to implement Singletons in Java. The technique known as the
 initialization on demand holder idiom, is as lazy as possible, and works in all known versions of Java.
It takes advantage of language guarantees about class initialization, and will therefore work correctly in
all Java-compliant compilers and virtual machines.
The nested class is referenced no earlier (and therefore loaded no earlier by the class loader) than the moment
that getInstance() is called. Thus, this solution is thread-safe without requiring special language constructs 
(i.e. volatile or synchronized).

Initialization on Demand is Thread-Safe:
The implementation relies on the well-specified initialization phase of execution within the Java Virtual 
Machine (JVM); see section 12.4 of Java Language Specification (JLS) for details.
When the class Something is loaded by the JVM, the class goes through initialization. Since the class does
 not have any static variables to initialize, the initialization completes trivially. The static class definition LazyHolder 
within it is not initialized until the JVM determines that LazyHolder must be executed.
The static class LazyHolder is only executed when the static method getInstance is invoked on the class Something,
and the first time this happens the JVM will load and initialize the LazyHolder class. The initialization of the LazyHolder
class results in static variable INSTANCE being initialized by executing the (private) constructor for the outer 
class Something. Since the class initialization phase is guaranteed by the JLS to be serial, i.e., non-concurrent, 
no further synchronization is required in the static getInstance method during loading and initialization. And since 
the initialization phase writes the static variable INSTANCE in a serial operation, all subsequent concurrent invocations of the getInstance will return the same correctly initialized INSTANCE without incurring any additional synchronization overhead.

Read more: http://javarevisited.blogspot.com/2012/12/how-to-create-thread-safe-singleton-in-java-example.html#ixzz2ruwrxJRo
 * 
 * */

