package com.mycompany.a1;

import java.util.LinkedList;
import java.util.ListIterator;

public class GameWorld {
	
	private static int sizeH = 768;
	private static int sizeW = 1024;
	private LinkedList<GameObjects> gameObjs;
	
	
	public void init(){
		Ship Player1 = Ship.getShip();
		gameObjs.add(Player1);
		SpaceStation Station1 = new SpaceStation();
		gameObjs.add(Station1);
		//Initializes game objects/setup
		
	}
	public void rechargeShip(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof SpaceStation|| checker instanceof Ship ){
				while(reader.hasNext()){
					ListIterator<GameObjects> reader2 = gameObjs.listIterator();
					if((checker.getLocX()==reader2.next().getLocX())&&(checker.getLocY()== reader2.previous().getLocY())){
						if(checker instanceof Ship){
							((Ship) checker).rechargeMsle();
						}else if(reader2.next() instanceof Ship){
							checker = reader2.previous();
							((Ship)checker).rechargeMsle();
						}
					}
				}
			}
		}
		//re-charge ship msleCount to max == 10
	}
	
	public void createSpaceStation(){
		gameObjs.add(new SpaceStation());
	}
	
	public void createAsteroid(){
		gameObjs.add(new Asteroid());
	}
	
	public void createShip(){
		gameObjs.add(Ship.getShip());
	}
	
	public void incShipSpd(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).incSpd();
			}
		}
		
	}
	
	public void fireMsle(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check = reader.previous();
				if(((Ship)check).getMsleCount() >0){
					gameObjs.add(new Missile(check.getLocX(),check.getLocY(),check.getDir(),check.getSpeed()));
					((Ship)check).fireMsle();
				}else if(((Ship)check).getMsleCount()<= 0){
					System.out.println("Ship is out of Missle!");
				}
			}
		}
	}
	
	
	public void decShipSpd(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).decSpd();
			}
		}
		
	}
	
	public void turnShipLeft(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).steerable(-10);
			}
		}
	}
	
	public void turnShipRight(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).steerable(10);
			}
		}
	}
	
	public void hyperSpace(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).setLocX(512);
				((Ship)check).setLocY(384);
			}
		}
	}
	
	public void gMMsleRecharge(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).rechargeMsle();
			}
		}
	}
	
	public void killConfirmed(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof Missile){
				ListIterator<GameObjects> reader2 = gameObjs.listIterator();
				while(reader2.hasNext()){
					if(reader2 instanceof Asteroid)
					if((checker.getLocX()==reader.next().getLocX())&&(checker.getLocY()== reader.previous().getLocY())){
						if(checker instanceof Ship){
							((Ship) checker).rechargeMsle();
						}else if(reader.next() instanceof Ship){
							checker = reader.previous();
							((Ship)checker).rechargeMsle();
						}
					}
				}
			}
		}
	}
	
	public void asteroidCollison(){
		//Ship collision with asteroid
	}
	
	public void asteroidDestroy(){
		//Asteroid hit by missile
	}
	
	public void missleDistance(){
		
		//calculate how far a missile will travel with fuel level
	}
	//More methods
}
