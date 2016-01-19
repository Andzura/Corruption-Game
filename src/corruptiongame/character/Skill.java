package corruptiongame.character;

public interface Skill {
        
	public void perform(RPGCharacter src, RPGCharacter target);
	public String getName();
        public int getCost();
        public Skill copy();
}
