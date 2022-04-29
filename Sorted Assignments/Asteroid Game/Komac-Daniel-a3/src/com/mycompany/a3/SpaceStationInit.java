package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpaceStationInit extends Command {

	private GameWorld gw = new GameWorld();
	
	SpaceStationInit(){
		super("Create Space Station");
	}
	
	public void actionPerformed(ActionEvent e){
		gw.createSpaceStation();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
