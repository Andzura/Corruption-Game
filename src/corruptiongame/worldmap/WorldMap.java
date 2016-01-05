package corruptiongame.worldmap;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import me.grea.antoine.utils.Log;

public class WorldMap {
	private int sizeX;
	private int sizeY;
	private char[] map;
	
	public WorldMap(String path){
		Scanner sc	= null;
		InputStream is = getClass().getResourceAsStream(path);
		if(is != null)
			sc =new Scanner(is);
		else
			Log.f("Map file couldn't be found !");
		boolean error = false;
		if(sc.hasNextInt()){
			sizeX = sc.nextInt();
			if(sc.hasNextInt())
				sizeY = sc.nextInt();
			else
				error = true;
			map = new char[sizeX*sizeY];
			int lineX = 0;
			int lineY = 0;
			String s;
			sc.nextLine();
			while(sc.hasNextLine() && lineY < sizeY){
				s = sc.nextLine();
				Log.d(s);
				Scanner lsc = new Scanner(s);
				while(lsc.hasNextInt() && lineX < sizeX){
					map[sizeX*lineY+lineX] = (char)lsc.nextInt();
					lineX++;
				}
				if(lineX < (sizeX - 1))
					error = true;
				lineX = 0;
				lineY++;
				lsc.close();
			}
			if(lineY < (sizeY-1))
				error = true;
		}else
			error = true;
		if(error){
			Log.f("Map file isn't valid or is corrupted. can't create the WorldMap from this file.");
		}
		
		sc.close();
	}
	
}
