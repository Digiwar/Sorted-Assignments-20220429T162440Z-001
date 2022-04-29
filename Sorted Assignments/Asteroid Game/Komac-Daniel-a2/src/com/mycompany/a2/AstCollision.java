package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class AstCollision extends Command {
	
	private GameWorld gw = new GameWorld();
	AstCollision(){
		super("Asteroid Collision");
	}
	
	public void actionPerformed(ActionEvent e){
		gw.asteroidCollison();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
	
}