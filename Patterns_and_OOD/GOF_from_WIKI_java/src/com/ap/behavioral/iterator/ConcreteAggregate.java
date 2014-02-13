package com.ap.behavioral.iterator;

import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteAggregate extends Aggregate
{
    //private  ArrayList _items = new ArrayList();
    private HashMap<Object, Object> _items = new HashMap<Object, Object>();
    public  Iterator CreateIterator()
    {
        return new ConcreteIterator(this);
    }

	@Override
	public Object getThis(int index) {
		return _items.get(index);
	}

	@Override
	public void setThis(int index, Object object) {
		//_items.set(index, aggregate);
		_items.put(index, object);
	}

	@Override
	protected int count() {
		return _items.size();
	}
}