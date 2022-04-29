package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;


public class Asteroid extends GameObjects {
	private double locX;
	private double locY;
	private Random rdm;
	private int color;
	private int speed;
	private int direction;
	private int size;
	
	Asteroid(){
		size= 5;
		locX= rdm.nextDouble()*1024.0;
		locY = rdm.nextDouble()*768.0;
		speed= rdm.nextInt(10);
		direction=rdm.nextInt(359);
		color = ColorUtil.rgb(255,0,0);
	
	}
	
	public void setColor(int r, int g, int b) {
		// TODO Auto-generated method stub
		
	}
	
	public int move() {
		// TODO Auto-generated method stub
		return 0;
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
		
	public int getSpeed(){
		return speed;
	}
	
	@Override
	public int getUnID() {
		// Will never be uniq
		return 0;
	}
	
	public int getDir(){
		return direction;
	}

}
