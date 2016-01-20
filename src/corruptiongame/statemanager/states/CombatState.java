package corruptiongame.statemanager.states;

import java.util.ArrayList;
import java.util.List;

import corruptiongame.character.Enemy;
import corruptiongame.character.RPGCharacter;
import corruptiongame.character.Stats;
import corruptiongame.controller.ControllerCombat;
import corruptiongame.item.Item;
import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;

public class CombatState extends State {
	private RPGCharacter player;
	private List<Enemy> enemy = new ArrayList<>();
	private int page;
	private final int maxNbItem = 3;
	private boolean hasNextPage;
	private List<Item> inventoryPage;
	private int enemyCount;
	private static String[] options = {"ACTION", "ITEM", "FLEE"};
	private String state;
	private ControllerCombat controller;
	private int choice;
	private boolean skillChoosed;
	
	public CombatState(StateManager manager, Keyboard keyboard){
		super(manager, keyboard);
		this.player = manager.getPlayer();
	}
	
	public void init(){
		this.player = manager.getPlayer();
		this.controller = new ControllerCombat(player,enemy);
		this.clearScreen(0x000000);
		this.state = "DEFAULT";
		this.fetchInventoryPage();
		this.page = 0;
	}

	@Override
	public void update(long elapsedTime) {
		boolean end = false;
		if(!controller.update(elapsedTime)){
			end = controller.checkEnd();
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
							if(choice == 0 && page > 0){
								page--;
								choice = 0;
							}else if(choice == 5 && (((page+1)*6) < player.getSkills().size() - 1)){
								page++;
								choice = 0;
							}else{
								skillChoosed = true;
								if(this.controller.chooseSkill(choice+(page*6))){
									page = 0;
									skillChoosed = false;
									state = "DEFAULT";
								}
								choice = 0;
							}
							
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
							this.controller.chooseTarget(enemy.get(choice));
							choice = 0;
						}
					}
				}		
				else if(state == "ITEM"){
					if(inventoryPage.size()  == 0){
						state = "DEFAULT";
					}
					if(keyboard.isUpTyped()){
						if(choice == 0 && page > 0){
							choice = -1;
							System.out.println(choice);
						}
						if(choice > 0)
							choice--;
						if(choice == -2)
							choice =  inventoryPage.size()-1;
						
						
					}
					if(keyboard.isDownTyped()){
						if(choice == inventoryPage.size()-1 && hasNextPage)
							choice = -2;
						if(choice < inventoryPage.size()-1 && choice >= 0)
							choice++;
						if(choice == -1)
							choice = 0;
					}
					if(keyboard.isEnterTyped()){
						if(choice == -1){
							System.out.println(choice);
							page--;
							choice = 0;
							this.fetchInventoryPage();
						}
						else if(choice == -2){
							page++;
							choice = 0;
							this.fetchInventoryPage();
						}
						else{
							page = 0;
							choice = 0;
							controller.chooseItem(choice+(page * maxNbItem));
							state = "DEFAULT";
							this.fetchInventoryPage();
						}
					}
				}
				else if(state == "FLEE"){
					end = true;
				}
				
			}
			
		if(end){
			manager.pop();
		}
	}

	@Override
	public void render() {
		Enemy enemy;
		
		//clear the combat region
		clearScreenRegion(0x0000000,0,0,Game.NBTILEW,2*(Game.NBTILEH/3));
		clearScreenRegion(0x0000000,0,2*(Game.NBTILEH/3)+1,Game.NBTILEW,Game.NBTILEH - 4);
		
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
				for(int i = 0; i < 6 && (i+(page*6)) < player.getSkills().size();i++){
					x = i/3;
					y = i%3;
					if(i == 0 && page > 0){
						if(choice == i)
							writeStringOnScreen("BACK",(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + y ,0xffffff,0x000000);
						else
							writeStringOnScreen("BACK",(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + y ,0x000000,0xffffff);
					}else if(i == 5 && (((page+1)*5) < player.getSkills().size() - 1)){
						if(choice == i)
							writeStringOnScreen("NEXT",(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + y ,0xffffff,0x000000);
						else
							writeStringOnScreen("NEXT",(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + y ,0x000000,0xffffff);
					}else{
					if(choice == i)
						writeStringOnScreen(player.getSkills().get(i+page*5).getName(),(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + y ,0xffffff,0x000000);
					else
						writeStringOnScreen(player.getSkills().get(i+page*5).getName(),(10 + (x*15)),2*(Game.NBTILEH/3) + 2 + y ,0x000000,0xffffff);
					}
				}
			}else{
				writeStringOnScreen(">",this.enemy.get(choice).getCombatX() - 1, this.enemy.get(choice).getCombatY(),0x000000,0xffffff);
			}
		}else if(state == "ITEM"){
			int i = 0;
			if(page > 0){
				if(choice == -1)
					writeStringOnScreen(">BACK", 5 , 2*(Game.NBTILEH/3)+i+1 , 0x000000, 0xffffff);
				else
					writeStringOnScreen("BACK", 5 , 2*(Game.NBTILEH/3)+i+1, 0x000000, 0xffffff);
			}
			for(i = 0; i < inventoryPage.size(); i++){
				if(choice == i)
					writeStringOnScreen(">" + inventoryPage.get(i).getName(), 5 , 2*(Game.NBTILEH/3)+i+2 , 0x000000, 0xffffff);
				else
					writeStringOnScreen(inventoryPage.get(i).getName(), 5 , 2*(Game.NBTILEH/3)+i+2, 0x000000, 0xffffff);
			}
			if(hasNextPage){
				if(choice == -2)
					writeStringOnScreen(">NEXT", 5 , 2*(Game.NBTILEH/3)+i+2 , 0x000000, 0xffffff);
				else
					writeStringOnScreen("NEXT", 5 , 2*(Game.NBTILEH/3)+i+2 , 0x000000, 0xffffff);
			}
		}else{
			
		}
		
		
		
	}
	
	public void fetchInventoryPage(){
		int idEndPage; 
		if(player.getInventory().isEmpty()){
			inventoryPage = player.getInventory().subList(0, 0);
			hasNextPage = false;
		}else{
			if( player.getInventory().size() <= (page+1)*maxNbItem){
				idEndPage = player.getInventory().size();
				hasNextPage = false;
			}
			else{
				idEndPage = (page+1)*maxNbItem;
				hasNextPage = true;
			}
			inventoryPage = player.getInventory().subList(page*maxNbItem, idEndPage);
		}
		
	}

	@Override
	public void exit(){
		enemyCount = 0;
		controller.exit();
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
