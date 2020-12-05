

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 * A component that contains balls.
 * 
 * Modify this class as you need
 * 
 * 
 */
public class BallComponent extends JComponent {

	public BallComponent() {
		// this generates a random number between 0 and 1
		double random = Math.random();
	}
	
	//This method should draw anything considered to be part of the Component
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for(int row = 0; row < 20; row++) {
			for(int col = 0; col < 20; col++) {
				g2.setColor(Color.LIGHT_GRAY);
				g2.drawRect(col*30, row*30, 30, 30);
			}
		}
		
		//draw one example ball
		g2.setColor(Color.BLUE);
		g2.fillOval(0, 0, 30, 30);
		
	}

}
