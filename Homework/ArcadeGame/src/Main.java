import java.awt.Dimension;

import javax.swing.JFrame;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("Write your cool arcade game here!");
			System.out.println("Start the game!");
			JFrame frame = new JFrame();
			JFrame frame2 = new JFrame();
			
			GamePanel panel = new GamePanel();
			frame.add(panel);
			frame.setSize(new Dimension(1000,800));
			frame.setTitle("Arcade Game");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);	
	}

}
