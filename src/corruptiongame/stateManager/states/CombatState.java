package corruptiongame.statemanager.states;

import java.util.ArrayList;
import java.util.List;

import corruptiongame.character.Character;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;

public class CombatState extends State {
	private Character player;
	private List<Character> enemy = new ArrayList<>();
	private int enemyCount;
	
	public CombatState(StateManager manager,Character player, int enemyCount){
		super(manager);
		this.player = player;
		this.enemyCount = enemyCount;
	}
	
	@Override
	public void init(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	


}
