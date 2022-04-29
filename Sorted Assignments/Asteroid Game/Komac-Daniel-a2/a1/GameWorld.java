package com.mycompany.a1;

import java.util.LinkedList;
import java.util.ListIterator;

public class GameWorld {
	
	private static int sizeH = 768;
	private static int sizeW = 1024;
	private LinkedList<GameObjects> gameObjs;
	private int score;
	private int gameClock;
	private int asteroidCount;
	private int shipCount;
	
	
	
	public void init(){
		gameObjs = new LinkedList<GameObjects>();
		Ship Player1 = Ship.getShip();
		shipCount ++;
		gameObjs.add(Player1);
		SpaceStation Station1 = new SpaceStation();
		gameObjs.add(Station1);
		//Initializes game objects/setup
		score = 0;
		
	}
	
	
	public void showBasicInfo(){
		GameObjects check = gameObjs.getFirst();
		
		System.out.println("The players score is currently: "+score);
		System.out.println("The ship currently has ["+((Ship)check).getMsleCount()+"] missiles left.");
		System.out.println("The game has been going on for ["+gameClock+"] ticks.");
		
	}
	
	public void showAllInfo(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		GameObjects checker;
		while(reader.hasNext()){
			checker = reader.next();
			
			if(checker instanceof Ship){
				System.out.println(((Ship) checker).name()+": Loc="+(double)Math.round(checker.getLocX())+", "+(double)Math.round(checker.getLocY())+" color= "+checker.getColor()+" speed= "+((MovingObjects) checker).getSpeed()+" dir= "+((MovingObjects) checker).getDirection()+" Missiles= "+((Ship) checker).getMsleCount());
			}
			if(checker instanceof Missile){	
				System.out.println(((Missile) checker).name()+": Loc="+(double)Math.round(checker.getLocX())+", "+(double)Math.round(checker.getLocY())+" color="+checker.getColor()+" speed= "+((MovingObjects) checker).getSpeed()+" dir= "+((MovingObjects) checker).getDirection()+" Fuel= "+((Missile) checker).getFuelLevel());
			}
			if(checker instanceof Asteroid){
				System.out.println(((Asteroid) checker).name()+": Loc="+(double)Math.round(checker.getLocX())+", "+(double)Math.round(checker.getLocY())+" color= "+checker.getColor()+" speed= "+((MovingObjects) checker).getSpeed()+" dir= "+((MovingObjects) checker).getDirection ()+" Size= "+((Asteroid) checker).getSize());
			}
			if(checker instanceof SpaceStation){
				System.out.println(((SpaceStation) checker).name()+": Loc="+(double)Math.round(checker.getLocX())+", "+(double)Math.round(checker.getLocY())+" color= "+checker.getColor()+" Rate= "+((SpaceStation) checker).getBlinkRate());
			}
			
		}
	}
	
	/*Not used in this game mode yet.
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
	*/
	
	public void createSpaceStation(){
		System.out.println("A Space Station is being created");
		if(shipCount < 1){
			gameObjs.add(new SpaceStation());
			System.out.println("A Space Station has been created");
			shipCount++;
		}else{
			System.out.println("A ship already exsists.");
		}
		}
	
	public void createAsteroid(){
		System.out.println("An Asteroid is being created");
		gameObjs.add(new Asteroid());
		asteroidCount++;
		System.out.println("An Asteroid has been created");
	}
	
	public void createShip(){
		System.out.println("A Ship is being created");
		gameObjs.add(Ship.getShip());
		System.out.println("A Ship has been created");
	}
	
	public void incShipSpd(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).incSpd();
				System.out.println("The ships speed has increased");
				reader.next();
			}
		}
		
	}
	
	public void fireMsle(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		if(shipCount == 1){
			while(reader.hasNext()){
				if(reader.next() instanceof Ship){
					GameObjects check = reader.previous();
					if(((Ship)check).getMsleCount() >0){
						gameObjs.add(new Missile(check.getLocX(),check.getLocY(),((MovingObjects) check).getDirection(),((MovingObjects) check).getSpeed()));
						reader = gameObjs.listIterator();
						System.out.println("A missile has been fired.");
						((Ship)check).firedMsle();
						reader.next();
					}else if(((Ship)check).getMsleCount()<= 0){
						System.out.println("Ship is out of Missle!");
						reader.next();
					}
				}
			}
		}else{
			System.out.println("There is no ship for missiles to be fired");
		}
	}
	
	
	public void decShipSpd(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		if(shipCount == 1){
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				System.out.println("The ship is decreasing speed");
				((Ship)check).decSpd();
				
				reader.next();
			}
		}
		}
		
	}
	
	public void turnShipLeft(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		if(shipCount == 1){
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).steerable(-10);
				System.out.println("The ship has turned left.");
				reader.next();
			}
		}
		}
	}
	
	public void turnShipRight(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		if(shipCount == 1){
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				((Ship)check).steerable(10);
				System.out.println("The ship has turned right");
				reader.next();
			}
		}
		}
	}
	
	public void hyperSpace(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		if(shipCount == 1){
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				if(check.getLocX()!= 512 && check.getLocY()!= 384){
					((Ship)check).setLocX(512);
					((Ship)check).setLocY(384);
					System.out.println("Ship has jumped through hyperspace");
					reader.next();
					
				}else{
					System.out.println("Ship is at this current location.");
					reader.next();
				}
			}
		}
		}
	}
	
	public void gMMsleRecharge(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		if(shipCount == 1){
		while(reader.hasNext()){
			if(reader.next() instanceof Ship){
				GameObjects check= reader.previous();
				if(((Ship) check).getMsleCount() != 10){
					((Ship)check).rechargeMsle();
					System.out.println("Cheat Activated: Missiles have been refilled");
					reader.next();
				}else{
					System.out.println("Ship has max missiles");
					reader.next();
				}
				
			}
		}
		}
	}
	
	public void killConfirmed(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		GameObjects checker;
		int flag = 0;
		while(reader.hasNext()&& flag <= 2 ){
			checker = reader.next();
			
			if(checker instanceof Missile || checker instanceof Asteroid){
					reader.remove();
					reader = gameObjs.listIterator();
					flag++;
			}
			
		}
		if(flag == 2){
			System.out.println("Your missile has hit an asteroid.");
			score += 2;
		}
		/*ListIterator<GameObjects> reader = gameObjs.listIterator();
		int flag = 0;
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof Missile){
				 reader = gameObjs.listIterator();
				while(reader.hasNext()){
					if(reader instanceof Asteroid)
						if((checker.getLocX()==reader.next().getLocX())&&(checker.getLocY()== reader.previous().getLocY())){
							reader.remove();
							flag = 1;
						}
				}
				if(flag == 1){
					reader = gameObjs.listIterator();
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
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		GameObjects checker;
		int flag = 0;
		if(shipCount == 1){
		while(reader.hasNext()&& flag <= 2 ){
			checker = reader.next();
			
			if(checker instanceof Ship || checker instanceof Asteroid){
					if(checker instanceof Ship){
						if(((Ship)checker).getLife()<= 0){
							System.out.println("There are no more lives.Game Over.");
						}else {
							reader.remove();
							reader = gameObjs.listIterator();
							flag++;
							shipCount --;
						}
					}else{
					reader.remove();
					reader = gameObjs.listIterator();
					flag++;
					}
			}
			
		}
		if(shipCount <=0){
			gameObjs.add(Ship.getShip());
		}
			
		if(flag == 2){
			System.out.println("Your ship has crashed into an asteroid.");
		}
		
		}
		/*ListIterator<GameObjects> reader = gameObjs.listIterator();
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof Ship){
				 reader = gameObjs.listIterator();
				while(reader.hasNext()){
					if(reader instanceof Asteroid)
						if((checker.getLocX()==reader.next().getLocX())&&(checker.getLocY()== reader.previous().getLocY())){
							reader.remove();
							if(((Ship)checker).getLife() <=0 )
							{
								System.out.println("There are no more lives. Game is over");
								
							}else{
								gameObjs.removeFirst();
								gameObjs.addFirst(Ship.getShip());
							}
						}
				}
			}
		}*/
		
	}
	
	
	//Asteroid collision between Asteroids
	public void asteroidCollison(){
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		GameObjects checker;
		int flag = 0;
		if(asteroidCount >= 2){
		while(reader.hasNext()&& flag <= 2 ){
			checker = reader.next();
			
			if(checker instanceof Asteroid){
				reader.remove();
				reader = gameObjs.listIterator();
				
			}
			
		}
		if(flag == 2){
			System.out.println("Two asteroids have collided.");
		}
		}
		/*ListIterator<GameObjects> reader = gameObjs.listIterator();
		int flag = 0;
		while(reader.hasNext()){
			GameObjects checker = reader.next();
			
			if(checker instanceof Asteroid){
				reader = gameObjs.listIterator();
				while(reader.hasNext()){
					if(reader instanceof Asteroid)
						if((checker.getLocX()==reader.next().getLocX())&&(checker.getLocY()== reader.previous().getLocY())){
							reader.remove();
							flag = 1;
						}
				}
				if(flag == 1){
					reader = gameObjs.listIterator();
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
		ListIterator<GameObjects> reader = gameObjs.listIterator();
		GameObjects checker;
		while(reader.hasNext()){
			checker = reader.next();
			
			if(checker instanceof Asteroid){
				((Asteroid)checker).move();
				System.out.println("Asteroid has moved.");
			}
			if(checker instanceof Missile){
				int chk = ((Missile) checker).getFuelLevel();
				if(chk <= 0){
					System.out.println("Missile has missed.");
					gameObjs.remove();
					reader = gameObjs.listIterator();
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
		
	}
	//This may be completely out of place in here since ticked handles missleDistance with
	//Association with speed
	/*public void missleDistance(){
		
		//calculate how far a missile will travel with fuel level
	}*/
	//More methods
}
