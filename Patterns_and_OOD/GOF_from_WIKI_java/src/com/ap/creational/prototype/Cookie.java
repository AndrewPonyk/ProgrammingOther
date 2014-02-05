package com.ap.creational.prototype;

/**
 * Prototype Class
 */
public class Cookie implements Cloneable {
    protected int weight;
 
    @Override
    public Cookie clone() throws CloneNotSupportedException {
        Cookie copy = new Cookie();
        copy.weight = this.weight;
 
        //In an actual implementation of this pattern you might now change references to
        //the expensive to produce parts from the copies that are held inside the prototype.
        return copy;
    }
}
