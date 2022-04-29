package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class undoCmd extends Command {
	
	private GameWorld gw = new GameWorld();
	
	undoCmd(){
		super("Undo");
		//Undos the last state of mthe game???
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Undo action needed");
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
}
