package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	/*
	private void setButtonTotally(Command C,Button){
		experiment for later...
	}*/
	
	//This method does formatting so I do not have to repeat code over and over again. Reduction of length.
	private Component formatButton(Button name){

		name.getAllStyles().setPadding(TOP, 2);
		name.getAllStyles().setPadding(BOTTOM, 2);
		name.getAllStyles().setBgTransparency(255);
		name.getAllStyles().setBgColor(ColorUtil.MAGENTA);
		name.getAllStyles().setFgColor(ColorUtil.BLACK);
		name.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		return name;
		
	}
	Game(){
		//Initializing game world
		gw = new GameWorld();
		gw.init();
		
		
		mv  = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		//Initializing all functionality for buttons.
		Button fire = new Button();
		Fire freMsl = new Fire();
		freMsl.setTarget(gw);
		fire.setCommand(freMsl);
		
		Button crash = new Button();
		ShipCrash crashShip = new ShipCrash();
		crashShip.setTarget(gw);
		crash.setCommand(crashShip);
		
		Button aCrash= new Button();
		CrashAsteroid astCrash = new CrashAsteroid();
		astCrash.setTarget(gw);
		aCrash.setCommand(astCrash);
		
		
		Button quit = new Button();
		Leaving leave = new Leaving();
		leave.setTarget(gw);
		quit.setCommand(leave);
		
		Button createA = new Button();
		AsteroidInit spawnAsteroid = new AsteroidInit();
		spawnAsteroid.setTarget(gw);
		createA.setCommand(spawnAsteroid);

		Button createS = new Button();
		ShipInit spawnShip = new ShipInit();
		spawnShip.setTarget(gw);
		createS.setCommand(spawnShip);
		
		Button createSS = new Button();
		SpaceStationInit spawnSpaceStation = new SpaceStationInit();
		spawnSpaceStation.setTarget(gw);
		createSS.setCommand(spawnSpaceStation);
		
		Button incSpd = new Button();
		RaiseSpeed spdInc = new RaiseSpeed();
		spdInc.setTarget(gw);
		incSpd.setCommand(spdInc);
		
		Button decSpd = new Button();
		LowerSpeed spdDec = new LowerSpeed();
		spdDec.setTarget(gw);
		decSpd.setCommand(spdDec);

		Button turnLeft = new Button();
		TurningL turnL = new TurningL();
		turnL.setTarget(gw);
		turnLeft.setCommand(turnL);
		
		Button turnRight = new Button();
		TurningR turnR = new TurningR();
		turnR.setTarget(gw);
		turnRight.setCommand(turnR);
		
		Button hyperS = new Button();
		HyperSpace jump = new HyperSpace();
		jump.setTarget(gw);
		hyperS.setCommand(jump);
		
		Button gMMslRchrg = new Button();
		Reloader reload = new Reloader();
		reload.setTarget(gw);
		gMMslRchrg.setCommand(reload);
		
		Button killAst = new Button();
		AstKill killAs = new AstKill();
		killAs.setTarget(gw);
		killAst.setCommand(killAs);
		
		Button astCollision= new Button();
		AstCollision asteroidsCollision = new AstCollision();
		asteroidsCollision.setTarget(gw);
		astCollision.setCommand(asteroidsCollision);
		
		Button tick = new Button();
		tickCmd sTick = new tickCmd();
		sTick.setTarget(gw);
		tick.setCommand(sTick);
		
		//Key Listeners for all corresponding buttons
		addKeyListener('t', sTick);
		addKeyListener('a', spawnAsteroid);
		addKeyListener('b', spawnSpaceStation);
		addKeyListener('s', spawnShip);
		addKeyListener('n', reload);
		addKeyListener('k', killAs);
		addKeyListener('c', crashShip);
		addKeyListener('x', astCrash);
		addKeyListener('q', leave);
		addKeyListener('r', turnR);//needs to be replaced with right arrow
		addKeyListener('l', turnL);//needs to be replaced with left arrow
		addKeyListener('i', spdInc);//needs to be replaced with up arrow
		addKeyListener('d', spdDec);//needs to be replaced with down arrow
		addKeyListener('j', jump);//Not sure what to replace j with but said didn't need button. I have buttons for pracitcallity.
		addKeyListener('f', freMsl);//has space bar command but sometimes doesn't read it
		addKeyListener(' ', freMsl);
		
		//Creating the Toolbar for the sound and exit commands that will be in the top left, or as determined by codenameOne
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);

		SoundCMD soundCmd = new SoundCMD();
		soundCmd.setTarget(gw);
		CheckBox soundCheck = new CheckBox("Sound");
		
		soundCheck.getAllStyles().setBgColor(ColorUtil.LTGRAY);

		Command help = new helpCMD();
		Command newF = new newFile();
		Command save = new saveCmd();
		Command undo = new undoCmd();
		aboutCMD about = new aboutCMD();
		
		about.setTarget(gw);
		
		
		soundCmd.putClientProperty("SideComponent", soundCheck);

		soundCheck.setCommand(soundCmd);
		
		myToolbar.addCommandToSideMenu(new Command("File"));
		myToolbar.addCommandToSideMenu(leave);
		myToolbar.addCommandToSideMenu(newF);
		myToolbar.addCommandToSideMenu(save);
		myToolbar.addCommandToSideMenu(undo);
		myToolbar.addCommandToSideMenu(about);
		
		myToolbar.addComponentToSideMenu(soundCheck);
		myToolbar.addCommandToRightBar(help);
		myToolbar.setTitle("Asteroids");
		
		

		
		//Containers that allow for the storage of more components in a specific location, also adding in all the formated buttons so the display and see them.
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.getAllStyles().setBgColor(ColorUtil.BLUE);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.GREEN));
		
		westContainer.add(formatButton(turnRight));
		westContainer.add(formatButton(turnLeft));
		westContainer.add(formatButton(fire));
		westContainer.add(formatButton(incSpd));
		westContainer.add(formatButton(decSpd));
		westContainer.add(formatButton(createA));
		westContainer.add(formatButton(createS));
		westContainer.add(formatButton(createSS));
		westContainer.add(formatButton(crash));
		westContainer.add(formatButton(aCrash));
		westContainer.add(formatButton(hyperS));
		westContainer.add(formatButton(gMMslRchrg));
		westContainer.add(formatButton(tick));
		westContainer.add(formatButton(astCollision));
		westContainer.add(formatButton(killAst));
		westContainer.add(formatButton(quit));
		
		//Score view at top displaying score, missiles, in-game time and if sound is on.
		Container northContainer = new Container(new FlowLayout());
		northContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		northContainer.add(sv);
		mv.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.red(255)));
		mv.add(new Label("Will be used in future"));
		
		BorderLayout screen = new BorderLayout();
		screen.getPreferredSize(this);
		this.setLayout(screen);
		
		
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, northContainer);
		this.add(BorderLayout.WEST, westContainer);
		
		
		this.show();
		
		//play();
	}
	/*
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
					gw.createAsteroid();1
					break;
				case 'b':
					gw.createSpaceStation();2
					break;
				case 's':
					gw.createShip();3
					break;
				case 'i':
					gw.incShipSpd();4
					break;
				case 'd':
					gw.decShipSpd();5
					break;
				case 'l':
					gw.turnShipLeft();6
					break;
				case 'r':
					gw.turnShipRight();7
					break;
				case 'f':
					gw.fireMsle();8
					break;
				case 'j':
					gw.hyperSpace();9
					break;
				case'n':
					gw.gMMsleRecharge();10
					break;
				case'k':
					gw.killConfirmed();11
					break;
				case'c':
					gw.crash();12
					break;
				case'x':
					gw.asteroidCollison();13
					break;
				case't':
					gw.ticked();14
					break;
				case'p':
					gw.showBasicInfo();15
					break;
				case'm':
					gw.showAllInfo();16
					break;
				case'q':
					System.out.println("Comfirm quiting 'y/.'");17
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
	
	}*/
}
