package corruptiongame.statemanager.states;

import java.util.ArrayList;
import java.util.List;

import corruptiongame.character.Enemy;
import corruptiongame.character.RPGCharacter;
import corruptiongame.character.Stats;
import corruptiongame.controller.ControllerCombat;
import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;

public class CombatState extends State {
	private RPGCharacter player;
	private List<Enemy> enemy = new ArrayList<>();
	private int enemyCount;
	private static String[] options = {"ACTION", "ITEM", "FLEE"};
	private String state;
	private ControllerCombat controller;
	private int choice;
	private int idSkill;
	private boolean skillChoosed;
	
	public CombatState(StateManager manager,RPGCharacter player, Keyboard keyboard){
		super(manager, keyboard);
		this.player = player;
	}
	
	public void init(){
		this.controller = new ControllerCombat(player,enemy);
		this.clearScreen(0x000000);
		this.state = "DEFAULT";
	}

	@Override
	public void update(long elapsedTime) {
		if(!controller.update(elapsedTime)){
				if(state == "DEFAULT"){
					if(keyboard.isRightTyped() && choice < options.length - 1)
						choice++;
					if(keyboard.isLeftTyped() && choice > 0)
						choice--;
					if(keyboard.isEnterTyped()){
						state = options[choice];
						choice = 0;
					}
				}
				else if(state == "ACTION"){
					if(!skillChoosed){
						if(keyboard.isDownTyped() && choice < player.getSkills().size() - 1)
							choice++;
						if(keyboard.isUpTyped() && choice > 0)
							choice--;
						if(keyboard.isEnterTyped()){
							if(this.controller.chooseSkill(choice)){
								this.controller.chooseTarget(player);
							}
							choice = 0;
							skillChoosed = true;
						}
					}
					else{
						if(keyboard.isRightTyped() && choice < enemyCount - 1)
							choice++;
						if(keyboard.isLeftTyped() && choice > 0)
							choice--;
						if(keyboard.isEnterTyped()){
							state = "DEFAULT";
							skillChoosed = false;
							this.clearScreen(0x000000);
							this.controller.chooseTarget(enemy.get(choice));
							choice = 0;
						}
					}
				}		
				else if(state == "ITEM"){
				
				}
				else if(state == "FLEE"){
					
				}
			}
		boolean end = controller.checkEnd();
		if(end){
			manager.pop();
		}
	}

	@Override
	public void render() {
		Enemy enemy;
		
		//clear the combat region
		clearScreenRegion(0x0000000,0,0,Game.NBTILEW,2*(Game.NBTILEH/3));
		
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
		
		writeStringOnScreen(player.getName(), 0, Game.NBTILEH - 4, 0x000000, 0xffffff);
		String hp = String.valueOf(player.getStats(Stats.HEALTH));
		String paddedHp = "00".substring(hp.length()) + hp;
		
		writeStringOnScreen( paddedHp + "/" + String.valueOf(player.getMaxHealth()) + "HP",
							0, Game.NBTILEH - 3, 0x000000, 0xffffff);
		if(state == "DEFAULT"){
			for(int i = 0; i < options.length;i++){
				if(choice == i){
					writeStringOnScreen(" " + options[i],(Game.NBTILEW/(options.length+1))*(i+1),Game.NBTILEH - 3,0xffff00,0x000000);
					writeStringOnScreen(String.valueOf((char)0xff02),(Game.NBTILEW/(options.length+1))*(i+1),Game.NBTILEH - 3,0xffff00,0xff0000);				
				}
				else
					writeStringOnScreen(" "+ options[i],(Game.NBTILEW/(options.length+1))*(i+1),Game.NBTILEH - 3,0xff5000,0x000000);
			}
		}else if(state == "ACTION"){
			if(!skillChoosed){
				int x,y;
				for(int i = 0; i < player.getSkills().size();i++){
					x = i/3;
					y = i%3;
					if(choice == i)
						writeStringOnScreen(player.getSkills().get(i).getName(),(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + (y*3) ,0xffffff,0x000000);
					else
						writeStringOnScreen(player.getSkills().get(i).getName(),(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + (y*3) ,0x000000,0xffffff);
				}
			}else{
				writeStringOnScreen(">",this.enemy.get(choice).getCombatX() - 1, this.enemy.get(choice).getCombatY(),0x000000,0xffffff);
			}
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
	
	public void addEnemy(Enemy enemy){
		this.enemy.add(enemy);
		enemyCount = this.enemy.size();
	}
	
	public void addEnemy(List<Enemy> listEnemy){
		for(int i = 0; i < listEnemy.size(); i++){
			this.addEnemy(listEnemy.get(i));
		}
	}
	


}
