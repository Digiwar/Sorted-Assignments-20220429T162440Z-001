package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class saveCmd extends Command {
	
	private GameWorld gw = new GameWorld();
	
	saveCmd(){
		super("Save");
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Game has saved.");
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
}
