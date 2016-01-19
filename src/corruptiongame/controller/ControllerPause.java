package corruptiongame.controller;

import corruptiongame.character.RPGCharacter;
import corruptiongame.character.Stats;
import corruptiongame.item.Armor;
import corruptiongame.item.Consumable;
import corruptiongame.item.Item;
import corruptiongame.item.Weapon;

public class ControllerPause extends Controller{

	public ControllerPause(RPGCharacter player) {
		super(player);
	}
	
	public void use(Item item){
		if(item instanceof Consumable){
			((Consumable) item).use(player);
		}else if(item instanceof Armor){
			player.equipArmor((Armor)item);
		}else if(item instanceof Weapon){
			player.equipWeapon((Weapon)item);
		}
	}
	
	public void drop(Item item){
		player.dropItem(item);
	}
	
	public boolean canUse(Item item){
		if(item instanceof Consumable){
			if(item.getStats(Stats.DEFENSE) > 0){
				if(item.getStats(Stats.STRENGTH) > 0){
					return false;
				}
			}
			return true;
		}
		return true;
	}
		

}
