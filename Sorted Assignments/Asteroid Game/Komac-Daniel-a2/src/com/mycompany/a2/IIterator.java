package com.mycompany.a2;

public interface IIterator {
	public boolean hasNext();
	
	public Object getNext();

	public Object previous();

	public void remove();
	
}
