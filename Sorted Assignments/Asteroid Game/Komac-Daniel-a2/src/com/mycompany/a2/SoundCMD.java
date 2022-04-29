package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;

public class SoundCMD extends Command {
	
	private GameWorld gw = new GameWorld();
	
	SoundCMD(){
		super("Sound");
		//COmplicated toggle command
	}
	public void actionPerformed(ActionEvent e) {
		if (((CheckBox) e.getComponent()).isSelected()) {
			gw.setSound(true);
			
		} else {
			gw.setSound(false);
		}
		SideMenuBar.closeCurrentMenu();
	}
	
	public void setTarget(GameWorld gw){
		this.gw = gw;
	}
	
	
}