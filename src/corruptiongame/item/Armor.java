package corruptiongame.item;

public class Armor extends Item {
	private int defense;

	
	public Armor(String name,int weight, int defense){
		super(name,weight);
		this.defense = defense;
	}
	
	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}
	
	public Armor copy(){
		Armor copy = new Armor(this.getName(), this.getWeight(), defense);
		return copy;
	}
}
