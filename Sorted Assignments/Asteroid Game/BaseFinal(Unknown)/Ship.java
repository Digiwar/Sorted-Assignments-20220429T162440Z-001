package com.mycompany.a1;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;


public class Ship extends GameObjects{
	private static Ship Ship = null;
	private double locX;
	private double locY;
	private int color;
	private int speed;
	private int direction;
	private Random rdm;
	private int msleCount;
	private int lifeCount;
	

	private Ship(){
		
		locX=512.0;
		locY=384.0;
		lifeCount=3;
		msleCount=10;
		direction=0;
		speed = 0;
		color = ColorUtil.rgb(0, 255,0);
	}
	
	public static Ship getShip(){
		if(Ship==null){
			Ship= new Ship();
		}
		return Ship;
	}
	
	public int move() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setColor(int r, int g, int b) {
		// TODO Auto-generated method stub
		
	}
	
	public int getDir(){
		return direction;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public int getColor(){
		return color;
	}

	public void rechargeMsle(){
		msleCount=10;
	}
	

	public void steerable(int dir) {

		direction += dir;
		if(direction<=0){
			direction += 359;
		}else if(direction >= 359){
			direction -= 359;
		}

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
	public void fireMsle(){
		msleCount--;
	}

	public void incSpd(){
		speed = speed + 1;
	}
	
	public void decSpd(){

		speed = speed - 1;
		if(speed<=0){
			speed = 0;
		}
		
	}
	
	public int getMsleCount(){
		return msleCount;
	}
	
	@Override
	public int getUnID() {
		//NO UNID MAYBE TWO PLAYER THATS WHY!!
		return 0;
	}




}
