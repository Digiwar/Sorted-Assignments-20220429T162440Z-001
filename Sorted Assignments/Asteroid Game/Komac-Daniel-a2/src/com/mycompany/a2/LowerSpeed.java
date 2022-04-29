package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LowerSpeed extends Command {

	private GameWorld gw = new GameWorld();
	
	LowerSpeed(){
		super("Decrease Speed");
	}
	
	public void actionPerformed(ActionEvent e){
		gw.decShipSpd();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
