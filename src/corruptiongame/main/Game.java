package corruptiongame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.grea.antoine.utils.Log;
import corruptiongame.character.Enemies;
import corruptiongame.character.Enemy;
import corruptiongame.character.RPGCharacter;
import corruptiongame.character.Skills;
import corruptiongame.graphics.TextGrid;
import corruptiongame.item.Items;
import corruptiongame.statemanager.StateManager;
import corruptiongame.statemanager.states.CombatState;
import corruptiongame.statemanager.states.MainMenuState;
import corruptiongame.statemanager.states.MapState;
import corruptiongame.statemanager.states.PauseState;
import corruptiongame.worldmap.Events;

public class Game extends JPanel implements Runnable{
	
	//public constant
	public static int W = 800;
	public static int H = 630;
	public static int TILESIZE = 20;
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
		//loading
		Items.loadItems();
		Enemies.loadEnemies();
		Events.loadEvents();
		Skills.loadSkills();
		Start.loadStartValue();
		
		//initialize StateManager
		//and create the states
		manager = new StateManager();
		manager.addState(new MainMenuState(this.manager,keyboard), "MAINMENU");
		manager.addState(new MapState(manager,keyboard),"MAP");
		manager.addState(new PauseState(manager ,keyboard),"PAUSE");
		manager.addState(new CombatState(manager, keyboard), "COMBAT");
		manager.push("MAINMENU");
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
			if(elapsedTime > 32){
				manager.update(elapsedTime);
				elapsedTime -= 32;				
				this.render();
				frames++;
			}else{
				try {
					Thread.sleep(32);
				} catch (InterruptedException e) {
					Log.w(e.getMessage());
				}
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

	
