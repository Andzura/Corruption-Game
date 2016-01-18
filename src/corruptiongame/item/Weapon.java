package corruptiongame.item;

import corruptiongame.character.Stats;

public class Weapon extends Item {
	
	public Weapon(String name, int defense, int evil, int health, int strength, int weight){
		super(name, defense, evil, health, strength, weight);
	}
        public Weapon(String name){
		super(name);
	}
        public Weapon(){
		super();
	}
	
	public Weapon copy(){
		Weapon copy = new Weapon(this.getName(), this.getStats(Stats.DEFENSE), this.getStats(Stats.EVIL), this.getStats(Stats.HEALTH), this.getStats(Stats.STRENGTH), this.getStats(Stats.WEIGHT));
		return copy;
	}
}
