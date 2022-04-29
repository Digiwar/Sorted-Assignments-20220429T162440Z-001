package com.mycompany.a1;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form {
	private GameWorld gw;
	
	Game(){
		
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play(){
		Label myLabel=new Label("Enter a Command: ");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();	
		this.addComponent(myTextField);
		this.show();
		

		myTextField.addActionListener(new ActionListener(){
			boolean followingCom = false;
			
			public void actionPerformed(ActionEvent evt){

				String aCommand = myTextField.getText().toString();
				myTextField.clear();
				switch(aCommand.charAt(0)){

				case 'a':
					gw.createAsteroid();
					break;
				case 'b':
					gw.createSpaceStation();
					break;
				case 's':
					gw.createShip();
					break;
				case 'i':
					gw.incShipSpd();
					break;
				case 'd':
					gw.decShipSpd();
					break;
				case 'l':
					gw.turnShipLeft();
					break;
				case 'r':
					gw.turnShipRight();
					break;
				case 'f':
					gw.fireMsle();
					break;
				case 'j':
					gw.hyperSpace();
					break;
				case'n':
					gw.gMMsleRecharge();
					break;
				case'k':
					gw.killConfirmed();
					break;
				case'c':
					gw.crash();
					break;
				case'x':
					gw.asteroidCollison();
					break;
				case't':
					gw.ticked();
					break;
				case'p':
					gw.showBasicInfo();
					break;
				case'm':
					gw.showAllInfo();
					break;
				case'q':
					System.out.println("Comfirm quiting 'y/.'");
					followingCom = true;
					break;
				case'y':
					if(followingCom){
						System.exit(0);
						break;
					}
				case'.':
					followingCom=false;
					break;
				}
			}
		});
	
	}
}
