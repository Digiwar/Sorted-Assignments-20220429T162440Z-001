package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CrashAsteroid extends Command {
	private GameWorld gw = new GameWorld();
	
	CrashAsteroid(){
		super("Asteroid hit ship");
	}
	
	public void actionPerformed(ActionEvent e){
			gw.crash();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
