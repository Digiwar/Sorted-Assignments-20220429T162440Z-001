package com.mycompany.a1;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;


public class Ship extends MovingObjects{
	private static Ship Ship = null;
	private int msleCount;
	private int lifeCount;
	

	private Ship(){
		
		setLocX(512.0);
		setLocY(384.0);
		setSize(10);
		lifeCount=3;
		msleCount=10;
		setDirection(0);
		setSpeed(0);
		setColor(ColorUtil.rgb(0, 255,0));
	}
	
	
	public static Ship getShip(){
		if(Ship==null){
			Ship= new Ship();
		}
		return Ship;
	}
	
	public int getLife(){
		return lifeCount;
	}
	public void setLife(int l){
		lifeCount -= l;
	}
	


	public void setColor(int r, int g, int b) {
		// TODO Auto-generated method stub
		
	}
	

	public void rechargeMsle(){
		msleCount=10;
	}
	

	public void steerable(int dir) {

		setDirection(getDirection() + dir);
		if(getDirection()<=0){
			setDirection(getDirection() + 359);
		}else if(getDirection() >= 359){
			setDirection(getDirection() - 359);
		}

	}

	public void firedMsle(){
		msleCount--;
	}

	public void incSpd(){
		setSpeed(getSpeed() + 1);
	}
	
	public void decSpd(){

		setSpeed(getSpeed() - 1);
		if(getSpeed()<0){
			System.out.println("The ship cannot go any slower.");
			setSpeed(0);
		}else if(getSpeed() == 0){
			System.out.println("The ship has come to a stop.");
		}else{
			System.out.println("The ship has decreased speed.");
		}
		
	}
	
	public String name(){
		return "Ship";
	}
	
	public int getMsleCount(){
		return msleCount;
	}
	




}
