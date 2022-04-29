package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Reloader extends Command {

	private GameWorld gw = new GameWorld();
	
	Reloader(){
		super("GM Reload");
	}
	
	public void actionPerformed(ActionEvent e){
			gw.gMMsleRecharge();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	

}
