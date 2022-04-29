package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class newFile extends Command {
	
	private GameWorld gw = new GameWorld();
	
	newFile(){
		super("New");
	}
	public void actionPerformed(ActionEvent e){
		System.out.println("This will probably be like a restart button");
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
}
