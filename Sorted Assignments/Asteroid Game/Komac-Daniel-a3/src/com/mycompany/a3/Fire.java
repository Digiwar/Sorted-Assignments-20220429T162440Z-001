package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Fire extends Command {
	
	private GameWorld gw = new GameWorld();
	
	public Fire()
	{
		super("Fire");
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e){
		
		gw.fireMsle();
	}
}
