package corruptiongame.graphics;

public enum Tiles {
	TREE ('T', 0xff0000,0x00ff00);
	
	private final char displayedChar;
	private final int background;
	private final int foreground;
	
	Tiles(char displayedChar, int background, int foreground){
		this.displayedChar = displayedChar;
		this.background = background;
		this.foreground = foreground;
	}
	
	
}
