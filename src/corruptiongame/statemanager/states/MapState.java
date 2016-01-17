package corruptiongame.statemanager.states;

import java.util.List;

import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;
import corruptiongame.worldmap.Event;
import corruptiongame.worldmap.EventCombat;
import corruptiongame.worldmap.WorldMap;
import corruptiongame.character.Enemies;
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
		player = null;
	}
	public MapState(StateManager manager, Keyboard keyboard, RPGCharacter player){
		super(manager, keyboard);
		this.player = player;
	}
	
	public void init() {
		if(player == null){
			player = new RPGCharacter("Char", 1, 20, 5, 5, 0);
			player.setMapX(20);
			player.setMapY(14);
		}
		world = new WorldMap("/map/map.txt");
		controller = new ControllerMap(player, world);
		EventCombat e = new EventCombat(22, 14, 22, 14);
		e.addEnemyId(0);
		world.addEvent(e);
		
	}

	@Override
	public void update(long elapsedTime) {
			delta += elapsedTime;
			//to avoid the character to be to fast & uncontrollable
			//we move player, if necessary, only every 1/5 second	
			
			
			if(controller.checkEvent()){
				Event e = controller.getLastTriggeredEvent();
				if(e instanceof EventCombat){
					List<Integer> enemyId = ((EventCombat) e).getEnemiesId();
					State s = manager.getState("COMBAT");
					((CombatState) s).addEnemy(Enemies.getEnemy(enemyId));
					manager.push("COMBAT");
				}
			}
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
		int xPlayer, yPlayer;
		xPlayer = player.getMapX();
		yPlayer = player.getMapY();
		int xStart = xPlayer - (Game.NBTILEW/2);
		int yStart = yPlayer - (Game.NBTILEH/2);
		if(xStart < 0){
			xStart = 0;
		}
		if(yStart < 0){
			yStart = 0;
		}
		if((xStart + Game.NBTILEW) > world.getSizeX()){
			xStart = world.getSizeX() - Game.NBTILEW;
		}
		if((yStart + Game.NBTILEH) > world.getSizeY()){
			yStart = world.getSizeY() - Game.NBTILEH;
		}
		for(int x = 0; x < Game.NBTILEW; x++){
			for(int y = 0; y < Game.NBTILEH; y++){
				tile = world.getTile(x+xStart,y+yStart);
				screen[y*Game.NBTILEW + x] = Tile.TILESET[tile].displayedChar;
				screenBackground[y*Game.NBTILEW + x] = Tile.TILESET[tile].background;
				screenForeground[y*Game.NBTILEW + x] = Tile.TILESET[tile].foreground;
			}
		}

		if(player.getMapX() > xStart && player.getMapX() < (xStart+Game.NBTILEW)){
			if(player.getMapY() >yStart && player.getMapY() < (yStart +Game.NBTILEH)){
				screen[(player.getMapY()-yStart)*Game.NBTILEW + player.getMapX()-xStart] = (char) 0xff02;
				screenForeground[(player.getMapY()-yStart)*Game.NBTILEW + player.getMapX()-xStart] = 0xff0000;
			}
		}
		
	}
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
