package com.mycompany.a3;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public abstract class MovingObjects extends GameObjects implements Imoveable {
	private int speed;
	private int direction;
	private int theta;
//	private int turn;
	
	

	MovingObjects(){
		setSpeed(0);
		setDirection(0);
//		setTurn(0);
	}

	public void move(Dimension gwSize) {
		theta = 90 - this.getDirection();
		if(this.getSpeed()>0){
			this.setLocX(this.getLocX()+ Math.cos(Math.round((Math.toRadians(theta))*this.getSpeed())));
			this.setLocY(this.getLocY()+ Math.sin(Math.round((Math.toRadians(theta))*this.getSpeed())));
//			this.setDirection(this.getDirection()+getMainDir());
		}
		if(getLocX()>gwSize.getWidth()||getLocX()<0.0||getLocY()<0.0||getLocY()>gwSize.getHeight()){
//			System.out.println("An object is outta bound. Fixing...");
			setDirection(this.getDirection()-178);
			move(gwSize);
		}
//		System.out.println("Fixed");
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}

}
