import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is a JPanel that simulates the game components and shows the
 * status bar.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/25/2019
 */
public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameComponent game; 
	/**
	 * Display the component on game panel. Current it only display the simulation
	 * panel for the game. The score board will be implemented later.
	 */
	public GamePanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000,800));
		GameComponent gc = new GameComponent();
		this.game = gc;
		this.add(gc);
	}
	
	public GameComponent getComponent() {
		return this.game;
	}
}
