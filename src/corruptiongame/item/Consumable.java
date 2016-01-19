package corruptiongame.item;

import corruptiongame.character.RPGCharacter;
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
        
        public void use(RPGCharacter charac){
                charac.setStats(Stats.DEFENSE, charac.getStats(Stats.DEFENSE)+this.getStats(Stats.DEFENSE));
                charac.setStats(Stats.EVIL, charac.getStats(Stats.EVIL)+this.getStats(Stats.EVIL));
                charac.setStats(Stats.HEALTH, charac.getStats(Stats.HEALTH)+this.getStats(Stats.HEALTH));
                charac.setStats(Stats.STRENGTH, charac.getStats(Stats.STRENGTH)+this.getStats(Stats.STRENGTH));
        }
	
	public Consumable copy(){
		Consumable copy = new Consumable(this.getName(), this.getStats(Stats.DEFENSE), this.getStats(Stats.EVIL), this.getStats(Stats.HEALTH), this.getStats(Stats.STRENGTH), this.getStats(Stats.WEIGHT));
		return copy;
	}
}
