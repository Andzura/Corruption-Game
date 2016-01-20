package corruptiongame.worldmap;

public class Event {
	 //used to make a trigger zone on the map which is the rectangle formed by (xUL,yUL) and (xDR, yDR)
	private int xUL,yUL, xDR,yDR;
	private boolean triggered;
	
	public Event(int xUL, int yUL,int xDR,int yDR){
		this.xUL = xUL;
		this.xDR = xDR;
		this.yUL = yUL;
		this.yDR = yDR;
		this.triggered = false;
	}
	public boolean checkTrigger(int x, int y){
		if(!triggered){
			if(x >= xUL && x <= xDR){
				if(y >= yUL && y <= yDR){
					triggered = true;
					return true;
				}	
			}
		}
		return false;
	}
}
