package corruptiongame.main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.grea.antoine.utils.Log;
import corruptiongame.character.Enemies;

public class Start {
	public static String name;
	public static int level;
	public static int health;
	public static int strength;
	public static int defense;
	public static int evil;
	public static List<Integer> inventory;
	public static int mapX;
	public static int mapY;
	
	public static void loadStartValue() {
		Scanner sc = null;
		InputStream is = Enemies.class.getResourceAsStream("/start.txt");
		if(is != null)
			sc =new Scanner(is);
		else
			Log.f("Start Value file couldn't be found !");
		Scanner lsc;
		sc.useDelimiter("\\r?\\n");
		inventory = new ArrayList<>();
		name = sc.next();
		lsc = new Scanner(sc.next());
		level = lsc.nextInt();
		health = lsc.nextInt();
		strength = lsc.nextInt();
		defense = lsc.nextInt();
		evil = lsc.nextInt();
		lsc.close();
		
		lsc = new Scanner(sc.next());
		mapX = lsc.nextInt();
		mapY = lsc.nextInt();		
		lsc.close();
		
		lsc = new Scanner(sc.next());
		int i = 0;
		while(lsc.hasNextInt()){
			inventory.add(lsc.nextInt());
		}
		lsc.close();
		
	}


}
