package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RaiseSpeed extends Command {

	private GameWorld gw = new GameWorld();
	
	RaiseSpeed(){
		super("Increase Speed");
	}

	public void actionPerformed(ActionEvent e){
		gw.incShipSpd();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
}
