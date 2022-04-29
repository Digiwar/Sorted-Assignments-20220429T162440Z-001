package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HyperSpace extends Command {
	private GameWorld gw = new GameWorld();
	HyperSpace(){
		super("Jump to HyperSpace");
	}
	public void actionPerformed(ActionEvent e){
		gw.hyperSpace();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
