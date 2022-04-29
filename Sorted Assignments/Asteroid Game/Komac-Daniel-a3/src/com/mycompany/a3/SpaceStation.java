package com.mycompany.a3;

//import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixedObjects implements IDrawable, ISelectable{

	private int blinkRate;
	private int blinkAmount;
	private boolean on;
	//private int msle;
	
	
	SpaceStation(){
		setUnID(getRdm().nextInt(100));
		setLocX(getRdm().nextDouble()*1024.0);
		setLocY(getRdm().nextDouble()*768.0);
		blinkRate=getRdm().nextInt(100)+50;
		blinkAmount=0;
		setSize(50);
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

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColour());
		int drawingX = (int)(pCmpRelPrnt.getX() + (this.getLocX()-getSize()/2));
		int drawingY = (int)(pCmpRelPrnt.getY() + (this.getLocY()-getSize()/2));
		if(!(on)){
			
			g.drawArc(drawingX, drawingY, getSize(), getSize(), 0, 360);
			g.fillArc(drawingX, drawingY, getSize(), getSize(), 0, 360);
		
		}else{

			g.drawArc(drawingX, drawingY, getSize(), getSize(), 0, 360);
		
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("SpaceStation", drawingX+40,drawingY+40);
	}





	




	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}





	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		return false;
	}





	public void selected(boolean selected) {
		// TODO Auto-generated method stub
		
	}



	/*public int msleCount(){
	 * 
	 * return msle;
	 * used for calculating the amount of rockets left
	 * for the spaceship, if and when needed for game world.
	 */
	
}
