package corruptiongame.item;

import corruptiongame.character.Stats;
import java.util.EnumMap;
import java.util.Map;

public class Item{
	private String name;
        private Map<Stats, Integer> stats;
        
	public Item(String name, int defense, int evil, int health, int strength, int weight){
		this.stats = new EnumMap<Stats,Integer>(Stats.class);
                
		this.name = name;
                
		this.stats.put(Stats.DEFENSE, defense);
		this.stats.put(Stats.EVIL, evil);
                this.stats.put(Stats.HEALTH, health);
		this.stats.put(Stats.STRENGTH, strength);
		this.stats.put(Stats.WEIGHT, weight);
	}
        public Item(String name){
            this(name, 0, 0, 0, 0, 0);
        }
        public Item(){
            this("void");
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
	
	public Map<Stats, Integer> getAllStats(){
		Map<Stats,Integer> stats = new EnumMap<Stats,Integer>(Stats.class);
		stats.putAll(this.stats);
		return stats;
	}
	
	public Item copy(){
		Item copy = new Item(name, this.getStats(Stats.DEFENSE), this.getStats(Stats.EVIL), this.getStats(Stats.HEALTH), this.getStats(Stats.STRENGTH), this.getStats(Stats.WEIGHT));
		return copy;
	}
	 
}
