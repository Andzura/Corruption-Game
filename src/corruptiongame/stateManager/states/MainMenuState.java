package corruptiongame.statemanager.states;

import me.grea.antoine.utils.Dice;
import corruptiongame.main.Game;
import corruptiongame.main.Keyboard;
import corruptiongame.statemanager.State;
import corruptiongame.statemanager.StateManager;

public class MainMenuState extends State{
	private int choice = 0;
	private String[] options = { "NEW GAME" , "QUIT" };
	private String title = "The Corruption Game";
	
	public MainMenuState(StateManager manager, Keyboard keyboard){
		super(manager, keyboard);
	}
	
	public void init() {
		this.clearScreen(0x00ff00);
	}

	private void select(){
		switch(choice){
			case 0:
				manager.push("MAP");
				break;
			case 1:
				System.exit(0);
		}
	}
	
	@Override
	public void update(long elapsedTime) {
		if(keyboard.isDownTyped() && choice < options.length - 1)
			choice++;
		if(keyboard.isUpTyped() && choice > 0)
			choice--;
		if(keyboard.isEnterTyped()){
			select();			
		}
		
	}

	@Override
	public void render() {
		this.writeStringOnScreen(title, (Game.NBTILEW)/2 - title.length()/2, 3, 0, 0xff0000);
		for(int i  = 0; i < options.length; i++){
			if(choice == i)
				this.writeStringOnScreen(options[i], (Game.NBTILEW)/2 - options[i].length()/2, (Game.NBTILEH/3)+(4*i), 0xffffff, 0);
			else
				this.writeStringOnScreen(options[i], (Game.NBTILEW)/2 - options[i].length()/2, (Game.NBTILEH/3)+(4*i), 0, 0xffffff);
		}
	}
	
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
