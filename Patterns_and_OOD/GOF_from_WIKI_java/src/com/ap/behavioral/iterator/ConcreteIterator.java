package com.ap.behavioral.iterator;

public class ConcreteIterator  extends Iterator{
	
	 private  Aggregate _aggregate;
     private int _current;
	
	public  ConcreteIterator(Aggregate aggregate) {
		this._aggregate = aggregate;
	}

	@Override
	public Object First() {
		return _aggregate.getThis(0);
	}

	@Override
	public Object Next() {
		Object ret = null;
		if (_current < _aggregate.count() - 1)
		{
		    ret = _aggregate.getThis(_current++);
		}
		return ret;
	}

	@Override
	public boolean IsDone() {
		 return _current >= _aggregate.count();
	}

	@Override
	public Object CurrentItem() {
		return _aggregate.getThis(_current);
	}
}