
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * The main which creates the window.
 * 
 * Modify this class as you need.
 * 
 */
public class BallMain {

	public static final int DELAY=10;
	
	public static void main(String[] args) {
		new BallMain();
	}

	public BallMain() {
		JFrame frame = new JFrame("Final Question");
		BallComponent component = new BallComponent();
		component.setPreferredSize(new Dimension(600, 600));
		
		frame.add(component, BorderLayout.CENTER);
					
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
