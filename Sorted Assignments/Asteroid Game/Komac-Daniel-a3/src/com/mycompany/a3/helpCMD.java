package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class helpCMD extends Command {
	
	private GameWorld gw;
	
	
	helpCMD(){
		super("Help");
	}
	
	public void actionPerformed(ActionEvent e){
		String s = "i - increase ship speed\n"
				+ "d - decrease ship speed\n"
				+ "l - left turn\n"
				+ "r - right turn\n"
				+ "f - fire missile\n"
				+ "q - exit game";
		Dialog.show("Commands", s, "OK", null);
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
}
