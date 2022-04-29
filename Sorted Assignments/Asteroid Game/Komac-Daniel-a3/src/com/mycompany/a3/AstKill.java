package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AstKill extends Command {
	private GameWorld gw = new GameWorld();
	AstKill(){
		super("Asteroid Destroyed");
	}
	public void actionPerformed(ActionEvent e){
		gw.killConfirmed();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
