package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Graphics;
//import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;

public class MapView extends Container implements Observer {
	
	private GameWorld gw;
	
	
	MapView(GameWorld gw){
		this.gw = gw;
		

	}
	
	public void update(Observable observable, Object data) {
		gw = (GameWorld) observable;
		repaint();
		
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		GameObjCollection gameObjs = gw.getGameObjCol();
		Point cmpRelPrnt = new Point(getX(),getY());
		IIterator gameObjItr = gameObjs.getIterator();
		
		while(gameObjItr.hasNext())
		{
			GameObjects objs = (GameObjects) gameObjItr .getNext();
			objs.draw(g,cmpRelPrnt);
		}
	}

}
