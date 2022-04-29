package com.mycompany.a2;


public abstract class FixedObjects extends GameObjects implements IfixedObject {

	private int unID;
	
	FixedObjects(){
		setUnID(getRdm().nextInt(100));
	}

	public int getUnID() {
		return unID;
	}

	public void setUnID(int unID) {
		this.unID = unID;
	}
	
	
}
