package corruptiongame.item;

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
	
	public Weapon copy(){
		Weapon copy = new Weapon(this.getName(), this.getWeight(), attack);
		return copy;
	}
}
