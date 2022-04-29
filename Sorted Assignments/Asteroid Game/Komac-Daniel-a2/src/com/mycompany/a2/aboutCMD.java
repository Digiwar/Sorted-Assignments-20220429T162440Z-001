package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class aboutCMD extends Command {
	
	private GameWorld gw = new GameWorld();
	aboutCMD(){
		super("About");
	}
	
	public void actionPerformed(ActionEvent e){
		gw.showAllInfo();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
	
}
