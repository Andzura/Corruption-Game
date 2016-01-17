package corruptiongame.character;

import java.util.ArrayList;
import java.util.List;

public class Enemies {
	private static List<Enemy> enemyList;
	
	public static void loadEnemies(){
		enemyList = new ArrayList<>();
		Enemy enemy1 = new Enemy("Flowey", 1, 2, 5, 5, 0);
		enemy1.setCombatX(20);
		enemy1.setCombatY(10); 
		enemyList.add(enemy1);
	}
	
	public static Enemy getEnemy(int id){
		return enemyList.get(id).copy();
	}
	
	public static List<Enemy> getEnemy(List<Integer> listId){
		List<Enemy> listEnemy = new ArrayList<>();
		for(int i = 0; i < listId.size(); i++){
			listEnemy.add(Enemies.getEnemy(listId.get(i)));
		}
		return listEnemy;
	}
}
