package corruptiongame.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import corruptiongame.item.Armor;
import corruptiongame.item.Item;
import corruptiongame.item.Weapon;


public class RPGCharacter {
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
	private int mapX,mapY,combatX,combatY;
	
	
	public RPGCharacter(String name, int startLevel, int maxHealth, int strength, int defense, int evil){
		this.name = name;
		this.level = startLevel;
		this.maxHealth = maxHealth;
		this.maxWeight = strength * 10;
		this.stats = new EnumMap<Stats,Integer>(Stats.class);
		this.stats.put(Stats.HEALTH, maxHealth);
		this.stats.put(Stats.STRENGTH, strength);
		this.stats.put(Stats.DEFENSE, defense);
		this.stats.put(Stats.EVIL, evil);
		this.stats.put(Stats.WEIGHT, 0);
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
		int defense = this.stats.get(Stats.DEFENSE);
		if(inventory.contains((Item) armor)){
			if(this.armor != null){
				defense -= this.armor.getDefense();
				this.stats.put(Stats.DEFENSE, defense);
			}
			this.armor = armor;
			defense += this.armor.getDefense();
			this.stats.put(Stats.DEFENSE, defense);
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
		int hpOld = stats.get(Stats.HEALTH);
		if(hpOld - hp <= 0){
			stats.put(Stats.HEALTH, 0);
			return true;
		}
		stats.put(Stats.HEALTH, hpOld - hp);
		return false;
	}
	
	/**
	 * @param hp, number of hp the character gain
	 *
	 */
	public void gainHealth(int hp){
		int hpOld = stats.get(Stats.HEALTH);
		if(hpOld + hp >= maxHealth)
			stats.put(Stats.HEALTH, maxHealth);
		else
			stats.put(Stats.HEALTH, hpOld + hp);
	}
	
	public boolean addItem(Item item){
		int weight = this.stats.get(Stats.WEIGHT);
		weight += item.getWeight();
		if(weight <= this.maxWeight){
			inventory.add(item);
			this.stats.put(Stats.WEIGHT, weight);
			return true;
		}
		return false;
	}
	
	public void dropItem(Item item){
		int weight = this.stats.get(Stats.WEIGHT);
		weight -= item.getWeight();
		if(inventory.remove(item))
			this.stats.put(Stats.WEIGHT, weight);
	}

}
