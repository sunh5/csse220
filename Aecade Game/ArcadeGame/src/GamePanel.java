import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * This class is a JPanel that simulates the game components
 * and shows the status bar.
 * 
 * @author Jingwen Wu & Haoxuan Sun
 * Created on 10/25/2019
 */
public class GamePanel extends JPanel {
	
	public GamePanel() {
		 this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		 GameComponent gc = new GameComponent();
		 this.add(Box.createVerticalStrut(10));
		 this.add(gc);	
		 this.add(Box.createVerticalStrut(10));
	}
}
