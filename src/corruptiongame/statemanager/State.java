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
		String[] lines = string.split("\\r?\\n");
		int yWrite;
		for(int i = 0; i < lines.length; i++){
			for(int xWrite = x; (xWrite-x) < lines[i].length() && xWrite < Game.NBTILEW; xWrite++){
				yWrite = y + i;
				if(yWrite < Game.NBTILEH){
					screen[yWrite*(Game.NBTILEW)+xWrite] = lines[i].charAt(xWrite-x);
					screenBackground[yWrite*(Game.NBTILEW)+xWrite] = colorBg;
					screenForeground[yWrite*(Game.NBTILEW)+xWrite] = colorFg;
				}
				
		}
		}
	}
	protected void clearScreenRegion(int color, int xUL, int yUL, int xDR, int yDR){
		for(int x = xUL; x < xDR; x++){
			for(int y = yUL; + y < yDR; y++){
				screen[x+y*Game.NBTILEW] = ' ';
				screenBackground[x+y*Game.NBTILEW] = color;
				screenForeground[x+y*Game.NBTILEW] = color;
			}
		}
	}
	protected void clearScreen(int color){
		clearScreenRegion(color,0,0,Game.NBTILEW-1, Game.NBTILEH-1);
	}
}
