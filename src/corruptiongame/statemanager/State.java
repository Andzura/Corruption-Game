package corruptiongame.statemanager;

import java.awt.Color;

import javax.swing.JLabel;

import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;

public abstract class State {
	
	protected StateManager manager;
	protected char[] screen;
	protected int[] screenBackground;
	protected int[] screenForeground;
	protected Keyboard keyboard;
	
	public State(StateManager manager, Keyboard keyboard){
		this.manager = manager;
		this.keyboard = keyboard;
		this.screen = new char[Game.NBTILEH*(Game.NBTILEW)];
		this.screenBackground = new int[Game.NBTILEH*(Game.NBTILEW)];
		this.screenForeground = new int[Game.NBTILEH*(Game.NBTILEW)];
		
	}
	
	public abstract void init();
	
	public abstract void update(long elapsedTime);
	
	public abstract void render();
	
	public abstract void exit();
	
	public char[] getScreen(){
		return screen;
	}
	
	public char getScreen(int index){
		return screen[index];
	}
	
	public int[] getScreenBackground(){
		return screenBackground;
	}
	
	public int getScreenBackground(int index){
		return screenBackground[index];
	}
	public int[] getScreenForeground(){
		return screenForeground;
	}
	
	public int getScreenForeground(int index){
		return screenForeground[index];
	}
	
	protected void writeStringOnScreen(String string, int x, int y, int colorBg, int colorFg){
		for(int i = 0; i < string.length() && i < screen.length;i++){
			screen[y*(Game.NBTILEW)+x+i] = string.charAt(i);
			screenBackground[y*(Game.NBTILEW)+x+i] = colorBg;
			screenForeground[y*(Game.NBTILEW)+x+i] = colorFg;
		}
	}
	protected void clearScreen(int color){
		for(int i = 0; i < screen.length; i++){
			screen[i] = ' ';
			screenBackground[i] = color;
			screenForeground[i] = color;
		}
	}
}
