package com.ap.creational.prototype;

/**
 * Client Class
 */
public class CookieMachine {
 
    private Cookie cookie; // Could have been a private Cloneable cookie.
 
    public CookieMachine(Cookie cookie) {
        this.cookie = cookie;
    }
 
    public Cookie makeCookie() throws CloneNotSupportedException {
        return (Cookie) this.cookie.clone();
    } 
}