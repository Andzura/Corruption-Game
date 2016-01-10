package corruptiongame.character;

public class Attack implements Skill {
	
	private String name;
	private Stats attackType;
	private int statsMultiplier;

	
	
	
	 public Attack(String name, Stats attackType, int statsMultiplier){
		 this.name = name;
		 this.attackType = attackType;
		 this.statsMultiplier = statsMultiplier;
	 }
	/**
	 * @param src the character source of the attack skill
	 * @param target the target of the attack skill
	 * @return the damage dealt to target 
	 * 
	 */
	@Override
	public void perform(RPGCharacter src, RPGCharacter target) {
		int attack = src.getWeapon().getAttack();
		int stat = src.getStats(attackType);
		int defense = target.getStats(Stats.DEFENSE);
		int damage = attack + stat*this.statsMultiplier - defense*(1+target.getLevel()/2);
                
                target.looseHealth(damage);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the attackType
	 */
	public Stats getAttackType() {
		return attackType;
	}

	/**
	 * @return the statsMultiplier
	 */
	public int getStatsMultiplier() {
		return statsMultiplier;
	}
}
