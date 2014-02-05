package com.ap.creational.prototype;

public class PrototypeProgram {

    public static void main(String args[]) throws CloneNotSupportedException {
        Cookie tempCookie = null;
        Cookie prot = new CoconutCookie();
        CookieMachine cm = new CookieMachine(prot);
        for (int i = 0; i < 100; i++){
            tempCookie = cm.makeCookie();
        	System.out.println(tempCookie);
        }
    }


}
