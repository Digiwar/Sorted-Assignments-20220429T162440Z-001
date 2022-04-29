package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {

	private GameWorld gw;
	private Label mInGameTime = new Label("In-game Time: ");
	private Label mScore = new Label("Score: ");
	private Label mMissileCount = new Label("Missiles: ");
	private Label mSound = new Label("Sound: ");
	private Label vInGameTime = new Label("0");
	private Label vScore = new Label("0");
	private Label vMissileCount = new Label("0");
	private Label vSound = new Label("Off");
	
	public Label formatLabels(Label name){
		name.getAllStyles().setFgColor(ColorUtil.BLACK);
		name.getAllStyles().setBgTransparency(255);
		name.getAllStyles().setBgColor(ColorUtil.WHITE);
		
		return name;
	}
	
	public Label subFormatLabels(Label name){
		name.getAllStyles().setFgColor(ColorUtil.BLACK);
		name.getAllStyles().setBgTransparency(255);
		name.getAllStyles().setBgColor(ColorUtil.WHITE);
		name.getAllStyles().setPadding(Component.RIGHT,5);
		name.getAllStyles().setPadding(Component.LEFT, 5);
		
		return name;
	}
	
	ScoreView(){
		
		this.setLayout(new FlowLayout(Component.CENTER));
		
		this.add(subFormatLabels(mInGameTime));
		this.add(subFormatLabels(vInGameTime));
		this.add(subFormatLabels(mScore));
		this.add(subFormatLabels(vScore));
		this.add(subFormatLabels(mMissileCount));
		this.add(subFormatLabels(vMissileCount));
		this.add(subFormatLabels(mSound));
		this.add(subFormatLabels(vSound));
	
	}
	
	public void update(Observable observable, Object data) {
		gw = (GameWorld) observable;
		int time = gw.getGameClock();
		int getMissileCount = gw.shipMslCount();
		int score= gw.getScore();
		boolean sound = gw.getSound();
		this.vInGameTime.setText(time+"");
		this.vMissileCount.setText(getMissileCount+"");
		this.vScore.setText(score+"");
		if(sound){
			this.vSound.setText("ON");
		}else{
			this.vSound.setText("Off");
		}
				
	}

}
