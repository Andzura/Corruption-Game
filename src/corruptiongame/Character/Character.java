package corruptiongame.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import corruptiongame.Item.Armor;
import corruptiongame.Item.Item;
import corruptiongame.Item.Weapon;


public class Character {
	private String name;
	private int level;
	private Map<Stats, Integer> stats;
	private List<Skill> skills;
	private int maxWeight;
	private int maxHealth;
	private int xp;
	private List<Item> inventory;
	private Armor armor;
	private Weapon weapon;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value of Stats s
	 * if Stats s isn't a key of the Map, return 0
	 */
	public int getStats(Stats s){
		if(stats.containsKey(s))
			return stats.get(s);
		else 
			return 0;
	}
	
	/**
	 * @return the level
	 */
	public int getLevel(){
		return level;
	}

	/**
	 * @return the maxWeight
	 */
	public int getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}
	
	/**
	 * @return the skills list
	 */
	public List<Skill> getSkills(){
		return  new ArrayList<Skill>(skills); // return a clone of the skills List, that way, we can't modify the original through this get method.
	}

	/**
	 * @return the inventory
	 */
	public List<Item> getInventory() {
		return new ArrayList<Item>(inventory);  // return a clone of the Inventory, that way, we can't modify the original through this get method.
	}

	/**
	 * @return the armor
	 */
	public Armor getArmor() {
		return armor;
	}

	/**
	 * @param armor the armor to equip
	 */
	public void equipArmor(Armor armor) {
		if(inventory.contains((Item) armor))
			this.armor = armor;
	}

	/**
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	
	/**
	 * @param weapon, the Weapon to equip
	 */
	public void equipWeapon(Weapon weapon) {
		if(inventory.contains((Item) weapon))
			this.weapon = weapon;
	}

}
