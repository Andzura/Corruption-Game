package corruptiongame.main;

import javax.swing.JFrame;

import me.grea.antoine.utils.Dice;

/**
 *
 * @author p1303674
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
