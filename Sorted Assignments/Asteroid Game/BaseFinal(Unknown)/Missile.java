package com.mycompany.a1;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class Missile extends GameObjects {
	private double locX;
	private double locY;
	private int color;	
	private static int speed;
	private int direction;
	private Random rdm;
	private int fuelLevel;
	
	Missile(){
		//error? or invalid rocket? miss-fire? This should
		//never be used
	}
	
	Missile(double shpX, double shpY, int dir, int spd){
		locX=shpX;
		locY=shpY;
		direction=dir;
		speed = spd +2;
		color = ColorUtil.argb((int).75,100,100,200);
	}
	
	public void setColor(int r, int g, int b) {
		color=ColorUtil.rgb(r,g,b);
		
	}

	@Override
	public int move() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLocX() {
		// TODO Auto-generated method stub
		return locX;
	}

	@Override
	public double getLocY() {
		// TODO Auto-generated method stub
		return locY;
	}


	public void setLocX(double newX) {
		locX= newX;
	}

	public void setLocY(double newY) {
		locY= newY;
	}

	public int getFuelLevel(){
		
		return fuelLevel;
	}
	
	public int getDir(){
		return direction;
	}

	public int getSpeed(){
		return speed;
	}
	@Override
	public int getUnID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
