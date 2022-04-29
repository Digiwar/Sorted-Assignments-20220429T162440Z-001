package com.mycompany.a1;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class SpaceStation extends GameObjects {
	private static int unID;
	private double locX;
	private double locY;
	private int color;
	private Random rdm;
	private int speed;
	private int direction;
	private int blinkRate;
	private int msle;
	
	
	SpaceStation(){
		unID = rdm.nextInt();
		locX=rdm.nextDouble()*1024.0;
		locY=rdm.nextDouble()*768.0;
		direction=rdm.nextInt(359);
		speed= 0;
		blinkRate=rdm.nextInt(10);
		msle = rdm.nextInt(100)+1; 
		color=ColorUtil.rgb(0, 0, 255);
		
		
	}


	
	public double getLocX() {
		
		return locX;
	}

	
	public double getLocY() {
		return locY;
	}

	
	public void setLocX(double newX) {
		locX=newX;
	}

	
	public void setLocY(double newY) {
		locY=newY;
	}


	
	public int getUnID() {
		return unID;
	}



	@Override
	public void setColor(int r, int g, int b) {
		// TODO Auto-generated method stub
		
	}
	
	public int getBlinkRate(){
		return blinkRate;
	}
	
	/*public int msleCount(){
	 * 
	 * return msle;
	 * used for calculating the amount of rockets left
	 * for the spaceship, if and when needed for game world.
	 */
	
	public int getDir(){
		return direction;
	}
	
	public int getSpeed(){
		return 0;
	}
	@Override
	public int move() {
		// TODO Auto-generated method stub
		return 0;
	}

}
