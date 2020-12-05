import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {

	private static final int DELAY = 50;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		GameAdvancedListener advancedListener = new GameAdvancedListener(frame);
		Timer timer = new Timer(DELAY, advancedListener);
		timer.start();
		
	}

}
