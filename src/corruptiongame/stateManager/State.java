package corruptiongame.stateManager;

public abstract class State {
	
	private StateManager manager;

	public abstract void init();
	
	public abstract void update(float elapsedTime);
}
