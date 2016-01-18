package corruptiongame.item;

import corruptiongame.character.Stats;


public class Consumable extends Item {
    
    public Consumable(String name, int defense, int evil, int health, int strength, int weight){
		super(name, defense, evil, health, strength, weight);
	}
        public Consumable(String name){
		super(name);
	}
        public Consumable(){
		super();
	}
	
	public Consumable copy(){
		Consumable copy = new Consumable(this.getName(), this.getStats(Stats.DEFENSE), this.getStats(Stats.EVIL), this.getStats(Stats.HEALTH), this.getStats(Stats.STRENGTH), this.getStats(Stats.WEIGHT));
		return copy;
	}
}
