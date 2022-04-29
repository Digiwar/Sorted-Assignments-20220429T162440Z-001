package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Pause extends Command {
	private Game game;
	private boolean pauseState;
	


	private GameWorld gw = new GameWorld();
	
	public Pause()
	{
		super("Pause");
	}
	
	public void setTarget(Game game){
		this.game = game;
	}
	
	public void actionPerformed(ActionEvent e){
		pauseState = !pauseState;
		if(pauseState){
			game.cancel();
			game.gameState(false);
			game.setPause(false);
			game.Pause.setText("Play");
		}else{
			game.start();
			game.gameState(true);
			game.setPause(true);
			game.Pause.setText("Pause");
		}
	}

}
