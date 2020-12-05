

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Rect {

	protected Color color;
	Rectangle2D data;
	public static final int WIDTH = 90;
	private int xCoor;
	private int yCoor;
	
	public Rect(int i, int j) {
		data = new Rectangle2D.Double(i, j, WIDTH, WIDTH);
		color = Color.WHITE;
		this.xCoor = i;
		this.yCoor = j;
	}
	

	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(data);
		g2.setColor(Color.BLACK); // always have a black border
		g2.draw(data);
		
	}

	public boolean containsPoint(Point point) {
		return data.contains(point);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public Point getPoint() {
		return new Point(this.xCoor,this.yCoor);
	}
}
