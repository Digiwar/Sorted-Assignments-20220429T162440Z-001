package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;



public class Asteroid extends MovingObjects {

	
	Asteroid(){
		
		setSize(getRdm().nextInt(30));
		setLocX(getRdm().nextDouble()*1024.0);
		setLocY(getRdm().nextDouble()*768.0);
		setSpeed(getRdm().nextInt(10));
		setDirection(getRdm().nextInt(359));
		setColor(ColorUtil.rgb(255,0,0));
	
	}
	
	public String name(){
		return "Asteroid";
	}
	
}
