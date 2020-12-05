import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class PineTreesComponent extends JComponent {
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;

		// TODO: Implement the House class constructor to take the house's
		// color and the (x,y) location of the upper-left corner of the house
		// body. Then implement a drawOn() function that draws the house, as specified in the assignment.
		// When done, you should see two houses when you run HousesViewer.
		Color brown = new Color(145, 112, 33);
		Color green = new Color(40, 135, 22);
				
		PineTrees tree = new PineTrees(100, 100, 100, 200);
	    tree.drawOn(graphics2);

	    PineTrees littleTree = new PineTrees(300, 200, 50, 100);	      
	    littleTree.drawOn(graphics2);

		// I found the RGB values for the aqua color from colorpicker.com
		
		
	}

}
