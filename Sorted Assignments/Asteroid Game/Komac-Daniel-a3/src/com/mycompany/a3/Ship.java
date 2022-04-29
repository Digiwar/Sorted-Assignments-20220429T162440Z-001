package com.mycompany.a3;

import java.util.LinkedList;

//import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;



public class Ship extends MovingObjects implements IDrawable,ICollider,ISelectable{
	private static Ship Ship = null;
	private int msleCount;
	private int lifeCount;
	private LinkedList<ICollider> collisionObj;
//	private boolean colHandled;
	private static final int MAX_SPEED = 50;
//	private int turn;
	

	private Ship(int i, int j){
//		colHandled = false;
		collisionObj = new LinkedList<ICollider>();
		setLocX(i);
		setLocY(j);
		setSize(50);
		lifeCount=3;
		msleCount=10;
		setDirection(30);
		setSpeed(0);
		setColor(ColorUtil.rgb(0, 255,0));
//		setTurn(0);
	}
	
	
//	private void setTurn(int i) {
//		this.turn = i;
		
//	}


	public static Ship getShip(int i, int j){
		if(Ship==null){
			Ship= new Ship(i,j);
		}
		return Ship;
	}
	
	public int getLife(){
		return lifeCount;
	}
	public void setLife(int l){
		lifeCount -= l;
	}
	
	public void rechargeMsle(){
		msleCount=10;
	}
	

	public void steerable(int dir) {
		
		setDirection(getDirection() + dir);
		if(getDirection()<=0){
			setDirection(getDirection() + 340);
		}else if(getDirection() >= 359){
			setDirection(getDirection() - 340);
		}

	}

	public void firedMsle(){
		msleCount--;
	}

	public void incSpd(){
		if(getSpeed()>=MAX_SPEED){
			System.out.println("Max speed reached");
			setSpeed(MAX_SPEED);
		}
		setSpeed(getSpeed() + 3);
	}
	
	public void decSpd(){

		setSpeed(getSpeed() - 3);
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






	public void draw(Graphics g,Point cmpRelPrnt) {
		g.setColor(getColour());
		int pointX = (int) (getLocX() + cmpRelPrnt.getX());
		int pointY = (int) (getLocY() + cmpRelPrnt.getY());
		int drawingX[] = {(pointX+getSize()/2),pointX,pointX -getSize()/2};
		int drawingY[] = {(pointY+getSize()/2) , pointY-getSize(),pointY +getSize()/2};
		g.drawPolygon(drawingX, drawingY, 3);
		g.setColor(ColorUtil.BLACK);
		g.drawString("Ship", pointX, pointY);
	}


	public boolean detectCollision(ICollider nextObj) {
		
		//Compare right/left of both objects
			if(((GameObjects) nextObj).getLocX()+((GameObjects) nextObj).getSize()/2 >= this.getLocX()+this.getSize()/2||
					((GameObjects) nextObj).getLocX() - ((GameObjects) nextObj).getSize()/2 <= this.getLocX() - this.getSize()/2 && !(nextObj instanceof Asteroid)){
		
				return false;
			}
			//Compare top and bottom of  both items
			if(((GameObjects) nextObj).getLocY()+((GameObjects) nextObj).getSize()/2 >= this.getLocY()+this.getSize()/2||
					((GameObjects) nextObj).getLocY() - ((GameObjects) nextObj).getSize()/2 <= this.getLocY() - this.getSize()/2 && !(nextObj instanceof Asteroid)){
				
				return false;
			
			}
			addColl(nextObj);
			nextObj.addColl(this);
			System.out.println("Collision has happened(1Ship)");
			return true;
		
//		System.out.println("Collision has happened(1Ship)");
//		return true;
	}


	public void handleCollision(Object otherCheck, GameWorld gw) {
			if(gw.getShipCount() == 1&& gw.getAsteroidCount()>=0){
				if(otherCheck instanceof Asteroid){
							if(this instanceof Ship){
								if(getLife()<= 0 && gw.getShipCount()<0){
									System.out.println("There are no more lives.Game Over.");
								}else {
									gw.removeObj(this);			
									gw.setShipCount(gw.getShipCount()-1);
									gw.removeObj((GameObjects) otherCheck);
									gw.setAsteroidCount(gw.getAsteroidCount()-1);
									gw.createShip();
//									setCol(true);
									((ICollider) otherCheck).removeColObj(this);
//									((ICollider) otherCheck).setCol(true);
									removeColObj((ICollider) otherCheck);
									System.out.println("Collision handled");
									
								}
							}
				}
			}
			
//			gw.setChange();
	
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
		// TODO Auto-generated method stub
		
	}


	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		return false;
	}



}
