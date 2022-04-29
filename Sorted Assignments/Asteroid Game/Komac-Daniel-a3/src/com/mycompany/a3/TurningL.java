package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurningL extends Command {

	private GameWorld gw = new GameWorld();
	
	TurningL(){
		super("Turn Ship Left");
	}
	
	public void actionPerformed(ActionEvent e){
		gw.turnShipLeft();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
