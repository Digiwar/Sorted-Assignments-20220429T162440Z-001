package com.mycompany.a1;

public abstract class MovingObjects extends GameObjects implements Imoveable {
	private int speed;
	private int direction;
	private int theta;
	
	

	MovingObjects(){
		setSpeed(0);
		setDirection(0);
	}
	public void move() {
		theta = 90 - getDirection();
		setLocX(getLocX() + Math.cos(Math.round(Math.toRadians(theta))*getSpeed()));
		setLocY(getLocY() + Math.sin(Math.round(Math.toRadians(theta))*getSpeed()));
		while(getLocX()>1024.0||getLocX()<0.0||getLocY()<0.0||getLocY()>768.0){
			System.out.println("An object is outta bound. Fixing...");
			move();
		}
		
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}

}
