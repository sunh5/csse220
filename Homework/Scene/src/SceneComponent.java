import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SceneComponent extends JComponent {
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2 = (Graphics2D) graphics;

		g2.setColor(Color.blue);
		g2.drawRect(0, 0, 750, 375);
		g2.fillRect(0, 0, 750, 375);
		
		g2.setColor(Color.green);
		g2.drawRect(0, 375, 750, 600);
		g2.fillRect(0, 375, 750, 600);
		
		Sun sun = new Sun(100,100,100,Color.yellow);
		sun.drawOn(g2);
		
		for (int i = 0; i < 5; i++) {
			House houses = new House(50+i*130,450,Color.red);
			houses.drawOn(g2);
		}
		
		for (int i = 0; i < 20; i++) {
			PineTrees trees = new PineTrees(200+30*i,360,15,60);
			trees.drawOn(g2);
			PineTrees smalltrees = new PineTrees(210+25*i,340,10,40);
			smalltrees.drawOn(g2);
		}
		

		}

}
