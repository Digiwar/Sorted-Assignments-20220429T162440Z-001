package com.mycompany.a2;

import java.util.Observable;

public class ProxyGameWorld extends Observable implements IGameWorld {

	private int getMissileCount;
	private GameWorld gw;
	
	ProxyGameWorld(GameWorld gw){
		this.gw= gw;
	}

	public boolean soundChange() {
		
		return gw.getSound();
	}

	public int getScore() {
		
		return gw.getScore();
	}

	public int getClock() {
		
		return gw.getGameClock();
	}
	
	public int getMissleCount(){
		return gw.shipMslCount();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
	
}
