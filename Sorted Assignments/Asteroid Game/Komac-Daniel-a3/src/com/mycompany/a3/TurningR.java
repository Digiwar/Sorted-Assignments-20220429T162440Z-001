package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurningR extends Command {

	private GameWorld gw = new GameWorld();
	
	TurningR(){
		super("Turn Ship Right");
	}
	
	public void actionPerformed(ActionEvent e){
		gw.turnShipRight();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
