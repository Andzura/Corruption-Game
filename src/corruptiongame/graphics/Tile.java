package corruptiongame.graphics;

public class Tile{	
	public char displayedChar;
	public int background;
	public int foreground;
	
	public static Tile TREE = new Tile((char) 0x00EF,0x000000,0x00AA00);
	public static Tile DARKTREE = new Tile((char) 0x00EF,0x000000,0x005000);
	public static Tile ROAD = new Tile((char) 0x00B0,0xcccccc,0x000000);
	public static Tile YELLOWFLOWER = new Tile((char) 0x00B0,0x000000,0xffff00);
	public static Tile BLACKFLOOR = new Tile(' ',0x000000,0x000000);
	public static Tile GRAYFLOOR = new Tile(' ',0xdddddd,0xdddddd);
	public static Tile SNOWYFLOOR = new Tile(' ',0xfffafa,0xfffafa);
	public static Tile FENCEHORIZONTAL = new Tile((char) 0x00CD,0xffffff,0x000000);
	public static Tile FENCEVERTICAL = new Tile((char) 0x00BA,0xffffff,0x000000);
	public static Tile FENCECORNERUL = new Tile((char) 0x00C9,0xffffff,0x000000);
	public static Tile FENCECORNERDL = new Tile((char) 0x00C8,0xffffff,0x000000);
	public static Tile FENCECORNERUR = new Tile((char) 0x00BB,0xffffff,0x000000);
	public static Tile FENCECORNERDR = new Tile((char) 0x00BC,0xffffff,0x000000);
	public static Tile WATER = new Tile((char)0x00F7, 0x000000, 0x000090);
	
	public static Tile[] TILESET ={BLACKFLOOR,		// tile  0
									GRAYFLOOR, 		//		 1
									SNOWYFLOOR,		//		 2
									YELLOWFLOWER,	//		 3
									ROAD,			//		 4
									TREE,			//		 5
									DARKTREE,		//		 6
									FENCEHORIZONTAL,//		 7
									FENCEVERTICAL,	//		 8
									FENCECORNERUL,	//		 9
									FENCECORNERDL,	//		10
									FENCECORNERUR,	//		11
									FENCECORNERDR,  //		12
									WATER};			//		13
	public static int STARTCOLLISIONTILE = 5;
	
	public Tile(char displayedChar, int background ,int foreground){
		this.displayedChar = displayedChar;
		this.background = background;
		this.foreground = foreground;
	}
}
