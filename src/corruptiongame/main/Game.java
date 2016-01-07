package corruptiongame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.grea.antoine.utils.Log;
import corruptiongame.character.RPGCharacter;
import corruptiongame.graphics.TextGrid;
import corruptiongame.statemanager.StateManager;
import corruptiongame.statemanager.states.CombatState;
import corruptiongame.statemanager.states.MainMenuState;
import corruptiongame.statemanager.states.MapState;

public class Game extends JPanel implements Runnable{
	
	//public constant
	public static int W = 800;
	public static int H = 600;
	public static int TILESIZE = 15;
	public static int NBTILEW = W/TILESIZE;
	public static int NBTILEH = H/TILESIZE;
	
	private boolean running = false;
	public Thread thread;
	
	//Window
	private JFrame frame;
	
	private TextGrid screen;
	
	//Keyboard
	private Keyboard keyboard;
	
	//Game State manager
	private StateManager manager;
	
	public Game(){
		//set size of the JPanel and the grid layout used to display text
		this.setSize(W, H);
		this.setLayout(new GridLayout(1,1));
		//create the window
		frame = new JFrame();
		
		//add Keyboard Listener
		keyboard = new Keyboard();
		this.addKeyListener(keyboard);
		
		//initialize StateManager
		//and create the main menu states
		//(Others state will be created on new game/load game 
		//to use the correct Character player)
		manager = new StateManager();
		manager.addState(new MainMenuState(this.manager,keyboard), "MAINMENU");
		manager.addState(new MapState(manager,keyboard), "MAP");
		manager.push("MAINMENU");
		RPGCharacter player = new RPGCharacter("Char", 1, 20, 5, 5, 0);
		CombatState combat = new CombatState(manager, player, keyboard);
		manager.addState(combat, "COMBAT");
		RPGCharacter enemy1 = new RPGCharacter("Flowey", 1, 20, 5, 5, 0);
		enemy1.setCombatX(20);
		enemy1.setCombatY(10);
		combat.addEnemy(enemy1);
		manager.push("COMBAT");
		//initialize Screen
		screen = new TextGrid(NBTILEW, NBTILEH, TILESIZE, TILESIZE);
		screen.setSize(W,H);
		this.add(screen);
	}
		
	public void render(){
		manager.render();
		for(int i = 0; i < NBTILEH; i++){
			for(int j = 0; j < NBTILEW; j++){
				screen.setDisplayedChar(manager.getCurrent().getScreen());
				screen.setBackgroundChar(manager.getCurrent().getScreenBackground());
				screen.setForegroundChar(manager.getCurrent().getScreenForeground());
			}
		}
		Graphics g = getGraphics();
		repaint();
		g.dispose();
		
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Screen");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			Log.f(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long last = System.currentTimeMillis();
		long elapsedTime = 0;
		int frames = 0;
		requestFocus();
		while(running){
			long now = System.currentTimeMillis();
			elapsedTime += (now - last);
			last = now;
			if(elapsedTime > 16){
				manager.update(elapsedTime);
				elapsedTime -= 16;				
				this.render();
				frames++;
				
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("TEST" + " | " + frames + "fps");
				frames = 0;
			}
			
		}
		this.stop();
	}

}

	
