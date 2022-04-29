package com.mycompany.a2;

import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

public class GameWorld extends Observable implements IGameWorld{
	
	private static int sizeH = 768;
	private static int sizeW = 1024;
	//private LinkedList<GameObjects> objectCollection;
	private GameObjCollection objectCollection;
	private int score;
	private int gameClock;
	private int asteroidCount;
	private int shipCount;
	private int mslCount;
	private boolean sound;
	private ArrayList<Observer> observers;
	private ScoreView sv = new ScoreView();
	//private ProxyGameWorld proxyGw;
	
	
	
	
	public void init(){

		//proxyGw = new ProxyGameWorld(this);
		objectCollection = new GameObjCollection();
		Ship Player1 = Ship.getShip();
		shipCount = 1;
		objectCollection.add(Player1);
		SpaceStation Station1 = new SpaceStation();
		objectCollection.add(Station1);
		//Initializes game objects/setup
		score = 0;
		asteroidCount=0;
		mslCount = 0 ;
		gameClock=0;
		
		
		setChange();
		
	}
	
	
	public void showBasicInfo(){
		IIterator checker = this.objectCollection.getIterator();
		while(checker.hasNext()){
			if(checker.getNext() instanceof Ship){
				System.out.println("The players score is currently: "+score);
				System.out.println("The ship currently has ["+((Ship)checker).getMsleCount()+"] missiles left.");
				System.out.println("The game has been going on for ["+gameClock+"] ticks.");
		
			}
		}
		setChange();
	}
	
	public void showAllInfo(){
		IIterator reader = objectCollection.getIterator();
		Object checker;
		while(reader.hasNext()){
			checker = reader.getNext();
			
			if(checker instanceof Ship){
				System.out.println(((Ship) checker).name()+": Loc="+(double)Math.round(((GameObjects) checker).getLocX())+", "+(double)Math.round(((GameObjects) checker).getLocY())+" color= "+((GameObjects) checker).getColor()+" speed= "+((MovingObjects) checker).getSpeed()+" dir= "+((MovingObjects) checker).getDirection()+" Missiles= "+((Ship) checker).getMsleCount());
			}
			if(checker instanceof Missile){	
				System.out.println(((Missile) checker).name()+": Loc="+(double)Math.round(((GameObjects) checker).getLocX())+", "+(double)Math.round(((GameObjects) checker).getLocY())+" color="+((GameObjects) checker).getColor()+" speed= "+((MovingObjects) checker).getSpeed()+" dir= "+((MovingObjects) checker).getDirection()+" Fuel= "+((Missile) checker).getFuelLevel());
			}
			if(checker instanceof Asteroid){
				System.out.println(((Asteroid) checker).name()+": Loc="+(double)Math.round(((GameObjects) checker).getLocX())+", "+(double)Math.round(((GameObjects) checker).getLocY())+" color= "+((GameObjects) checker).getColor()+" speed= "+((MovingObjects) checker).getSpeed()+" dir= "+((MovingObjects) checker).getDirection ()+" Size= "+((Asteroid) checker).getSize());
			}
			if(checker instanceof SpaceStation){
				System.out.println(((SpaceStation) checker).name()+": Loc="+(double)Math.round(((GameObjects) checker).getLocX())+", "+(double)Math.round(((GameObjects) checker).getLocY())+" color= "+((GameObjects) checker).getColor()+" Rate= "+((SpaceStation) checker).getBlinkRate());
			}
			
		}
		setChange();
	}
	
	/*Not used in this game mode yet.
	public void rechargeShip(){
		ListIterator<GameObjects> reader = objectCollection.listIterator();
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof SpaceStation|| checker instanceof Ship ){
				while(reader.hasNext()){
					ListIterator<GameObjects> reader2 = objectCollection.listIterator();
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
	*/
	
	
	//Creates ship and only allows one at a time. Singleton is also implemented.
	public void createShip(){

		System.out.println("A Ship is being created");
		if(shipCount < 1){
			objectCollection.add(Ship.getShip());
			shipCount++;
		}else{
			System.out.println("A ship already exsists.");
		}
		setChange();
	}
	
	public void createAsteroid(){
		System.out.println("An Asteroid is being created");
		objectCollection.add(new Asteroid());
		asteroidCount++;
		System.out.println("An Asteroid has been created");
		setChange();
	}
	
	public void createSpaceStation(){
		System.out.println("A Space Station is being created");
		objectCollection.add(new SpaceStation());
		System.out.println("A Space Station has been created");
		setChange();
	}
	
	public void incShipSpd(){
		IIterator reader = objectCollection.getIterator();
		while(reader.hasNext()){
			Object check = reader.getNext();
			if(check instanceof Ship){
				((Ship)check).incSpd();
				System.out.println("The ships speed has increased");
			}
		}
		setChange();
	}
	
	public void fireMsle(){
		IIterator reader = objectCollection.getIterator();
		boolean fired = false;
		if(shipCount == 1){
			while(reader.hasNext()&& fired == false){
				Object check = reader.getNext();
				if(check instanceof Ship){
					if(((Ship)check).getMsleCount() >0){
						objectCollection.add(new Missile(((GameObjects) check).getLocX(),((GameObjects) check).getLocY(),((MovingObjects) check).getDirection(),((MovingObjects) check).getSpeed()));
						reader = objectCollection.getIterator();
						System.out.println("A missile has been fired.");
						((Ship)check).firedMsle();
						//reader.getNext();
						fired = true;
						mslCount++;
					}else if(((Ship)check).getMsleCount()<= 0){
						System.out.println("Ship is out of Missle!");
						//reader.getNext();
						fired = true;
					}
				}
			}
		}else{
			System.out.println("There is no ship for missiles to be fired");
		}
		setChange();
	}
	
	
	public void decShipSpd(){
		IIterator reader = objectCollection.getIterator();
		boolean shipFound = false;
		if(shipCount == 1){
			while(reader.hasNext()&& !(shipFound)){
				Object check = reader.getNext();
				if(check instanceof Ship){
					System.out.println("The ship is decreasing speed");
					((Ship)check).decSpd();
					shipFound = true;
					
				}
			}
		}
		setChange();
	}
	
	public void turnShipLeft(){
		IIterator reader = objectCollection.getIterator();
		boolean shipFound = false;
		if(shipCount == 1){
			while(reader.hasNext()&& !(shipFound)){
				Object check = reader.getNext();
				if(check instanceof Ship){
					System.out.println("The ship is turning left");
					((Ship)check).steerable(-10);
					shipFound = true;
					
				}
			}
		}
		setChange();
	}
	
	public void turnShipRight(){
		IIterator reader = objectCollection.getIterator();
		boolean shipFound = false;
		if(shipCount == 1){
			while(reader.hasNext()&& !(shipFound)){
				Object check = reader.getNext();
				if(check instanceof Ship){
					System.out.println("The ship is turning left");
					((Ship)check).steerable(-10);
					shipFound = true;
					
				}
			}
		}
		setChange();
	}
	
	public void hyperSpace(){
		IIterator reader = objectCollection.getIterator();
		if(shipCount == 1){
			while(reader.hasNext()){
				Object check = reader.getNext();
				if(check instanceof Ship){
					if(((GameObjects) check).getLocX()!= 512 && ((GameObjects) check).getLocY()!= 384){
						((Ship)check).setLocX(512);
						((Ship)check).setLocY(384);
						System.out.println("Ship has jumped through hyperspace");
						
					}else{
						System.out.println("Ship is at this current location.");
					}
				}
			}
		}
		setChange();
	}
	
	public void gMMsleRecharge(){
		IIterator reader = objectCollection.getIterator();
		boolean shipFound = false;
		if(shipCount == 1){
			while(reader.hasNext() && !(shipFound)){
				Object check = reader.getNext();
				if(check instanceof Ship){
					if(((Ship) check).getMsleCount() != 10){
						((Ship)check).rechargeMsle();
						System.out.println("Cheat Activated: Missiles have been refilled");
						shipFound = true;
					}else{
						System.out.println("Ship has max missiles");
						shipFound = true;
					}
					
				}
			}
		}
		setChange();
	}
	
	public void killConfirmed(){
		IIterator reader = objectCollection.getIterator();
		Object checker;
		int flag = 0;
		boolean mslFound = false;
		boolean astFound = false;
		if (asteroidCount >0 && mslCount >0){
			while(reader.hasNext()&& flag <= 2 ){
				checker = reader.getNext();
				
				if(checker instanceof Missile && !(mslFound)){
						reader.remove();
						reader = objectCollection.getIterator();
						flag++;
						mslFound = true;
				}
				if(checker instanceof Asteroid && !(astFound)){
					reader.remove();
					reader = objectCollection.getIterator();
					flag++;
					astFound = true;
				}
			}
		}else{
			if(asteroidCount<0){
				System.out.println("There is no asteroid to destoy...");
			}
			if(mslCount<0){
				System.out.println("No missile is on the field...");
			}
		}
		if(mslFound && !(astFound)){
			System.out.println("Missile has missed target");
		}
		if(flag == 2 && mslFound && astFound){
			System.out.println("Your missile has hit an asteroid.");
			score += 2;
		}
		setChange();
		/*ListIterator<GameObjects> reader = objectCollection.listIterator();
		int flag = 0;
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof Missile){
				 reader = objectCollection.listIterator();
				while(reader.hasNext()){
					if(reader instanceof Asteroid)
						if((checker.getLocX()==reader.next().getLocX())&&(checker.getLocY()== reader.previous().getLocY())){
							reader.remove();
							flag = 1;
						}
				}
				if(flag == 1){
					reader = objectCollection.listIterator();
					while (reader.hasNext()){
						if(reader.next().equals(checker)){
								reader.remove();
								score += 2;
						}
					}
				}
			}
			
		}*/ 
	}
	
	public void crash(){
		IIterator reader = objectCollection.getIterator();
		Object checker;
		int flag = 0;
		boolean shipFound= false;
		boolean astFound= false;
		if(shipCount == 1){
			while(reader.hasNext()&& flag <= 2 ){
				checker = reader.getNext();
				
				if(checker instanceof Ship && !(shipFound)){
						if(checker instanceof Ship){
							if(((Ship)checker).getLife()<= 0 && shipCount<0){
								System.out.println("There are no more lives.Game Over.");
							}else {
								reader.remove();
								reader = objectCollection.getIterator();
								flag++;
								shipCount --;
								shipFound=true;
							}
						}
				}
				if(checker instanceof Asteroid && !(astFound)){
					astFound = true;
					reader.remove();
					reader = objectCollection.getIterator();
					flag++;
					
				}
			}
		}
		
		if(shipCount <=0){
			objectCollection.add(Ship.getShip());
		}
			
		if(flag == 2){
			System.out.println("Your ship has crashed into an asteroid.");
		}
		setChange();
		
	}
		/*ListIterator<GameObjects> reader = objectCollection.listIterator();
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof Ship){
				 reader = objectCollection.listIterator();
				while(reader.hasNext()){
					if(reader instanceof Asteroid)
						if((checker.getLocX()==reader.next().getLocX())&&(checker.getLocY()== reader.previous().getLocY())){
							reader.remove();
							if(((Ship)checker).getLife() <=0 )
							{
								System.out.println("There are no more lives. Game is over");
								
							}else{
								objectCollection.removeFirst();
								objectCollection.addFirst(Ship.getShip());
							}
						}
				}
			}
		}*/
	
	
	//Asteroid collision between Asteroids
	public void asteroidCollison(){
		IIterator reader = objectCollection.getIterator();
		Object checker;
		int flag = 0;
		if(asteroidCount >= 2){
		while(reader.hasNext()&& flag <= 2 ){
			checker = reader.getNext();
			
			if(checker instanceof Asteroid){
				reader.remove();
				reader = objectCollection.getIterator();
				
			}
			
		}

		}
		if (flag<2){
			System.out.println("Not enough asteroids for a collison.");
		}
		if(flag == 2){
			System.out.println("Two asteroids have collided.");
		}
		setChanged();
		/*ListIterator<GameObjects> reader = objectCollection.listIterator();
		int flag = 0;
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof Asteroid){
				reader = objectCollection.listIterator();
				while(reader.hasNext()){
					if(reader instanceof Asteroid)
						if((checker.getLocX()==reader.next().getLocX())&&(checker.getLocY()== reader.previous().getLocY())){
							reader.remove();
							flag = 1;
						}
				}
				if(flag == 1){
					reader = objectCollection.listIterator();
					while (reader.hasNext()){
						if(reader.next().equals(checker)){
								reader.remove();
						}
					}
				}
			}*/
			
		}

	
	public void ticked(){
		gameClock++;
		IIterator reader = objectCollection.getIterator();
		Object checker;
		while(reader.hasNext()){
			checker = reader.getNext();
			
			if(checker instanceof Asteroid){
				((Asteroid)checker).move();
				System.out.println("Asteroid has moved.");
			}
			if(checker instanceof Missile){
				int chk = ((Missile) checker).getFuelLevel();
				if(chk <= 0){
					System.out.println("Missile has missed.");
					reader.remove();
					reader = objectCollection.getIterator();
				}else{
					((Missile)checker).decFuel();
					((Missile)checker).move();	
					System.out.println("Missile has moved.");
				}
			}
			if(checker instanceof Ship){
				((Ship)checker).move();
				System.out.println("Ship has moved.");
			}
			if(checker instanceof SpaceStation){
				((SpaceStation) checker).incBlinkCount();
				System.out.println("SpaceStation has blinked");
			}
			
		}
		System.out.println("The game time is: "+gameClock);
		setChange();
		
	}
	
	public void setSound(boolean sound){
		this.sound = sound;
		setChange();
	}
	
	public void setChange(){
		setChanged();
		notifyObservers();
	}


	public boolean getSound() {
		
		return this.sound;
	}


	public int getScore() {
		
		return this.score;
	}


	public int getGameClock() {
		
		return this.gameClock;
	}
	
	public int shipMslCount(){
		IIterator shipFinder = objectCollection.getIterator();
		Object checker = shipFinder.getNext();
		while(shipFinder.hasNext()&&!(checker instanceof Ship)){
			checker = shipFinder.getNext();
		}
		if(!(checker instanceof Ship))
			return ((Integer)null);
		setChanged();
		return ((Ship)checker).getMsleCount();
	}
	//This may be completely out of place in here since ticked handles missleDistance with
	//Association with speed
	/*public void missleDistance(){
		
		//calculate how far a missile will travel with fuel level
	}*/
	//More methods
}