import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SunComponent extends JComponent {

	private static final double LITTLE_SUN_SIZE = 30.0;
	private static final double LITTLE_SUN_SEPARATION = 100.0;
	private static final int NUM_LITTLE_SUNS = 5;
	private static final double LITTLE_SUNS_Y = 400.0;
	private static final Color LITTLE_SUN_COLOR = Color.RED;
	private static final double LITTLE_SUNS_X_OFFSET = 50;

	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2 = (Graphics2D) graphics;

		Sun sun = new Sun();
		sun.drawOn(g2);

		
		g2.drawRect(30, 30, 240, 240);
		sun = new Sun(550, 100, 50, Color.BLUE);
		sun.drawOn(g2);
		g2.drawRect(515, 65, 120, 120);
		
		
		
		double x = SunComponent.LITTLE_SUNS_X_OFFSET;
		for (int i = 0; i < SunComponent.NUM_LITTLE_SUNS; i++) {	
		  sun = new Sun(x,SunComponent.LITTLE_SUNS_Y,SunComponent.LITTLE_SUN_SIZE, 
				  SunComponent.LITTLE_SUN_COLOR);
		  
		  sun.drawOn(g2);
		  x+= SunComponent.LITTLE_SUN_SEPARATION;

		}

		

	}
}
