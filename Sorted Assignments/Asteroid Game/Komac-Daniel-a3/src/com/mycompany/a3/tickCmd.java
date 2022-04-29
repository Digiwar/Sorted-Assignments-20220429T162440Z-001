package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class tickCmd extends Command {
	
	private GameWorld gw = new GameWorld();
	
	tickCmd(){
		super("Tick");
	}
	
	public void actionPerformed(ActionEvent e){
//		gw.ticked();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
}
