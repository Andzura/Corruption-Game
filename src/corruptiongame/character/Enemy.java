package corruptiongame.character;

import java.util.ArrayList;
import java.util.List;

import me.grea.antoine.utils.Dice;

public class Enemy extends RPGCharacter {
	private String startName;
	private List<String> attackName;
	private String endName;
	private String defaultName;
	
	public Enemy(String name, int startLevel, int maxHealth, int strength,
			int defense, int evil){
		super(name, startLevel, maxHealth, strength, defense, evil);
		defaultName = name;
		endName = name;
		startName = name;
		attackName = new ArrayList<>();
	}

	public void setStartName(String name){
		this.startName = name;
	}
	
	public String getStartName(){
		return startName;
	}
	
	public void addAttackname(String name){
		attackName.add(name);
	}
	
	public void addAttackname(List<String> name){
		attackName.addAll(name);
	}
	
	public List<String> getAttackName(){
		return attackName;
	}
	
	public void setEndName(String name){
		endName = name;
	}
	
	public String getEndName(){
		return endName;
	}
	
	public void applyStartName(){
		name = startName;
	}
	
	public void applyEndName(){
		name = endName;
	}
	
	public void applyDefaultName(){
		name = defaultName;
	}
	
	public void applyAttackName(){
		int n = Dice.roll(attackName.size());
		if( n != 0)
			name = attackName.get(n-1);
		name = attackName.get(0);
	}

	public Enemy copy() {
		Enemy copy = new Enemy(this.getName(), this.getLevel(), this.getMaxHealth(),
								this.getStats(Stats.STRENGTH), this.getStats(Stats.DEFENSE), this.getStats(Stats.EVIL));
		copy.setStats(Stats.HEALTH, this.getStats(Stats.HEALTH));
		copy.setStats(Stats.WEIGHT, this.getStats(Stats.WEIGHT));
		copy.setCombatX(this.getCombatX());
		copy.setCombatY(this.getCombatY());
		copy.setMapX(this.getMapX());
		copy.setMapY(this.getMapY());
		copy.addSkill(this.getSkills());
		copy.addItem(this.getInventory());
		copy.setStartName(this.getStartName());
		copy.setEndName(this.getEndName());
		copy.addAttackname(this.getAttackName());
		return copy;
		
	}
}
