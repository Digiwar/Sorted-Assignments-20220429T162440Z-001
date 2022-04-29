package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class helpCMD extends Command {
	
	private GameWorld gw = new GameWorld();
	
	helpCMD(){
		super("Test");
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("This is a test.");
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
}
