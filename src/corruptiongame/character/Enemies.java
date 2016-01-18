package corruptiongame.character;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.grea.antoine.utils.Log;

public class Enemies {
	private static List<Enemy> enemyList;
	
	public static void loadEnemies(){
		enemyList = new ArrayList<>();
		Scanner sc	= null;
		InputStream is = Enemies.class.getResourceAsStream("/enemy/enemy.txt");
		if(is != null)
			sc =new Scanner(is);
		else
			Log.f("Enemies file couldn't be found !");
		
		sc.useDelimiter("\\r?\\n");
		if(sc.hasNextInt()){
			int nbEnemies = sc.nextInt();
			String name;
			int level;
			int health;
			int strength;
			int defense;
			int evil;
			Enemy enemy = null;
			Scanner lsc = null;
			for(int i = 0; i < nbEnemies; i++){
				name = sc.next();
				lsc = new Scanner(sc.next());
				level = lsc.nextInt();
				health = lsc.nextInt();
				strength = lsc.nextInt();
				defense = lsc.nextInt();
				evil = lsc.nextInt();
				lsc.close();
				
				lsc = new Scanner(sc.next());
				enemy = new Enemy(name, level, health, strength, defense, evil);
				enemy.setCombatX(lsc.nextInt());
				enemy.setCombatY(lsc.nextInt());
				lsc.close();
				name = sc.next();
				name = name.replaceAll("\\\\n", System.getProperty("line.separator"));
				enemy.setStartName(name);
				name = sc.next();
				name = name.replaceAll("\\\\n", System.getProperty("line.separator"));
				enemy.setEndName(name);
				while(!(name = sc.next()).equals("")){
					name = name.replaceAll("\\\\n", System.getProperty("line.separator"));
					enemy.addAttackname(name);
				}
				enemyList.add(enemy);
			}
			
		}
		else{
			Log.f("Enemies file is corrupted!");
		}
		
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
