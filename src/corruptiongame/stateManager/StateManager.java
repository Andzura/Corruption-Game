package corruptiongame.stateManager;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class StateManager {

		Map<String, State> states = new HashMap<String, State>();
	    Deque<State> stack = new ArrayDeque<State>();
	 
	    public void update(float elapsedTime)
	    {
	        State top = stack.peek();
	        top.update(elapsedTime);
	    }
	 
	    public void render()
	    {
	        State top = stack.peek();
	        //top.render();
	    }
	 
	    public void push(String name)
	    {
	        State state = states.get(name);
	        stack.push(state);
	    }
	 
	    public State Pop()
	    {
	        return stack.pop();
	    }
	
}
