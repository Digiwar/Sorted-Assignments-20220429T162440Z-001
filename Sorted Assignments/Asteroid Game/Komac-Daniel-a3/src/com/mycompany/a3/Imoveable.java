package com.mycompany.a3;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public interface Imoveable {
	
	public void move(Dimension gw);
	public double getLocX();
	public double getLocY();
	public int getDirection();
	public void setLocX(double newX);
	public void setLocY(double newY);
	
}
