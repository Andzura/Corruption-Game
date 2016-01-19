package corruptiongame.character;

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
	 * @return the damage dealt to target 
	 * 
	 */
	@Override
	public void perform(RPGCharacter src, RPGCharacter target) {
		int attack = src.getWeapon().getStats(Stats.STRENGTH) + src.getArmor().getStats(Stats.STRENGTH);
		int stat_src = src.getStats(Stats.STRENGTH) + src.getStats(Stats.EVIL)*this.cost/100;
		int stat_target = target.getStats(Stats.DEFENSE)*1+(target.getLevel()-src.getLevel())/6;
                int defense = target.getArmor().getStats(Stats.DEFENSE);
		int damage = (attack + stat_src)*((int)(Math.random()*src.getStats(Stats.EVIL)/100)) - (stat_target + defense)*((int)(Math.random()*target.getStats(Stats.EVIL)/100));
                
                target.looseHealth(damage);
                src.modifyStats(Stats.EVIL, 0-cost);
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
