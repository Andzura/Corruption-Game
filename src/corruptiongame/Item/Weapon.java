package corruptiongame.Item;

public class Weapon extends Item {
	private int attack;
	
	public Weapon(String name, int weight, int attack) {
		super(name, weight);
		this.attack = attack;
	}

	/**
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}
	
}
