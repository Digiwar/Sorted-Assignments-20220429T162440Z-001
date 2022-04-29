package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MissileRefuel extends Command {

	private GameWorld gw;
	private ISelectable mslSelected;
	
	public MissileRefuel() {
		super("Refuel Missile");
	}

	public void setTarget(GameWorld gw) {
		
		this.gw=gw;
		
	}
	
	public void actionPerformed(ActionEvent e){
		mslSelected = gw.getMslSelected();
		if(mslSelected.isSelected()){
			((Missile) mslSelected).setFuel(300);
		}
	}

}
