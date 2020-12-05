import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

public class Face {
	private static final Color EYE_COLOR = Color.BLACK;
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final Color MOUTH_COLOR = Color.red;
	private static final double DEFAULT_SUN_DIAMETER = 100.0;
	private static final double DEFAULT_SUN_X = 100.0;
	private static final double DEFAULT_SUN_Y = 100.0;
	
	private static final double EYE_XCOOR_COFFE = 0.2;
	private static final double EYE_YCOOR_COFFE = 0.2;
	private static final double EYE_DIAMETER_COFFE = 0.28;
	
	private static final double MONTH_XCOOR_COFFE = 0.5;
	private static final double MONTH_YCOOR_COFFE = 0.6;
	private static final double MONTH_RADIUS_COFFE = 0.7;
	

	private double x = 0;
	private double y = 0;
	private double circleDiameter = 0;
	private double r;
	private double radius = 0;
	private double rayLength = 0;
	private double rayWidth = 0;
	private double rayDistanceFromSun = 0;
	private Color color;
	
	public Face(double x, double y, double circleDiameter, double r,Color color) {
		this.x = x;
		this.y = y;
		this.circleDiameter = circleDiameter;
		this.r = r;
		this.color = color;
		this.radius = circleDiameter/2;
	}
	
	public void drawOn(Graphics2D g2) {
		g2.drawOval((int) this.x, (int) this.y, (int) this.circleDiameter, (int) this.circleDiameter);

		g2.setColor(this.color);
		g2.fillOval((int) this.x, (int) this.y, (int) this.circleDiameter, (int) this.circleDiameter);
		g2.setColor(BORDER_COLOR);
		
		g2.setColor(EYE_COLOR);
		g2.drawOval((int) this.x + (int)(this.EYE_XCOOR_COFFE*this.circleDiameter), 
				(int) this.y+(int)(this.EYE_YCOOR_COFFE*this.circleDiameter), (int) (this.circleDiameter*this.EYE_DIAMETER_COFFE), 
				(int) (this.circleDiameter*this.EYE_DIAMETER_COFFE));
		g2.fillOval((int) this.x + (int)(this.EYE_XCOOR_COFFE*this.circleDiameter), 
				(int) this.y+(int)(this.EYE_YCOOR_COFFE*this.circleDiameter), (int) (this.circleDiameter*this.EYE_DIAMETER_COFFE), 
				(int) (this.circleDiameter*this.EYE_DIAMETER_COFFE));
		
		g2.drawOval((int) this.x + (int)this.radius+(int)(this.radius-this.circleDiameter*this.EYE_DIAMETER_COFFE-this.EYE_XCOOR_COFFE*this.circleDiameter), 
				(int) this.y+(int)(this.EYE_YCOOR_COFFE*this.circleDiameter), (int) (this.circleDiameter*this.EYE_DIAMETER_COFFE), 
				(int) (this.circleDiameter*this.EYE_DIAMETER_COFFE));
		g2.fillOval((int) this.x + (int)this.radius+(int)(this.radius-this.circleDiameter*this.EYE_DIAMETER_COFFE-this.EYE_XCOOR_COFFE*this.circleDiameter), 
				(int) this.y+(int)(this.EYE_YCOOR_COFFE*this.circleDiameter), (int) (this.circleDiameter*this.EYE_DIAMETER_COFFE), 
				(int) (this.circleDiameter*this.EYE_DIAMETER_COFFE));
		
		
		g2.setColor(MOUTH_COLOR);
		Arc2D.Double mouth = new Arc2D.Double((int) (this.x+this .radius*this.MONTH_XCOOR_COFFE),
				(int) (this.y+this.MONTH_YCOOR_COFFE*this.radius),
				this.r*Math.sqrt(2),this.r*Math.sqrt(2),-15,-150,1);
		g2.fill(mouth);
//		
	}
}
