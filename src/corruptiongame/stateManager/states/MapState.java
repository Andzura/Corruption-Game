package corruptiongame.statemanager.states;

import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;
import corruptiongame.worldmap.WorldMap;
import corruptiongame.character.RPGCharacter;

public class MapState extends State{
	
	private RPGCharacter player;
	private WorldMap world;
	

	public MapState(StateManager manager, Keyboard keyboard){
		super(manager, keyboard);
	}
	
	public void init() {
		player = new RPGCharacter("Char", 1, 20, 5, 5, 0);
		world = new WorldMap("/map/map.txt");
		
		
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
