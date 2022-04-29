package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class AsteroidInit extends Command {
	
	private GameWorld gw = new GameWorld();
	AsteroidInit(){
		super("Create Asteroids");
	}
	
	public void actionPerformed(ActionEvent e){
			gw.createAsteroid();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
	
}
