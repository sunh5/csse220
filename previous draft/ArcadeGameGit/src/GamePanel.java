import javax.swing.Box;
import javax.swing.BoxLayout;
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

	/**
	 * Display the component on game panel. Current it only display the simulation
	 * panel for the game. The score board will be implemented later.
	 */
	public GamePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		GameComponent gc = new GameComponent();
		this.add(Box.createVerticalStrut(10));
		this.add(gc);
		this.add(Box.createVerticalStrut(10));
	}
}
