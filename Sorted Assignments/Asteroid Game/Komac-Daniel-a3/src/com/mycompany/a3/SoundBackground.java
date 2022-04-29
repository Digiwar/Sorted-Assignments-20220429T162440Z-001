package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class SoundBackground implements Runnable {
	private Media bg;
	
	public SoundBackground(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			bg = MediaManager.createMedia(is, "audio/wav", this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		bg.pause();
	}
	
	public void play() {
		bg.play();
	}
	
	//allows for looping while game is running.
	public void run() {
		bg.setTime(0);
		bg.play();
	}
}
