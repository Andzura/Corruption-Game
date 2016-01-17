package corruptiongame.worldmap;

import java.util.ArrayList;
import java.util.List;

public class EventCombat extends Event {
	private List<Integer> enemyId;
	
	public EventCombat(int xUL, int yUL, int xDR, int yDR) {
		super(xUL, yUL, xDR, yDR);
		enemyId = new ArrayList<>();
	}

	
	public List<Integer> getEnemiesId(){
		return new ArrayList<Integer>(enemyId);
	}
	
	public void addEnemyId(int enemy){
		enemyId.add(enemy);
	}
}
