package corruptiongame.character;

public class Defense implements Skill {
	private String name;
        private Stats boost;
        private int cost;
	
	public Defense(String name, Stats boost, int cost) {
		this.name = name;
		this.boost = boost;
        this.cost = cost;
	}

	/**
	 * @param src the character source of the defense skill
	 * @param target the target of the defense skill
	 * @return the value of the buff
	 */
	@Override
	public void perform(RPGCharacter src, RPGCharacter target) {
		target.modifyStats(boost, cost/100);
                src.modifyStats(Stats.EVIL, 0-cost/100*src.getStats(Stats.EVIL));
	}

        @Override
		public String getName() {
			return name;
		}

        public Stats getBoost(){
                return this.boost;
        }
        
        @Override
        public int getCost(){
                return this.cost;
        }
        
        @Override
        public Skill copy(){
                Defense copy = new Defense(this.name, this.boost, this.cost);
                return copy;
        }
}
