package com.mycompany.a1;

public abstract class GameObjects implements Imoveable, IfixedObject {
	
	private double locX;
	private double locY;
	private int color;
	
	
	public int getColor(){
		return 0;//FiXXXXX later
	}
	
	public abstract void setColor(int r, int g, int b);
	public abstract int move();
	public abstract int getDir();
	public abstract int getSpeed();
	
	public double getLocX() {
		return locX;
	}

	
	public double getLocY() {
		return locY;
	}


	public void setLocX(double newX) {
		locX= newX;
	}

	public void setLocY(double newY) {
		locY= newY;
	}
}
