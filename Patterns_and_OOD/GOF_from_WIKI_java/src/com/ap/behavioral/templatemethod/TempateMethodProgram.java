package com.ap.behavioral.templatemethod;

public class TempateMethodProgram {
	public static void main(String[] args) {
		PackageA trip = new PackageA();
		trip.performTrip();
	}
}

abstract class Trip {
    public final void performTrip(){
             doComingTransport();
             doDayA();
             doDayB();
             doDayC();
             doReturningTransport();
    }
    public abstract void doComingTransport();
    public abstract void doDayA();
    public abstract void doDayB();
    public abstract void doDayC();
    public abstract void doReturningTransport();
}

class PackageA extends Trip {
    public void doComingTransport() {
             System.out.println("The turists are comming by air ...");
    }
    public void doDayA() {
             System.out.println("The turists are visiting the aquarium ...");
    }
    public void doDayB() {
             System.out.println("The turists are going to the beach ...");
    }
    public void doDayC() {
             System.out.println("The turists are going to mountains ...");
    }
    public void doReturningTransport() {
             System.out.println("The turists are going home by air ...");
    }
}
class PackageB extends Trip {
    public void doComingTransport() {
             System.out.println("The turists are comming by train ...");
    }
    public void doDayA() {
             System.out.println("The turists are visiting the mountain ...");
    }
    public void doDayB() {
             System.out.println("The turists are going to the beach ...");
    }
    public void doDayC() {
             System.out.println("The turists are going to zoo ...");
    }
    public void doReturningTransport() {
             System.out.println("The turists are going home by train ...");
    }
}