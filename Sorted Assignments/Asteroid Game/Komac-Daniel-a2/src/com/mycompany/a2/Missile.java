package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Missile extends MovingObjects{
	//private double locX;
	//private double locY;
	//private int color;	
	//private static int speed;
	//private int direction;
	private int fuelLevel;
	
	/*Missile(){
		//error? or invalid rocket? miss-fire? This should
		//never be used
	}*/
	
	Missile(double shpX, double shpY, int dir, int spd){
		
		setLocX(shpX);
		setLocY(shpY);
		setDirection(dir);
		setSpeed(spd +2);
		fuelLevel = 10;
		setColor(ColorUtil.argb((int).75,100,100,200));

	}

	public int getFuelLevel(){
		
		return fuelLevel;
	}
	
	public void decFuel(){
		if(fuelLevel > 0){
			fuelLevel -=1;
		}
	}

	public String name(){
		return "Missile";
	}

}
