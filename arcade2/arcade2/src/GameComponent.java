import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameComponent extends JComponent{
	private GameWorld world;

	public GameComponent(GameWorld world) {
		this.world=world;
		setPreferredSize(world.getSize());
		setMaximumSize(world.getSize());
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.world.drawBackground(g2);
		this.world.drawObjects(g2);
		repaint();
	}

}