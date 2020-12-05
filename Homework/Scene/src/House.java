import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class House {
	private static final int HEIGHT = 50;
	private static final int WIDTH = 100;
	private static final int ROOF_HEIGHT = 20;
	
	private int xCorner;
	private int yCorner;

	private Color color;

	public House(int x, int y, Color color) {
		// TODO: save off the parameters into instance variables
		
		this.xCorner = x;
		this.yCorner = y;
		this.color = color;
	}

	public void drawOn(Graphics2D g2) {
		// TODO: Draw the house body (a rectangle) and the roof (3 lines or a
		// Polygon)
		g2.setColor(color);
		g2.drawRect(xCorner,yCorner,this.WIDTH,this.HEIGHT);
		g2.fillRect(xCorner,yCorner,this.WIDTH,this.HEIGHT);
		
		
		g2.drawLine(xCorner, yCorner, xCorner+this.WIDTH/2, yCorner-this.ROOF_HEIGHT);
		g2.drawLine(xCorner+this.WIDTH, yCorner,xCorner+this.WIDTH/2, yCorner-this.ROOF_HEIGHT);
	}

}
