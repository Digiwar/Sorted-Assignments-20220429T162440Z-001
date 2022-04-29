package com.mycompany.a3;

//import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;

public abstract class GameObjects implements ISelectable {
	/*
	 * All the game Objects that are collected in the Game
	 * Worlds linked list are created with these variables.
	 */
	private double locX;
	private double locY;
	private int color;
	private Random rdm;
	private int size;
	private boolean selected;
	/*
	 * All Game Objects have sizes,locations for x and y coordinates
	 * (x and y will be used later when graphics are used) and a color. 
	 * Along with having these variables all objects(everything inheriting 
	 * gameObjects) will have a random variable allowing for random information
	 * to be stored
	 */
	GameObjects(){
		selected = false;
		setSize(0);
		setRdm(new Random());
		locX = 0;
		locY = 0;
		setColor(0);
		
	}
	/*
	 * Game objects have setters and getters so that subsequent methods 
	 * can obtain information 
	 */
	
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	
	
	public int getColour(){
		return color;
	}
	
	
	
	public String getColor(){
		String myColor;
		return myColor = "["+ColorUtil.red(color)+", "+ColorUtil.green(color)+", "+ColorUtil.blue(color)+" ]";
	}
	
	
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
	public void setColor(int color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Random getRdm() {
		return rdm;
	}

	public void setRdm(Random rdm) {
		this.rdm = rdm;
	}
	
	public void select(){
		this.selected = !(this.selected);
	}
}
