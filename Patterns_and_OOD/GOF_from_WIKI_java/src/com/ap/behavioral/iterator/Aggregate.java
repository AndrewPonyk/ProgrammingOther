package com.ap.behavioral.iterator;

abstract class Aggregate
{
    public abstract Iterator CreateIterator();
    protected abstract int count() ;
    public abstract Object getThis(int index);
    public abstract void setThis(int index,Object object);
}

