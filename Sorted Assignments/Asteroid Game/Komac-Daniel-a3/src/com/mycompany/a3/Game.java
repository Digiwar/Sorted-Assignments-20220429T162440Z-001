package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;


public class Game extends Form implements Runnable  {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	
	private static int LENGTH_X;
	private static int LENGTH_Y;
	private static boolean active;
	private static boolean pause;
	private static boolean select;
	
	//adding Buttons
	Button Pause = new Button();
	Button fire = new Button();
	Button crash = new Button();
	Button aCrash= new Button();
	Button quit= new Button();
	Button createA = new Button();
	Button createS = new Button();
	Button createSS= new Button();
	Button hyperS= new Button();
	Button gMMslRchrg= new Button();
	Button killAst= new Button();
	Button astCollision = new Button();
	Button mslRefuel = new Button();
	
	//Adding commands
	Fire freMsl = new Fire();
	ShipCrash crashShip = new ShipCrash();	
	CrashAsteroid astCrash = new CrashAsteroid();
	Leaving leave = new Leaving();
	AsteroidInit spawnAsteroid = new AsteroidInit();
	ShipInit spawnShip = new ShipInit();	
	SpaceStationInit spawnSpaceStation = new SpaceStationInit();
	RaiseSpeed spdInc = new RaiseSpeed();
	LowerSpeed spdDec = new LowerSpeed();
	TurningL turnL = new TurningL();
	TurningR turnR = new TurningR();
	HyperSpace jump = new HyperSpace();	
	Reloader reload = new Reloader();
	AstKill killAs = new AstKill();
	AstCollision asteroidsCollision = new AstCollision();
	MissileRefuel mslRef = new MissileRefuel();

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
		
		BorderLayout screen = new BorderLayout();
		screen.getPreferredSize(this);
		this.setLayout(screen);
		
		gw = new GameWorld();
		gw.init();
		
		
		mv  = new MapView(gw);
		sv = new ScoreView();
		
		timer = new UITimer(this);
		timer.schedule(20, true, this);
		gw.addObserver(mv);
		gw.addObserver(sv);
		gw.setChange();
		
		
		
		//Initializing all functionality for buttons.

		Pause pauseCmd = new Pause();
		pauseCmd.setTarget(this);
		Pause.setCommand(pauseCmd);

		mslRef.setTarget(gw);
		mslRefuel.setCommand(mslRef);
		
		freMsl.setTarget(gw);
		fire.setCommand(freMsl);
		
		crashShip.setTarget(gw);
		crash.setCommand(crashShip);
		
		astCrash.setTarget(gw);
		aCrash.setCommand(astCrash);
		
		leave.setTarget(gw);
		quit.setCommand(leave);
		
		spawnAsteroid.setTarget(gw);
		createA.setCommand(spawnAsteroid);
		
		spawnShip.setTarget(gw);
		createS.setCommand(spawnShip);
		
		spawnSpaceStation.setTarget(gw);
		createSS.setCommand(spawnSpaceStation);
		
		spdInc.setTarget(gw);
		
		spdDec.setTarget(gw);
		
		turnL.setTarget(gw);
		
		turnR.setTarget(gw);
		
		jump.setTarget(gw);
		
		reload.setTarget(gw);
		
		killAs.setTarget(gw);
		killAst.setCommand(killAs);

		asteroidsCollision.setTarget(gw);
		astCollision.setCommand(asteroidsCollision);
				
//		Button tick = new Button();
//		tickCmd sTick = new tickCmd();
//		sTick.setTarget(gw);
//		tick.setCommand(sTick);
		
		//Key Listeners for all corresponding buttons
//		addKeyListener('t', sTick);
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
		
//		westContainer.add(formatButton(turnRight));
//		westContainer.add(formatButton(turnLeft));
		westContainer.add(formatButton(Pause));
		westContainer.add(formatButton(fire));
//		westContainer.add(formatButton(incSpd));
//		westContainer.add(formatButton(decSpd));
		westContainer.add(formatButton(createA));
		westContainer.add(formatButton(createS));
		westContainer.add(formatButton(createSS));
		westContainer.add(formatButton(crash));
		westContainer.add(formatButton(aCrash));
		westContainer.add(formatButton(hyperS));
		westContainer.add(formatButton(gMMslRchrg));
//		westContainer.add(formatButton(tick));
		westContainer.add(formatButton(astCollision));
		westContainer.add(formatButton(killAst));
		westContainer.add(formatButton(quit));
		
		//Score view at top displaying score, missiles, in-game time and if sound is on.
		Container northContainer = new Container(new FlowLayout());
		northContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		northContainer.add(sv);
		
		
		mv.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.red(255)));
		
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
	public void run() {
		Dimension gwSize = new Dimension(mv.getWidth(),mv.getHeight());
//		Point origin = new Point(mv.getAbsoluteX(),mv.getAbsoluteY());
		gw.ticked(gwSize);
		mv.repaint();
		
		
	}
	public void setPause(boolean b) {
		Game.pause=b;
		
	}
	
	public void start(){
		timer.schedule(20, true,this);
	}
	
	public void cancel() {
		timer.cancel();
		
	}
	public void gameState(boolean active) {
		if (!active) {
			mslRefuel.setEnabled(true);
			fire.setEnabled(false);
			createA.setEnabled(false);
			createS.setEnabled(false);
			createSS.setEnabled(false);
			crash.setEnabled(false);
			aCrash.setEnabled(false);
			hyperS.setEnabled(false);
			gMMslRchrg.setEnabled(false);
			astCollision.setEnabled(false);
			killAst.setEnabled(false);
			Pause.setEnabled(true);
			freMsl.setEnabled(false);
			astCrash.setEnabled(false);
			crashShip.setEnabled(false);
			spawnAsteroid.setEnabled(false);
			spawnShip.setEnabled(false);
			spawnSpaceStation.setEnabled(false);
			spdInc.setEnabled(false);
			spdDec.setEnabled(false);
			turnL.setEnabled(false);
			turnR.setEnabled(false);
			jump.setEnabled(false);
			reload.setEnabled(false);
			killAs.setEnabled(false);
			mslRef.setEnabled(true);
			removeKeyListener('i', spdInc);
			removeKeyListener('d', spdDec);
			removeKeyListener('l', turnL);
			removeKeyListener('r', turnR);
			removeKeyListener('f', freMsl);
		} 
		else {
			mslRefuel.setEnabled(false);
			fire.setEnabled(true);
			createA.setEnabled(true);
			createS.setEnabled(true);
			createSS.setEnabled(true);
			crash.setEnabled(true);
			aCrash.setEnabled(true);
			hyperS.setEnabled(true);
			gMMslRchrg.setEnabled(true);
			astCollision.setEnabled(true);
			killAst.setEnabled(true);
			Pause.setEnabled(true);
			freMsl.setEnabled(true);
			astCrash.setEnabled(true);
			crashShip.setEnabled(true);
			spawnAsteroid.setEnabled(true);
			spawnShip.setEnabled(true);
			spawnSpaceStation.setEnabled(true);
			spdInc.setEnabled(true);
			spdDec.setEnabled(true);
			turnL.setEnabled(true);
			turnR.setEnabled(true);
			jump.setEnabled(true);
			reload.setEnabled(true);
			killAs.setEnabled(true);
			mslRef.setEnabled(false);
			addKeyListener('i', spdInc);
			addKeyListener('d', spdDec);
			addKeyListener('l', turnL);
			addKeyListener('r', turnR);
			addKeyListener('f', freMsl);
			
		}		
	}
}
