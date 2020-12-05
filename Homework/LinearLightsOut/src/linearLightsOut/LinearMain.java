package linearLightsOut;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
//import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Runs the Linear Lights Out application.
 * 
 * @author < Haoxuan Sun>. Created OCT 13, 2019.
 */
public class LinearMain {

	/**
	 * Starts here.
	 * 
	 * @param args
	 */
	public static String setButtons() {
		Random var = new Random(); // Use Javadoc Random An instance of this class is
									// used to generate a stream ofpseudorandom numbers.
		int n = var.nextInt(2) + 0;
		if (n == 0) {
			return "X";
		}
		return "O";
	}

	public static void main(String[] args) {
		String nButtonsString = JOptionPane.showInputDialog("How many buttons would you like?");
		int nButtons = Integer.parseInt(nButtonsString);
		JFrame myFrame = new JFrame("Linear Light Out...");
		// you'll want to think about how you want to manage the state of the game
		// also you want to add some buttons and stuff
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		myFrame.add(panel1, BorderLayout.NORTH);
		myFrame.add(panel2, BorderLayout.SOUTH);

		JButton newGame = new JButton("New Game");
		JButton quit = new JButton("Quit");

		panel2.add(newGame);
		panel2.add(quit);

		JButton[] buttons = new JButton[nButtons];
		ActionListener listener = new AddListener(buttons, newGame, quit, myFrame, nButtons);

		for (int i = 0; i < nButtons; i++) {
			JButton but = new JButton(setButtons());
			but.addActionListener(listener);
			buttons[i] = but;
			panel1.add(buttons[i]);
		}

		newGame.addActionListener(listener);
		quit.addActionListener(listener);
		myFrame.pack();
		myFrame.setVisible(true);
	}
}
