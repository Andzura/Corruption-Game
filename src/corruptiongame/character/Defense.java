package corruptiongame.character;

public class Defense implements Skill {
	private String name;
	private Stats buffType;
	private int buffValue;
	private boolean selfOnly;
	
	public Defense(String name, Stats buffType, int buffValue, boolean selfOnly) {
		this.name = name;
		this.buffType = buffType;
		this.buffValue = buffValue;
		this.selfOnly = selfOnly;
	}

	/**
	 * @param src the character source of the defense skill
	 * @param target the target of the defense skill
	 * @return the value of the buff
	 */
	@Override
	public void perform(RPGCharacter src, RPGCharacter target) {
		int buff = buffValue*(1+src.getLevel()/5);
		/*
		 * calculate the value of the buff, and apply it to the  target.
		 */
	}

	public String getName() {
		return name;
	}

	public int getBuffValue() {
		return buffValue;
	}

	public void setBuffValue(int buffValue) {
		this.buffValue = buffValue;
	}

	public Stats getBuffType() {
		return buffType;
	}
	
	public boolean isSelfOnly() {
		return selfOnly;
	}
}
