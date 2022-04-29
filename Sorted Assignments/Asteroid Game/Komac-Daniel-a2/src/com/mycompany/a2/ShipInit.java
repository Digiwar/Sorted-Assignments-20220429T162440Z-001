package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ShipInit extends Command {

	private GameWorld gw;
	
	ShipInit(){
		super("Create Ship");
	}

	public void actionPerformed(ActionEvent e){
		gw.createShip();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
	
}
