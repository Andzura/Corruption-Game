package corruptiongame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.grea.antoine.utils.Log;

public class Keyboard extends KeyAdapter{
	private boolean up, down, left, right, enter;
	private boolean[] keys = new boolean[190];
	
	//invoked when a Key is pressed
	//change the keys Array accordingly then invoke the update() method 
	//to update the value of the tracked actions.
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() >= 190){
			Log.w("Key code "+ e.getKeyCode() +" is out of bounds, this key won't do anything.");
		}
		else{
		keys[e.getKeyCode()] = true;
		this.update();
		}
	}
	//update the value of the tracked actions ( i.e. up down left right enter)
	//accordingly with their keyboard binding.
	private void update(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_Z];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_Q];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		enter = keys[KeyEvent.VK_ENTER];
		
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() >= 190){
			Log.w("Key code "+ e.getKeyCode() +" is out of bounds, this key won't do anything.");
		}
		else{
		keys[e.getKeyCode()] = false;
		this.update();
		}
	}

	public boolean isEnterPressed() {
		return enter;
	}
	
	public boolean isRightPressed() {
		return right;
	}
	
	public boolean isLeftPressed() {
		return left;
	}
	
	public boolean isDownPressed() {
		return down;
	}
	
	public boolean isUpPressed() {
		return up;
	}
	
	public boolean isEnterTyped() {
		if(enter){
			enter = false;
			return true;
		}
		return false;
	}
	
	public boolean isRightTyped() {
		if(right){
			right = false;
			return true;
		}
		return false;
	}
	
	public boolean isLeftTyped() {
		if(left){
			left = false;
			return true;
		}
		return false;
	}
	
	public boolean isDownTyped() {
		if(down){
			down = false;
			return true;
		}
		return false;
	}
	
	public boolean isUpTyped() {
		if(up){
			up = false;
			return true;
		}
		return false;
	}
	
}
