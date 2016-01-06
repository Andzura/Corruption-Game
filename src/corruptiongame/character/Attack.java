package corruptiongame.character;

public class Attack implements Skill {
	
	private String name;
	private Stats attackType;
	private int statsMultiplier;

	/**
	 * @param src the character source of the attack skill
	 * @param target the target of the attack skill
	 * @return the damage dealt to target 
	 * 
	 */
	@Override
	public int perform(RPGCharacter src, RPGCharacter target) {
		int attack = src.getWeapon().getAttack();
		int stat = src.getStats(attackType);
		int defense = target.getStats(Stats.DEFENSE);
		int damage = 0;
		/*
		 * To do: calculate damage ( with the attack of the src, the defense of the target, and the attackType), and apply this damage to target.
		 */
		return damage;
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
