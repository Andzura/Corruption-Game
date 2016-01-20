package corruptiongame.statemanager.states;

import java.util.List;

import corruptiongame.character.RPGCharacter;
import corruptiongame.character.Stats;
import corruptiongame.controller.ControllerPause;
import corruptiongame.item.Item;
import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;

public class PauseState extends State {
	private RPGCharacter player;
	private int pageItem;
	private int choice; 
	private final int maxNbItem = 10;
	private boolean hasNextPage;
	private List<Item> inventoryPage;
	private boolean selected;
	private int itemSelected;
	private ControllerPause controller;
	
	public PauseState(StateManager manager, Keyboard keyboard) {
		super(manager, keyboard);
		this.player = manager.getPlayer();
		this.pageItem = 0;
		this.choice = 0;
		this.hasNextPage = false;
		this.selected = false;
		this.itemSelected = 1;
		this.controller = new ControllerPause(player);
		
	}
	
	public PauseState(StateManager manager,RPGCharacter player, Keyboard keyboard) {
		this(manager, keyboard);
		this.player = player;		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.clearScreenRegion(0xffffff, 2, 2, Game.NBTILEW-2, Game.NBTILEH-2);
		this.fetchInventoryPage();
	}


	@Override
	public void update(long elapsedTime) {
		if(keyboard.isEscapeTyped()){
			manager.pop();
		}
		if(!selected){
			if(keyboard.isUpTyped()){
				if(itemSelected == 1 && pageItem > 0)
					itemSelected = -1;
				if(itemSelected > 1)
					itemSelected--;
				if(itemSelected == -2)
					itemSelected =  inventoryPage.size();
				
				
			}
			if(keyboard.isDownTyped()){
				if(itemSelected == inventoryPage.size() && hasNextPage)
					itemSelected = -2;
				if(itemSelected < inventoryPage.size() && itemSelected > 0)
					itemSelected++;
				if(itemSelected == -1 && inventoryPage.size() > 0)
					itemSelected = 1;
			}
			if(keyboard.isEnterTyped()){
				if(itemSelected == -1){
					pageItem--;
					itemSelected = 1;
					this.fetchInventoryPage();
				}
				if(itemSelected == -2){
					pageItem++;
					itemSelected = 1;
					this.fetchInventoryPage();
				}
				else{
					choice = 0;
					selected = true;
				}
					
			}
		}else{
			if(keyboard.isLeftTyped()){
				if(choice < 1 || (choice < 2 && controller.canUse(inventoryPage.get(itemSelected-1)))){
					choice++;
				}
			}
			else if(keyboard.isRightTyped()){
				if(choice > 0)
					choice--;
			}else if(keyboard.isEnterTyped()){
				if(choice == 0){
					itemSelected = 1;
					selected = false;
				}
				else if(choice == 1){
					controller.drop(inventoryPage.get(itemSelected-1));
					selected = false;
					itemSelected = 1;
					this.fetchInventoryPage();
				}else if(choice == 2){
					controller.use(inventoryPage.get(itemSelected-1));
					selected = false;
					itemSelected = 1;
					this.fetchInventoryPage();
				}
			}
		}
	}

	@Override
	public void render() {
		this.clearScreenRegion(0x000000, 3, 3, Game.NBTILEW-3, Game.NBTILEH-3);
		writeStringOnScreen(player.getName(), 5 , 5 , 0x000000, 0xffffff);
		
		//display Stats on screen
		writeStringOnScreen("HP: "+ player.getFullStats(Stats.HEALTH) + "/" + player.getMaxHealth(), 6 , 6 , 0x000000, 0xffffff);
		writeStringOnScreen("STRENGTH: "+ player.getFullStats(Stats.STRENGTH), 20, 5 , 0x000000, 0xffffff);
		writeStringOnScreen("DEFENSE: "+ player.getFullStats(Stats.DEFENSE), 20, 6 , 0x000000, 0xffffff);
		writeStringOnScreen("WEIGHT: "+ player.getFullStats(Stats.WEIGHT)+ "/" + player.getMaxWeight(), 20, 7 , 0x000000, 0xffffff);
		writeStringOnScreen("???: "+ player.getFullStats(Stats.EVIL), 20, 8 , 0x000000, 0xffffff);
		
		writeStringOnScreen("INVENTORY:", 5 , 9 , 0x000000, 0xffffff);
		int i = 0;
		if(pageItem > 0){
			if(itemSelected == -1)
				writeStringOnScreen(">BACK", 5 , 10+i , 0x000000, 0xffffff);
			else
				writeStringOnScreen("BACK", 5 , 10+i , 0x000000, 0xffffff);
		}
		for(i = 1; i <= inventoryPage.size(); i++){
			if(itemSelected == i)
				writeStringOnScreen(">" + inventoryPage.get(i-1).getName(), 5 , 10+i , 0x000000, 0xffffff);
			else
				writeStringOnScreen(inventoryPage.get(i-1).getName(), 5 , 10+i , 0x000000, 0xffffff);
		}
		if(hasNextPage){
			if(itemSelected == -2)
				writeStringOnScreen(">NEXT", 5 , 10+i , 0x000000, 0xffffff);
			else
				writeStringOnScreen("NEXT", 5 , 10+i , 0x000000, 0xffffff);
		}
		//display Item Stats on screen
		
		if(itemSelected != -1 && itemSelected != -2){
			String sample = "STRENGTH: "+ inventoryPage.get(itemSelected-1).getStats(Stats.STRENGTH);
			writeStringOnScreen("ITEM DESCRIPTION:", 20 , 9 , 0x000000, 0xffffff);
			writeStringOnScreen("HP: "+ inventoryPage.get(itemSelected-1).getStats(Stats.HEALTH), (Game.NBTILEW-3) - sample.length() , 11 , 0x000000, 0xffffff);
			writeStringOnScreen("STRENGTH: "+ inventoryPage.get(itemSelected-1).getStats(Stats.STRENGTH),(Game.NBTILEW-3) - sample.length(), 12 , 0x000000, 0xffffff);
			writeStringOnScreen("DEFENSE: "+ inventoryPage.get(itemSelected-1).getStats(Stats.DEFENSE),(Game.NBTILEW-3) - sample.length(), 13 , 0x000000, 0xffffff);
			writeStringOnScreen("WEIGHT: "+ inventoryPage.get(itemSelected-1).getStats(Stats.WEIGHT), (Game.NBTILEW-3) - sample.length(), 14 , 0x000000, 0xffffff);
			writeStringOnScreen("???: "+ inventoryPage.get(itemSelected-1).getStats(Stats.EVIL), (Game.NBTILEW-3) - sample.length(), 15 , 0x000000, 0xffffff);
		}
		
		if(selected){
			if(choice == 0)
				writeStringOnScreen(">BACK", 4*(Game.NBTILEW)/5 , (Game.NBTILEH) - 5 , 0x000000, 0xffffff);
			else
				writeStringOnScreen("BACK", 4*(Game.NBTILEW)/5 , (Game.NBTILEH) - 5 , 0x000000, 0xffffff);
			if(choice == 1)
				writeStringOnScreen(">DROP", 3*(Game.NBTILEW)/5 , (Game.NBTILEH) - 5 , 0x000000, 0xffffff);
			else
				writeStringOnScreen("DROP", 3*(Game.NBTILEW)/5 , (Game.NBTILEH) - 5 , 0x000000, 0xffffff);
			if(controller.canUse(inventoryPage.get(itemSelected - 1))){
				if(choice == 2)
					writeStringOnScreen(">USE", 2*(Game.NBTILEW)/5 , (Game.NBTILEH) - 5 , 0x000000, 0xffffff);
				else
					writeStringOnScreen("USE", 2*(Game.NBTILEW)/5 , (Game.NBTILEH) - 5 , 0x000000, 0xffffff);
			}
		}
	}

	
	public void fetchInventoryPage(){
		int idEndPage;
		if(player.getInventory().isEmpty()){
			inventoryPage = player.getInventory().subList(0, 0);
			hasNextPage = false;
			itemSelected = -1;
		}else{
			if(player.getInventory().size() > (pageItem)*maxNbItem){
				if( player.getInventory().size() <= (pageItem+1)*maxNbItem){
					idEndPage = player.getInventory().size();
					hasNextPage = false;
				}
				else{
					idEndPage = (pageItem+1)*maxNbItem;
					hasNextPage = true;
				}
			
			inventoryPage = player.getInventory().subList(pageItem*maxNbItem, idEndPage);
			}else{
				pageItem--;
				this.fetchInventoryPage();
			}
			itemSelected = 1;
		}
		
	}
	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
