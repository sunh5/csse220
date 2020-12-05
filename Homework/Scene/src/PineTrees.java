import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class PineTrees {
	private int HEIGHT = 0;
	private int WIDTH = 0;
	
	private int xCorner;
	private int yCorner;

	private Color color;

	public PineTrees(int x, int y, int WIDTH, int HEIGHT) {
		
		this.xCorner = x;
		this.yCorner = y;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;

	}

	public void drawOn(Graphics2D g2) {
	
		Color brown = new Color(145, 112, 33);
		Color green = new Color(40, 135, 22);
		
		g2.setColor(brown);
		g2.drawRect(xCorner+this.WIDTH/3,yCorner+this.HEIGHT*2/3,this.WIDTH/3,this.HEIGHT/3);
		g2.fillRect(xCorner+this.WIDTH/3,yCorner+this.HEIGHT*2/3,this.WIDTH/3,this.HEIGHT/3);
		
		
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		int nPoints = 3;
		xPoints[0] = xCorner+this.WIDTH/2; xPoints[1] = xCorner; xPoints[2] = xCorner+this.WIDTH;
		yPoints[0] = yCorner; yPoints[1] = yCorner+this.HEIGHT*2/3; yPoints[2] = yCorner+this.HEIGHT*2/3; 
		g2.setColor(green);
		g2.drawPolygon(xPoints, yPoints, nPoints);
		g2.fillPolygon(xPoints, yPoints, nPoints);
		
		
	}
}
