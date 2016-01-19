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
import corruptiongame.graphics.TextGrid;
import corruptiongame.item.Items;
import corruptiongame.statemanager.StateManager;
import corruptiongame.statemanager.states.CombatState;
import corruptiongame.statemanager.states.MainMenuState;
import corruptiongame.statemanager.states.MapState;
import corruptiongame.statemanager.states.PauseState;

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
		Enemies.loadEnemies();
		Items.loadItems();
		
		//initialize StateManager
		//and create the states
		manager = new StateManager();
		RPGCharacter player = new RPGCharacter("Char", 1, 20, 5, 5, 0);
		player.addItem(Items.getItem(0));
		player.addItem(Items.getItem(1));
		player.addItem(Items.getItem(2));
		player.addItem(Items.getItem(3));
		player.addItem(Items.getItem(4));
		player.addItem(Items.getItem(5));
		player.addItem(Items.getItem(6));
		player.addItem(Items.getItem(7));
		player.addItem(Items.getItem(8));
		player.addItem(Items.getItem(9));
		player.addItem(Items.getItem(10));
		player.addItem(Items.getItem(11));
		player.addItem(Items.getItem(12));
		player.addItem(Items.getItem(13));
		player.addItem(Items.getItem(14));
		player.addItem(Items.getItem(15));
		
		manager.addState(new MainMenuState(this.manager,keyboard), "MAINMENU");
		manager.addState(new MapState(manager, player ,keyboard),"MAP");
		manager.push("MAP");
		CombatState combat = new CombatState(manager, player, keyboard);
		manager.addState(new PauseState(manager, player ,keyboard),"PAUSE");
		manager.addState(combat, "COMBAT");
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

	
