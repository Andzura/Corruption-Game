package corruptiongame.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import corruptiongame.Item.Armor;
import corruptiongame.Item.Item;
import corruptiongame.Item.Weapon;


public class Character {
	private String name;
	private int level;
	private Map<STATS, Integer> stats;
	private List<Skill> skills;
	private int maxWeight;
	private int maxHealth;
	private int xp;
	private List<Item> inventory;
	private Armor armor;
	private Weapon weapon;
	
	
	public Character(String name, int startLevel, int maxHealth, int strength, int defense, int evil){
		this.name = name;
		this.level = startLevel;
		this.maxHealth = maxHealth;
		this.maxWeight = strength * 10;
		this.stats = new EnumMap<STATS,Integer>(STATS.class);
		this.stats.put(STATS.HEALTH, maxHealth);
		this.stats.put(STATS.STRENGTH, strength);
		this.stats.put(STATS.DEFENSE, defense);
		this.stats.put(STATS.EVIL, evil);
		this.stats.put(STATS.WEIGHT, 0);
		this.armor = new Armor("none", 0, 0);
		this.weapon = new Weapon("fist", 0, 1);
		this.inventory = new ArrayList<>();
		this.skills = new ArrayList<>();
		this.xp = 0;
		
	}
	
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
	public int getStats(STATS s){
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
		int defense = this.stats.get(STATS.DEFENSE);
		if(inventory.contains((Item) armor)){
			if(this.armor != null){
				defense -= this.armor.getDefense();
				this.stats.put(STATS.DEFENSE, defense);
			}
			this.armor = armor;
			defense += this.armor.getDefense();
			this.stats.put(STATS.DEFENSE, defense);
		}
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
	
	/**
	 * @param hp, number of hp the character loose
	 * @return true if the character died while loosing health (ie health = 0), else return false
	 */
	public boolean looseHealth(int hp){
		int hpOld = stats.get(STATS.HEALTH);
		if(hpOld - hp <= 0){
			stats.put(STATS.HEALTH, 0);
			return true;
		}
		stats.put(STATS.HEALTH, hpOld - hp);
		return false;
	}
	
	/**
	 * @param hp, number of hp the character gain
	 *
	 */
	public void gainHealth(int hp){
		int hpOld = stats.get(STATS.HEALTH);
		if(hpOld + hp >= maxHealth)
			stats.put(STATS.HEALTH, maxHealth);
		else
			stats.put(STATS.HEALTH, hpOld + hp);
	}
	
	public boolean addItem(Item item){
		int weight = this.stats.get(STATS.WEIGHT);
		weight += item.getWeight();
		if(weight <= this.maxWeight){
			inventory.add(item);
			this.stats.put(STATS.WEIGHT, weight);
			return true;
		}
		return false;
	}
	
	public void dropItem(Item item){
		int weight = this.stats.get(STATS.WEIGHT);
		weight -= item.getWeight();
		if(inventory.remove(item))
			this.stats.put(STATS.WEIGHT, weight);
	}

}
