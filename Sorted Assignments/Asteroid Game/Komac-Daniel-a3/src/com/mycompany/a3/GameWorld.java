package com.mycompany.a3;

import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class GameWorld extends Observable implements IGameWorld{
	
	private static int SIZE_H;
	private static int SIZE_W;
//	private LinkedList<GameObjects> objectCollection;
	private GameObjCollection objectCollection;
	private int score;
//	private int gameClock;
	private int asteroidCount;
	private int shipCount;
	private int mslCount;
	private boolean sound;
	private ArrayList<Observer> observers;
	private ScoreView sv = new ScoreView();
	private Sound missileHit;
	private Sound astCol;
	private SoundBackground bgSong;
	private Sound shipHit;
	private Sound fireMsl;
	private Sound gameOver;
//	private ProxyGameWorld proxyGw;

	
	
	
	
	
	public void init(){
//		SIZE_H=lENGTH_Y;
//		SIZE_W=lENGTH_X;
		//proxyGw = new ProxyGameWorld(this);
		objectCollection = new GameObjCollection();
		Ship Player1 = Ship.getShip(1078/2,738/2);
		setShipCount(1);
		objectCollection.add(Player1);
		SpaceStation Station1 = new SpaceStation();
		objectCollection.add(Station1);
		//Initializes game objects/setup
		
		score = 0;
		setAsteroidCount(0);
		setMslCount(0) ;
//		gameClock=0;
		setShipHitSound(new Sound("shipHit.wav"));
		setMissileHitSound(new Sound("mslHit.wav"));
		setGameOverSound(new Sound("GameOver.wav"));
		setBackGroundSound(new SoundBackground("Ambient.mp3"));
		setAsteroidCollisionSound(new Sound("astCol.wav"));
		setFireSound(new Sound("fireMsl.wav"));
		
		
		setChange();
		
	}
	//Sound setters and getters to allocated our sound variables and use them.
	
	private Sound getFireSound(){
		return fireMsl;
	}
	
	private void setFireSound(Sound sound2) {
		fireMsl = sound2;
		
	}

	private Sound getAsteroidCollisionSound(){
		return astCol;
	}

	private void setAsteroidCollisionSound(Sound sound2) {
		astCol= sound2;
	}
	
	private SoundBackground getBackGroundSound(){
		return bgSong;
	}

	private void setBackGroundSound(SoundBackground soundBackground) {
		bgSong = soundBackground;
	}

	private Sound getGameOverSound(){
		return gameOver;
	}

	private void setGameOverSound(Sound sound2) {
		gameOver = sound2;
	}
	
	private Sound getMissileHitSound(){
		return missileHit;
	}

	private void setMissileHitSound(Sound sound2) {
		missileHit = sound2;
	}
	
	
	private Sound getShipHitSound(){
		return shipHit;
	}

	private void setShipHitSound(Sound sound2) {
		shipHit = sound2;
	}


	public void showBasicInfo(){
		IIterator checker = this.objectCollection.getIterator();
		while(checker.hasNext()){
			if(checker.getNext() instanceof Ship){
				System.out.println("The players score is currently: "+score);
				System.out.println("The ship currently has ["+((Ship)checker).getMsleCount()+"] missiles left.");
//				System.out.println("The game has been going on for ["+gameClock+"] ticks.");
		
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
		if(getShipCount() < 1){
			objectCollection.add(Ship.getShip(SIZE_W/2,SIZE_H/2));
			setShipCount(getShipCount() + 1);
		}else{
			System.out.println("A ship already exsists.");
		}
		setChange();
	}
	
	public void createAsteroid(){
		System.out.println("An Asteroid is being created");
		objectCollection.add(new Asteroid(SIZE_W, SIZE_H));
		setAsteroidCount(getAsteroidCount() + 1);
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
		if(getShipCount() == 1){
			while(reader.hasNext()&& fired == false){
				Object check = reader.getNext();
				if(check instanceof Ship){
					if(((Ship)check).getMsleCount() >0){
						objectCollection.add(new Missile(((GameObjects) check).getLocX(),((GameObjects) check).getLocY(),((MovingObjects) check).getDirection(),((MovingObjects) check).getSpeed()));
						reader = objectCollection.getIterator();
						System.out.println("A missile has been fired.");
						((Ship)check).firedMsle();
						if(getSound()){fireMsl.play();}
						//reader.getNext();
						fired = true;
						setMslCount(getMslCount() + 1);
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
		if(getShipCount() == 1){
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
		if(getShipCount() == 1){
			while(reader.hasNext()&& !(shipFound)){
				Object check = reader.getNext();
				if(check instanceof Ship){
					System.out.println("The ship is turning left");
					((Ship)check).steerable(-1);
					shipFound = true;
					
				}
			}
		}
		setChange();
	}
	
	public void turnShipRight(){
		IIterator reader = objectCollection.getIterator();
		boolean shipFound = false;
		if(getShipCount() == 1){
			while(reader.hasNext()&& !(shipFound)){
				Object check = reader.getNext();
				if(check instanceof Ship){
					System.out.println("The ship is turning right");
					((Ship)check).steerable(1);
					shipFound = true;
					
				}
			}
		}
		setChange();
	}
	
	public void hyperSpace(){
		IIterator reader = objectCollection.getIterator();
		if(getShipCount() == 1){
			while(reader.hasNext()){
				Object check = reader.getNext();
				if(check instanceof Ship){
					if(((GameObjects) check).getLocX()!= 512 && ((GameObjects) check).getLocY()!= 384){
						((Ship)check).setLocX(SIZE_W/2);
						((Ship)check).setLocY(SIZE_H/2);
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
		if(getShipCount() == 1){
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
		if (getAsteroidCount() >0 && getMslCount() >0){
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
			if(getAsteroidCount()<0){
				System.out.println("There is no asteroid to destoy...");
			}
			if(getMslCount()<0){
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
	
	/*public void crash(){
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
			objectCollection.add(Ship.getShip(SIZE_W,SIZE_H));
		}
			
		if(flag == 2){
			System.out.println("Your ship has crashed into an asteroid.");
		}
		setChange();
		
	}*/
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
		if(getAsteroidCount() >= 2){
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

	
	public void ticked(Dimension gwSize){
//		gameClock++;
		SIZE_H = gwSize.getHeight();
		SIZE_W = gwSize.getWidth();
		IIterator reader = objectCollection.getIterator();
		Object checker;
		//objects move with this while loop
		while(reader.hasNext()){
			checker = reader.getNext();
			
			if(checker instanceof Asteroid){
				((Asteroid)checker).move(gwSize);
//				System.out.println("Asteroid has moved.");
			}
			if(checker instanceof Missile){
				int chk = ((Missile) checker).getFuelLevel();
				if(chk <= 0){
					System.out.println("Missile has missed.");
					reader.remove();
					reader = objectCollection.getIterator();
				}else{
					((Missile)checker).decFuel();
					((Missile)checker).move(gwSize);	
//					System.out.println("Missile has moved.");
				}
			}
			if(checker instanceof Ship){

				((Ship)checker).move(gwSize);
//				System.out.println("Ship has moved.");
			}
			if(checker instanceof SpaceStation){
				((SpaceStation) checker).incBlinkCount();
//				System.out.println("SpaceStation has blinked");
			}
			
		}
		//Objects need to check and handle collisions during ticks
		reader= objectCollection.getIterator();
		while(reader.hasNext()){
			checker = reader.getNext();
			while(reader.hasNext()){
				Object otherCheck = reader.getNext();
				if(otherCheck != checker || !(otherCheck instanceof SpaceStation)&&  !(checker instanceof SpaceStation)){
					if(otherCheck instanceof IfixedObject||otherCheck instanceof SpaceStation|| checker instanceof IfixedObject || checker instanceof SpaceStation){
						
					}
					if(checker instanceof Missile && otherCheck instanceof Asteroid){
						if(((ICollider)checker).detectCollision((ICollider) otherCheck)){
							((ICollider) checker).handleCollision(otherCheck,this);
							reader=objectCollection.getIterator();
							if(getSound()){missileHit.play();}
						}
					}
					if(checker instanceof Asteroid && otherCheck instanceof Asteroid){
						if(((ICollider)checker).detectCollision((ICollider) otherCheck)){
							((ICollider) checker).handleCollision(otherCheck,this);
							reader=objectCollection.getIterator();
							if(getSound()){astCol.play();}
						}
					}
					if(checker instanceof Ship && otherCheck instanceof Asteroid){
						if(((ICollider)checker).detectCollision((ICollider) otherCheck)){
							((ICollider) checker).handleCollision(otherCheck,this);
							reader=objectCollection.getIterator();
							if(getSound()){shipHit.play();}
						}
					}
//					if(checker instanceof Missile || otherCheck instanceof Missile){
//						((ISelectable)checker).contains(MouseInfo., this);
//						((ISelectable)otherCheck).contains(check,this );
//					}
				}
			}
		}
//		System.out.println("The game time is: "+gameClock);
		setChange();
		
	}
	
	public void setSound(boolean sound){
		this.sound = sound;
		if(!(sound))
			bgSong.pause();
		else
			bgSong.play();
		
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


//	public int getGameClock() {
		
//		return this.gameClock;
//	}
	
	public int shipMslCount(){
		IIterator shipFinder = objectCollection.getIterator();
		Object checker = shipFinder.getNext();
		while(shipFinder.hasNext()&&!(checker instanceof Ship)){
			checker = shipFinder.getNext();
		}
		if(!(checker instanceof Ship)){
			
			setChanged();
			return ((Integer)null);
	
		}
		setChanged();
		return ((Ship)checker).getMsleCount();
	}
	//This may be completely out of place in here since ticked handles missleDistance with
	//Association with speed
	/*public void missleDistance(){
		
		//calculate how far a missile will travel with fuel level
	}*/
	//More methods


	public GameObjCollection getGameObjCol() {
		
		return this.objectCollection;
	}
	
	public void setWidth(int lengthX){
		SIZE_W = lengthX;
	}
	
	public void setHeight(int lengthY){
		SIZE_H = lengthY;
	}
	
	public int getGameX(){
		return SIZE_W;
	}
	
	public int getGameY(){
		return SIZE_H;
	}


	public int getShipCount() {
		return shipCount;
	}


	public void setShipCount(int shipCount) {
		this.shipCount = shipCount;
	}


	public int getAsteroidCount() {
		return asteroidCount;
	}


	public void setAsteroidCount(int asteroidCount) {
		this.asteroidCount = asteroidCount;
	}


	public void removeObj(GameObjects tobeRemoved) {
		IIterator gameObjItr = objectCollection.getIterator();
		Object checker = new Object();

		while(gameObjItr.hasNext()){
			
			checker = gameObjItr.getNext();
			if(checker.equals(tobeRemoved)){	
				if(checker instanceof Ship)
					((Ship)checker).setLife(1);
				gameObjItr.remove();
				gameObjItr = objectCollection.getIterator();
			}
		}
			
	}


	public int getMslCount() {
		return mslCount;
	}


	public void setMslCount(int mslCount) {
		this.mslCount = mslCount;
	}


	public int getShipLife() {
		IIterator reader = objectCollection.getIterator();
		Object check = reader.getNext();
		while(reader.hasNext()&&!(check instanceof Ship)){
			check = reader.getNext();
		}
		return ((Ship)check).getLife();
	}

	public ISelectable getMslSelected(){
		IIterator reader = objectCollection.getIterator();
		Object check = reader.getNext();
		while(reader.hasNext() && !(check instanceof Missile))
			check = reader.getNext();
		return ((ISelectable)check);
	}
	
}
