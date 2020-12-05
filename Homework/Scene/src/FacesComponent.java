import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class FacesComponent extends JComponent {
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Face face = new Face(100,100,100,100*0.35,Color.yellow);
		face.drawOn(g2);
		
		Face face2 = new Face(400,100,50,50*0.35,Color.yellow);
		face2.drawOn(g2);
		Face face3 = new Face(200,100,75,75*0.35,Color.blue);
		face3.drawOn(g2);
		//Face eye = new Face(100);
		int posiX = 200;
		int posiY = 200;
		int angle  = 360/5;
		for (int i = 1; i < 7; i++) {
			g2.translate(posiX,posiY);
			g2.rotate(angle*i);
			face.drawOn(g2);
			g2.rotate(-angle*i);
			g2.translate(-posiX, -posiY);
		}
		
		
	}

}
