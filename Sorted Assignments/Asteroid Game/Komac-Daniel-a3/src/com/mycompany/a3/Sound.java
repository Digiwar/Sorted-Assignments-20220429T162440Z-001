package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media medi;
	public Sound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			medi = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		medi.setTime(0);
		medi.play();
	}
}
