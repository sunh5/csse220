import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.geom.Point2D.Double;

/**
 * This class defines how bullet moves from hero.
 * 
 * @author Jingwen Wu & Haoxuan Sun
 *
 */
public class Bullet extends Element {

	private Color color;
	private double dx;
	private final static double RANGE = 300.0;
	private int distance;
	private double width;
	private boolean outOfRange;
	private GameWorld world;
	private int direction; // 0 means left, 1 means right
	private int id; // 0 means hero's bullet, 1 means monster's bullet
	private BufferedImage right_img;
	private BufferedImage left_img;
	private BufferedImage ice_left;
	private BufferedImage ice_right;
	private BufferedImage Image;

	public Bullet(GameWorld world, Point2D drawPoint, int d, int i) {
		super(world, drawPoint);
		this.world = world;
		this.distance = 0;
		this.dx = 1.5;
		this.width = 20.0;
		this.color = new Color(255, 255, 255, 0);
		this.outOfRange = false;
		this.direction = d;
		this.id = i;

		try {
			this.right_img = ImageIO.read(new File("fire_right.png"));
			this.left_img = ImageIO.read(new File("fire_left.png"));
			this.ice_left = ImageIO.read(new File("ice_left.png"));
			this.ice_right = ImageIO.read(new File("ice_left.png"));
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file ");
		}

		this.Image = null;
	}

	public boolean isOutRange() {
		return this.outOfRange;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void updatePosition() {
		if (this.distance <= RANGE) {
			Point2D.Double checkPoint = null;
			if (this.direction == 1) {
				checkPoint = new Point2D.Double(this.getDrawPoint().getX() + this.dx, this.getDrawPoint().getY());
				if (this.id == 1) {
					this.Image = this.right_img;
				} else {
					this.Image = this.ice_right;
				}
			}
			if (this.direction == 0) {
				checkPoint = new Point2D.Double(this.getDrawPoint().getX() - this.dx, this.getDrawPoint().getY());
				if (this.id == 1) {
					this.Image = this.left_img;
				} else {
					this.Image = this.ice_left;
				}
			}
			if (this.withinWall(checkPoint) == false) {
				this.setDrawPoint(checkPoint);
			} else {
				this.outOfRange = true;
			}
			this.distance += this.dx;
		} else {
			this.outOfRange = true;
		}

	}
	
	public boolean withinWall(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == true || this.isWithinBoundary(rect) == false)
			return true;
		return false;
	}
	
	@Override
	public void updateSize() {

	}

	@Override
	public void updateColor() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.Image;
	}

}
