package corruptiongame.item;

import corruptiongame.character.Stats;

public class Armor extends Item {
    
	public Armor(String name, int defense, int evil, int health, int strength, int weight){
		super(name, defense, evil, health, strength, weight);
	}
        public Armor(String name){
		super(name);
	}
        public Armor(){
		super();
	}
        
	
	public Armor copy(){
		Armor copy = new Armor(this.getName(), this.getStats(Stats.DEFENSE), this.getStats(Stats.EVIL), this.getStats(Stats.HEALTH), this.getStats(Stats.STRENGTH), this.getStats(Stats.WEIGHT));
		return copy;
	}
}
