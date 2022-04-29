package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class SpaceStation extends FixedObjects {

	private int blinkRate;
	private int blinkAmount;
	private boolean on;
	//private int msle;
	
	
	SpaceStation(){
		setUnID(getRdm().nextInt(100));
		setLocX(getRdm().nextDouble()*1024.0);
		setLocY(getRdm().nextDouble()*768.0);
		blinkRate=getRdm().nextInt(10);
		blinkAmount=0;
		on = true;
		//msle = rdm.nextInt(100)+1; This may be used later for missle counts and how much
		//ammo can be provided to ships.
		setColor(ColorUtil.rgb(0, 0, 255));
	}

	


	
	public int getUnID() {
		return getUnID();
	}
	
	public int getBlinkRate(){
		return blinkRate;
	}
	
	public void incBlinkCount(){
		if(blinkAmount == blinkRate){
			if(on == true){
				on = false;
				blinkAmount = 0;
			}else{
				on = true;
				blinkAmount = 0;
			}
		}
		blinkAmount++;
	}
	
	public String name(){
		return "Station";
	}
	
	/*public int msleCount(){
	 * 
	 * return msle;
	 * used for calculating the amount of rockets left
	 * for the spaceship, if and when needed for game world.
	 */
	
}
