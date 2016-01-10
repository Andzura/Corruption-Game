package corruptiongame.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;

import me.grea.antoine.utils.Dice;
import me.grea.antoine.utils.Log;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	PrintStream ps = null;
		try {
			ps = new PrintStream(
			         new FileOutputStream ("Log.txt",true));
		} catch (FileNotFoundException e) {
			Log.e("Couldn't open Log.txt ! Log output while be written to the console from now on.");
		}
    	Log.level = Log.Level.DEBUG;
    	if(ps != null)
    		Log.out = ps;
    	Game game = new Game();
    	JFrame frame = game.getFrame();
    	frame.add(game);
    	frame.setTitle("TEST");
    	frame.setSize(Game.W, Game.H);
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
    	game.start();
    }
    
}
