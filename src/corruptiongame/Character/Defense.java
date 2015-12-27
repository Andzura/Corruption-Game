package corruptiongame.character;

public class Defense implements Skill {
	private String name;
	private STATS buffType;
	private int buffValue;
	private boolean selfOnly;
	
	/**
	 * @param src the character source of the defense skill
	 * @param target the target of the defense skill
	 * @return the value of the buff
	 */
	@Override
	public int perform(Character src, Character target) {
		int buff = 0;
		/*
		 * calculate the value of the buff, and apply it to the  target.
		 */
		return buff;
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

	public STATS getBuffType() {
		return buffType;
	}
	
	public boolean isSelfOnly() {
		return selfOnly;
	}
}
