package corruptiongame.controller;

import corruptiongame.character.RPGCharacter;
import corruptiongame.graphics.Tile;
import corruptiongame.worldmap.Event;
import corruptiongame.worldmap.WorldMap;

public class ControllerMap {
	private RPGCharacter player;
	private WorldMap map;
	private Event lastTriggeredEvent;
	
	public ControllerMap(RPGCharacter player, WorldMap map){
		this.player = player;
		this.map = map;
		lastTriggeredEvent = null;
	}
	
	
	//try to move player
	// x is the direction and distance the player should be move by on the X axis
	// y is the direction and distance the player should be move by on the Y axis
	public void movePlayerBy(int x, int y){
		if(map.getTile(player.getMapX() + x, player.getMapY())  < Tile.STARTCOLLISIONTILE){
			player.setMapX(player.getMapX() + x);
		}
		
		if(map.getTile(player.getMapX(), player.getMapY() + y)  < Tile.STARTCOLLISIONTILE){
			player.setMapY(player.getMapY() + y);
		}
	}
	
	//try to move player
	// (x,y) the position the player should be move to
	public void movePlayerTo(int x, int y){
		if(map.getTile(x, y) < Tile.STARTCOLLISIONTILE){
			player.setMapX(x);
			player.setMapY(y);
		}
	}


	public boolean checkEvent() {
		Event e = map.checkEvent(player.getMapX(), player.getMapY());
		if(e != null){
			lastTriggeredEvent = e;
			return true;
		}
		return false;
	}


	public Event getLastTriggeredEvent() {
		return lastTriggeredEvent;
	}
}
