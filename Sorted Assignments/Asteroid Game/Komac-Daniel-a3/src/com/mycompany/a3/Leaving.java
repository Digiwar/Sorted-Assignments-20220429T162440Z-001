package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Leaving extends Command {

	private GameWorld gw = new GameWorld();
	
	Leaving(){
		super("Quit");
	}
	
	public void actionPerformed(ActionEvent e){
		//needs system quit not gameworld information
		System.exit(0);
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
