package corruptiongame.character;

import me.grea.antoine.utils.Dice;

public class Attack implements Skill {
	
        private String name;
        private int cost;

	
	
	
	 public Attack(String name, int cost){
		 this.name = name;
		 this.cost = cost;
	 }
	/**
	 * @param src the character source of the attack skill
	 * @param target the target of the attack skill
	 * 
	 */
	@Override
	public void perform(RPGCharacter src, RPGCharacter target) {
                int evilLost = 0-src.getStats(Stats.EVIL)*cost/100;
		int attack = src.getFullStats(Stats.STRENGTH) + src.getFullStats(Stats.EVIL)*this.cost/100;
		int defense = target.getFullStats(Stats.DEFENSE);
		int damage = attack - defense;
                
                target.looseHealth(Math.max(1,damage));
                src.modifyStats(Stats.EVIL, evilLost);
	}

	/**
	 * @return the name
	 */
        @Override
	public String getName() {
		return name;
	}

	/**
	 * @return the cost in EVIL to the attack
	 */
        @Override
	public int getCost() {
		return cost;
	}
        
        @Override
        public Skill copy(){
                Attack copy = new Attack(this.name, this.cost);
                return copy;
        }
}
