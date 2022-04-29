package com.mycompany.a3;

import java.util.LinkedList;

//import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;


public class Asteroid extends MovingObjects implements ICollider,IDrawable,ISelectable{
	private LinkedList<ICollider> collisionObj;
	private boolean colHandled;
	
	Asteroid(int x, int y){
		collisionObj = new LinkedList<ICollider>();
		setSize(getRdm().nextInt(25)+10);
		setLocX(getRdm().nextDouble()*x);
		setLocY(getRdm().nextDouble()*y);
		setSpeed(getRdm().nextInt(10)+5);
		setDirection(getRdm().nextInt(359));
		setColor(ColorUtil.rgb(255,0,0));
		colHandled = false;
	
	}
	
	public String name(){
		return "Asteroid";
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColour());
		int drawingX = (int)getLocX() + pCmpRelPrnt.getX();
		int drawingY = (int)getLocY() + pCmpRelPrnt.getY();
		g.fillRect(drawingX, drawingY, getSize(), getSize());
		g.drawString("Asteroid", drawingX, drawingY);
	}

	public boolean detectCollision(ICollider nextObj) {
		if(!(collisionObj.contains(nextObj))){
			//Compare right/left of both objects
			if(((GameObjects) nextObj).getLocX()+((GameObjects) nextObj).getSize()/2 >= this.getLocX()+this.getSize()/2||
					((GameObjects) nextObj).getLocX() - ((GameObjects) nextObj).getSize()/2 <= this.getLocX() - this.getSize()/2){
//				setCol(false);
				return false;
			}
			//Compare top and bottom of  both items
			if(((GameObjects) nextObj).getLocY()+((GameObjects) nextObj).getSize()/2 >= this.getLocY()+this.getSize()/2||
					((GameObjects) nextObj).getLocY() - ((GameObjects) nextObj).getSize()/2 <= this.getLocY() - this.getSize()/2){
//				setCol(false);
				
				return false;
			}
			addColl(nextObj);
			nextObj.addColl(this);
			System.out.println("Collision has happened(2Ast)");
			return true;
		}
		System.out.println("Collision has happened(2Asteroid)");
		return true;
	}

	public void handleCollision(Object otherCheck,GameWorld gw) {
		if(gw.getShipCount() == 1&& gw.getAsteroidCount()>=1){
			if(otherCheck instanceof Ship){
						if(this instanceof Asteroid){
							if(gw.getShipLife()<= 0 && gw.getShipCount()<0){
								System.out.println("There are no more lives.Game Over.");
							}else {
								gw.removeObj(this);			
								gw.setAsteroidCount(gw.getAsteroidCount()-1);
								gw.removeObj((GameObjects) otherCheck);
								gw.setShipCount(gw.getAsteroidCount()-1);
								gw.createShip();
//								setCol(true);
								((ICollider) otherCheck).removeColObj(this);
//								((ICollider) otherCheck).setCol(true);
								removeColObj((ICollider) otherCheck);
								System.out.println("Collision(Ast) handled");
								
							}
						}
			}
		}
		if(gw.getMslCount()>=1 && gw.getAsteroidCount()>=1){
			if(otherCheck instanceof Missile){
				gw.setMslCount(gw.getMslCount()-1);
				gw.setAsteroidCount(gw.getAsteroidCount()-1);
//				setCol(true);
//				((ICollider)otherCheck).setCol(true);
				removeColObj((ICollider) otherCheck);
				((ICollider) otherCheck).removeColObj(this);
				gw.removeObj((GameObjects) otherCheck);
				gw.removeObj(this);
				
				System.out.println("Collision(Ast) handled");
			}
		}
		if(gw.getAsteroidCount()>=2){
			if(this instanceof Asteroid){
				if(otherCheck instanceof Asteroid && !(colHandled)){
					gw.setAsteroidCount(gw.getAsteroidCount()-2);
//					setCol(true);
//					((ICollider)otherCheck).setCol(true);
					removeColObj((ICollider) otherCheck);
					((ICollider) otherCheck).removeColObj(this);
					gw.removeObj((GameObjects) otherCheck);
					gw.removeObj(this);
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


	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		return false;
	}

	public void selected(boolean selected) {
		// TODO Auto-generated method stub
		
	}


}
