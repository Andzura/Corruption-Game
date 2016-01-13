package corruptiongame.statemanager.states;

import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;
import corruptiongame.worldmap.WorldMap;
import corruptiongame.character.RPGCharacter;
import corruptiongame.controller.ControllerMap;
import corruptiongame.graphics.Tile;

public class MapState extends State{
	
	private RPGCharacter player;
	private WorldMap world;
	private ControllerMap controller;
	private long delta;
	

	public MapState(StateManager manager, Keyboard keyboard){
		super(manager, keyboard);
	}
	
	public void init() {
		player = new RPGCharacter("Char", 1, 20, 5, 5, 0);
		world = new WorldMap("/map/map.txt");
		player.setMapX(20);
		player.setMapY(14);
		controller = new ControllerMap(player, world);		
		
	}

	@Override
	public void update(long elapsedTime) {
			delta += elapsedTime;
			//to avoid the character to be to fast & uncontrollable
			//we move player, if necessary, only every 1/5 second			
			if(keyboard.isDownPressed() && delta > 166){
				controller.movePlayerBy(0, 1);
				delta = 0;
			}
			if(keyboard.isUpPressed() && delta > 166){
				controller.movePlayerBy(0, -1);
				delta = 0;
			}
			if(keyboard.isLeftPressed() && delta > 166){
				controller.movePlayerBy(-1, 0);
				delta = 0;
			}
			if(keyboard.isRightPressed() && delta > 166){
				controller.movePlayerBy(1, 0);
				delta = 0;
			}
	}

	@Override
	public void render() {
		int tile;
		for(int x = 0; x < Game.NBTILEW; x++){
			for(int y = 0; y < Game.NBTILEH; y++){
				tile = world.getTile(x,y);
				screen[y*Game.NBTILEW + x] = Tile.TILESET[tile].displayedChar;
				screenBackground[y*Game.NBTILEW + x] = Tile.TILESET[tile].background;
				screenForeground[y*Game.NBTILEW + x] = Tile.TILESET[tile].foreground;
			}
		}
		screen[player.getMapY()*Game.NBTILEW + player.getMapX()] = (char) 0xff02;
		screenForeground[player.getMapY()*Game.NBTILEW + player.getMapX()] = 0xff0000;
		
	}
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
