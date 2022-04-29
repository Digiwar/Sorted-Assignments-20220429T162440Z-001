package com.mycompany.a3;

import java.util.LinkedList;

//import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class Missile extends MovingObjects implements IDrawable,ISelectable,ICollider{
	//private double locX;
	//private double locY;
	//private int color;	
	//private static int speed;
	//private int direction;
	private int fuelLevel;
	private LinkedList<ICollider> collisionObj;
	private boolean selected;
//	private boolean colHandled;
	/*Missile(){
		//error? or invalid rocket? miss-fire? This should
		//never be used
	}*/
	
	Missile(double shpX, double shpY, int dir, int spd){
//		colHandled = false;
		setSize(25);
		setLocX(shpX+30);
		setLocY(shpY+30);
		setDirection(dir);
		setSpeed(spd + 10);
		fuelLevel = 250;
		setColor(ColorUtil.argb((int).75,100,100,200));
		collisionObj = new LinkedList<ICollider>();
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


	@Override
	public void draw(Graphics g, Point cmpRelPrnt) {
		g.setColor(getColour());
		
		int x = (int)getLocX() + cmpRelPrnt.getX();
		int y = (int)getLocY() + cmpRelPrnt.getY();
		if(selected)
			g.drawRect(x, y, getSize()/2, getSize()/2);
		else
			g.fillRect(x, y, getSize()/2, getSize());
			
	}

	public boolean detectCollision(ICollider nextObj) {
		
			//Compare right/left of both objects
			if(((GameObjects) nextObj).getLocX()+((GameObjects) nextObj).getSize()/2 >= this.getLocX()+this.getSize()/2||
					((GameObjects) nextObj).getLocX() - ((GameObjects) nextObj).getSize()/2 <= this.getLocX() - this.getSize()/2&& !(nextObj instanceof Asteroid)){
				return false;
			}
			//Compare top and bottom of  both items
			if(((GameObjects) nextObj).getLocY()+((GameObjects) nextObj).getSize()/2 >= this.getLocY()+this.getSize()/2||
					((GameObjects) nextObj).getLocY() - ((GameObjects) nextObj).getSize()/2 <= this.getLocY() - this.getSize()/2 && !(nextObj instanceof Asteroid)){
				return false;
			}
			addColl(nextObj);
			nextObj.addColl(this);
			System.out.println("Collision has happened(3Mis)");
			return true;
	}
	
	
	
	public void handleCollision(Object otherCheck,GameWorld gw) {

		if(gw.getMslCount()>=1&&gw.getAsteroidCount()>=1){
			if(otherCheck instanceof Asteroid){
				if(this instanceof Missile){
					removeColObj((ICollider)otherCheck);
					((ICollider) otherCheck).removeColObj(this);
//					setCol(true);
//					((ICollider)otherCheck).setCol(true);
					gw.removeObj(((GameObjects)otherCheck));
					gw.removeObj(this);
					gw.setAsteroidCount(gw.getAsteroidCount()-1);
					gw.setMslCount(gw.getMslCount()-1);
					
					System.out.println("Collision(Mis) Handle");
				}
			}
		}
	}
	
	public void addColl(ICollider x) {
		collisionObj.add(x);
	}
	
//	public void setCol(boolean colDec){
//		this.colHandled = colDec;
//	}

	public void removeColObj(ICollider x) {
		collisionObj.remove(x);		
	}

	public void selected(boolean selected) {
		this.selected=selected; 
		System.out.println("Missile Selected");
	}

	public boolean isSelected() {

		return selected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX();
		int py = pPtrRelPrnt.getY();
		int relLocX = (int) (pCmpRelPrnt.getX()+getLocX());
		int relLocY = (int) (pCmpRelPrnt.getY()+getLocY());
		
		if((px>=relLocX )&& (px <=relLocX +getSize()/2)&& (py>=relLocY)&&(py<= relLocY+getSize()/2))
			return true;
		else
			return false;
	}

	public void setFuel(int i) {
		fuelLevel = i;
		
	}

}
