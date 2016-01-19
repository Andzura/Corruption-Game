package corruptiongame.worldmap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.grea.antoine.utils.Log;
import corruptiongame.character.Enemies;

public class Events {
	private static List<Event> events = new ArrayList<>();;
	
	public static int size(){
		return events.size();
	}

	public static Event get(int i){
		return events.get(i);
	}
	
	public static void loadEvents(){
		Scanner sc	= null;
		InputStream is = Enemies.class.getResourceAsStream("/event/eventcombat.txt");
		if(is != null)
			sc =new Scanner(is);
		else
			Log.f("Events file couldn't be found !");
		
		sc.useDelimiter("\\r?\\n");
		if(sc.hasNextInt()){
			int nbEvents = sc.nextInt();
			int xUL,yUL,xDR,yDR;
			Event event = null;
			Scanner lsc = null;
			for(int i = 0; i < nbEvents; i++){
				lsc = new Scanner(sc.next());
				xUL = lsc.nextInt();
				yUL = lsc.nextInt();
				xDR = lsc.nextInt();
				yDR = lsc.nextInt();
				lsc.close();
				event = new EventCombat(xUL,yUL,xDR,yDR);
				lsc = new Scanner(sc.next());
				while(lsc.hasNextInt()){
					((EventCombat)event).addEnemyId(lsc.nextInt());
				}
				events.add(event);				
			}
			sc.close();
		}else
			Log.f("Events file corrupted !");
			
	}
}
