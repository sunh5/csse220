import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Sun {
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final int NUMBER_OF_RAYS = 8;
	private static final double RAY_LENGTH_SCALE = 0.5;
	private static final double RAY_WIDTH_SCALE = 0.1;
	private static final double RAY_DISTANCE_FROM_SUN_SCALE = .2;
	private static final double DEFAULT_SUN_DIAMETER = 100.0;
	private static final double DEFAULT_SUN_X = 100.0;
	private static final double DEFAULT_SUN_Y = 100.0;
	private static Color DEFAULT_SUN_COLOR = Color.YELLOW;
	private static final double LITTLE_SUNS_X_OFFSET = 50;

	private double x = 0;
	private double y = 0;
	private double circleDiameter = 0;
	private double rayLength = 0;
	private double rayWidth = 0;
	private double rayDistanceFromSun = 0;
	private Color color;

	public Sun() {
		this.x = DEFAULT_SUN_X;
		this.y = DEFAULT_SUN_Y;
		this.rayLength = RAY_LENGTH_SCALE * DEFAULT_SUN_DIAMETER;
		this.rayWidth = RAY_WIDTH_SCALE * DEFAULT_SUN_DIAMETER;
		this.circleDiameter = DEFAULT_SUN_DIAMETER;
		this.rayDistanceFromSun = RAY_DISTANCE_FROM_SUN_SCALE * DEFAULT_SUN_DIAMETER;
		this.color = DEFAULT_SUN_COLOR;
	}

	public Sun(double x, double y, double circleDiameter, Color color) {

		this.x = x;
		this.y = y;
		this.rayLength = RAY_LENGTH_SCALE * circleDiameter;
		this.rayWidth = RAY_WIDTH_SCALE * circleDiameter;
		this.circleDiameter = circleDiameter;
		this.rayDistanceFromSun = RAY_DISTANCE_FROM_SUN_SCALE * circleDiameter;
		this.color = color;

	}

	public void drawOn(Graphics2D g2) {

		g2.drawOval((int) this.x, (int) this.y, (int) this.circleDiameter, (int) this.circleDiameter);

		g2.setColor(this.color);
		g2.fillOval((int) this.x, (int) this.y, (int) this.circleDiameter, (int) this.circleDiameter);
		g2.setColor(BORDER_COLOR);
		g2.drawOval((int) this.x, (int) this.y, (int) this.circleDiameter, (int) this.circleDiameter);

		int angle = 360 / this.NUMBER_OF_RAYS;
		int posiX = (int) this.x + (int) this.circleDiameter / 2;
		int posiY = (int) this.y + (int) this.circleDiameter / 2;

		int rayX = (int) (0.7 * this.circleDiameter);
		int rayY = -(int) this.rayWidth / 2;

		g2.translate(posiX, posiY);
		for (int i = 0; i < this.NUMBER_OF_RAYS + 1; i++) {
			g2.setColor(BORDER_COLOR);
			g2.setColor(this.color);
			g2.drawRect(rayX, rayY, (int) this.rayLength, (int) this.rayWidth);
			g2.fillRect(rayX, rayY, (int) this.rayLength, (int) this.rayWidth);
			g2.setColor(BORDER_COLOR);
			g2.drawRect(rayX, rayY, (int) this.rayLength, (int) this.rayWidth);
			g2.rotate(Math.toRadians(i * angle));
		}
		for (int i = 0; i < this.NUMBER_OF_RAYS + 1; i++) {
			g2.rotate(Math.toRadians(i * (-angle)));
		}
		g2.translate(-posiX, -posiY);
	}

}
