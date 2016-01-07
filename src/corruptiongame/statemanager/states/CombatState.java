package corruptiongame.statemanager.states;

import java.util.ArrayList;
import java.util.List;

import corruptiongame.character.RPGCharacter;
import corruptiongame.controller.ControllerCombat;
import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;

public class CombatState extends State {
	private RPGCharacter player;
	private List<RPGCharacter> enemy = new ArrayList<>();
	private int enemyCount;
	private static String[] options = {"ACTION", "ITEM", "FLEE"};
	private String state;
	private ControllerCombat controller;
	private boolean playerTurn;
	private int idSkill;
	private boolean skillChoosed;
	private int choice;
	
	public CombatState(StateManager manager,RPGCharacter player, Keyboard keyboard){
		super(manager, keyboard);
		this.player = player;
		choice = 0;
	}
	
	public void init(){
		this.controller = new ControllerCombat(player,enemy);
		this.clearScreen(0x000000);
		this.state = "DEFAULT";
	}

	@Override
	public void update(long elapsedTime) {
		if(keyboard.isRightTyped() && choice < options.length - 1)
			choice++;
		if(keyboard.isLeftTyped() && choice > 0)
			choice--;
		
	}

	@Override
	public void render() {
		RPGCharacter enemy;
		//render Enemies on screen
		for(int i = 0; i < enemyCount; i++){
			enemy = this.enemy.get(i);
			writeStringOnScreen(enemy.getName(), enemy.getCombatX(), enemy.getCombatY(), 0x000000, 0xffffff);
		}
		
		//render a white bar to separate menu
		for(int i = 0; i < Game.NBTILEW; i++){
			screenBackground[(Game.NBTILEH/3)*2*Game.NBTILEW + i]  = 0xffffff;
			screenForeground[(Game.NBTILEH/3)*2*Game.NBTILEW + i]  = 0xffffff;
		}
		
		if(state == "DEFAULT"){
			for(int i = 0; i < options.length;i++){
				if(choice == i)
					writeStringOnScreen(options[i],(Game.NBTILEW/(options.length+1))*(i+1),Game.NBTILEH - 3,0xffff00,0x000000);
				else
					writeStringOnScreen(options[i],(Game.NBTILEW/(options.length+1))*(i+1),Game.NBTILEH - 3,0xff5000,0x000000);
			}
		}else if(state == "ACTION"){
		
		}else if(state == "ITEM"){
			
		}else{
			
		}
		
		
		
	}

	@Override
	public void exit(){
		enemyCount = 0;
		enemy.clear();
	}

	public int getEnemyCount() {
		return enemyCount;
	}



	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public void addEnemy(RPGCharacter enemy){
		this.enemy.add(enemy);
		enemyCount = this.enemy.size();
	}
	


}
