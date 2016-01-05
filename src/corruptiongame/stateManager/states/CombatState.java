package corruptiongame.statemanager.states;

import java.util.ArrayList;
import java.util.List;

import corruptiongame.character.RPGCharacter;
import corruptiongame.controller.ControllerCombat;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;

public class CombatState extends State {
	private RPGCharacter player;
	private List<RPGCharacter> enemy = new ArrayList<>();
	private int enemyCount;
	private ControllerCombat controller;
	private boolean playerTurn;
	
	public CombatState(StateManager manager,RPGCharacter player, Keyboard keyboard){
		super(manager, keyboard);
		this.player = player;
	}
	
	public void init(){
		this.controller = new ControllerCombat(player,enemy);
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
	public void exit(){
		setEnemyCount(0);
		enemy.clear();
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	
	


}
