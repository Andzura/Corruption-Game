package corruptiongame.statemanager;

public abstract class State {
	
	protected StateManager manager;
	protected char[][] screen;
	
	public State(StateManager manager){
		this.manager = manager;
	}
	
	public abstract void init();
	
	public abstract void update(float elapsedTime);
	
	public abstract void render();
	
	public char[][] getScreen(){
		return screen;
	}
}
