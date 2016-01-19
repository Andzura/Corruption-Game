package corruptiongame.character;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import corruptiongame.item.Armor;
import corruptiongame.item.Item;
import corruptiongame.item.Weapon;


public class RPGCharacter {
	protected String name;
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
		this.armor = new Armor("none", 0, 0, 0, 0, 0);
		this.weapon = new Weapon("fist", 0, 0, 0, 1, 0);
		this.inventory = new ArrayList<>();
		this.skills = new ArrayList<>();
		//this.skills.add(new Attack("Normal Attack", Stats.STRENGTH, 1));
		//this.skills.add(new Defense("Heal", Stats.HEALTH, 5, true));
		this.xp = 0;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the name
	 */
	public void setName(String name) {
		this.name = name;
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
	
	public Map<Stats, Integer> getAllStats(){
		Map<Stats,Integer> stats = new EnumMap<Stats,Integer>(Stats.class);
		stats.putAll(this.stats);
		return stats;
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
	
	
	public void gainXp(int xp) {
		this.xp += xp;
		if(xp > (level*50)){
			xp -= 50*level;
			level++;
		}
	}
	
	public void setStats(Stats stat, int value){
		this.stats.replace(stat, value);
	}
	/**
	 * @return the skills list
	 */
	public List<Skill> getSkills(){
		return  new ArrayList<Skill>(skills); 
	}
	
	public void addSkill(Skill s){
		skills.add(s);
	}

	public void addSkill(List<Skill> list){
		skills.addAll(list);
	}
	/**
	 * @return the inventory
	 */
	public List<Item> getInventory() {
		return new ArrayList<Item>(inventory);
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
		weight += item.getStats(Stats.WEIGHT);
		if(weight <= this.maxWeight){
			inventory.add(item);
			this.stats.put(Stats.WEIGHT, weight);
			return true;
		}
		return false;
	}
	
	public boolean addItem(List<Item> list){
		boolean ret = false;
		for(int i = 0; i < list.size(); i++){
			ret = this.addItem(list.get(i));
		}
		return ret;
	}
	
	
	public void dropItem(Item item){
		int weight = this.stats.get(Stats.WEIGHT);
		weight -= item.getStats(Stats.WEIGHT);
		if(inventory.remove(item))
			this.stats.put(Stats.WEIGHT, weight);
	}

	public int getCombatY() {
		return combatY;
	}

	public void setCombatY(int combatY) {
		this.combatY = combatY;
	}

	public int getMapX() {
		return mapX;
	}

	public void setMapX(int mapX) {
		this.mapX = mapX;
	}

	public int getCombatX() {
		return combatX;
	}

	public void setCombatX(int combatX) {
		this.combatX = combatX;
	}

	public int getMapY() {
		return mapY;
	}

	public void setMapY(int mapY) {
		this.mapY = mapY;
	}

	public void modifyStats(Stats stat, int value){
		this.stats.replace(stat, this.stats.get(stat) + value);
	}
}
