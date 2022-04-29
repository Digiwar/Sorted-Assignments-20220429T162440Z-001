package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ShipCrash extends Command {
	
	private GameWorld gw = new GameWorld();
	
	ShipCrash(){
		super("Crash Ship");
	}
	
	public void actionPerformed(ActionEvent e){
		gw.crash();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
