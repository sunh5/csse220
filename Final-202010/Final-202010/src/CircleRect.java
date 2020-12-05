

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class CircleRect extends Rect {

	protected Color color = Color.RED;
	Rectangle2D data;
	public static final int WIDTH = 90;
	private int xCoor;
	private int yCoor;
	
	public CircleRect(int i, int j) {
		super( i,  j);
		data = new Rectangle2D.Double(i, j, WIDTH, WIDTH);
//		data2 = new Circle2D.Double();
		color = Color.WHITE;
		this.xCoor = i;
		this.yCoor = j;
	}
	

	public void draw(Graphics2D g2) {
		
		g2.setColor(color);
		g2.fillOval(this.xCoor, this.yCoor, WIDTH, WIDTH);
//		g2.fill(data);
		g2.setColor(Color.BLACK); // always have a black border
		g2.drawOval(this.xCoor, this.yCoor, WIDTH, WIDTH);
		
	}

	public boolean containsPoint(Point point) {
		return data.contains(point);
	}
	
	public void setColor(Color color) {
		this.color = color;
		System.out.println("color");
	}
	public Point getPoint() {
		return new Point(this.xCoor,this.yCoor);
	}
}
