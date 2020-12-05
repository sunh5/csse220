import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The main class is to start the arcade game by opening a frame.
 * @author Jingwen Wu & Haoxuan Sun.
 * Created on 10/25/2019
 * 
 *
 */
public class Main {

	/**
	 * Open a frame and display the game panel
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Start the game!");
		JFrame frame = new JFrame();
		GamePanel panel = new GamePanel();
		frame.add(panel,BorderLayout.CENTER);
		
		Status s = new Status(panel.getComponent());
		frame.add(s,BorderLayout.NORTH);
		frame.setSize(new Dimension(1000,860));
		frame.setTitle("Arcade Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
