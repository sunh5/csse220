import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Monster extends Element {
	private Color color;
	private double width;
	private GameWorld world;
	private double bounce = 0.5;
	private double bouncex = 0.5;
	private int freezeTime;
	private Point2D.Double initialPosition;
	private BufferedImage left_1;
	private BufferedImage left_2;
	private BufferedImage right_1;
	private BufferedImage right_2;
	private BufferedImage Image;
	private int count;

	public Monster(GameWorld world, Point2D drawPoint) {
		super(world, drawPoint);
		this.initialPosition = (Double) drawPoint;
		this.color = new Color(255, 255, 255, 0);
		this.width = 40.0;
		this.world = world;
		this.freezeTime = 0;
		this.count = 1;
		
		try {
			this.Image = ImageIO.read(new File("pen.png"));
			this.left_1 = ImageIO.read(new File("monster_left_1.png"));
			this.left_2 = ImageIO.read(new File("monster_left_2.png"));
			this.right_1 = ImageIO.read(new File("monster_right_1.png"));
			this.right_2 = ImageIO.read(new File("monster_right_2.png"));
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file ");
		}
		this.Image = this.left_1;
	}

	public void reset() {
		this.setDrawPoint(initialPosition);
	}

	public void invisible(Boolean status) {
		if (status == true) {
			this.width = 0;
			this.Image = null;
			this.freezeTime = 2000;
		} else {
			this.width = 40.0;
			this.Image = this.left_1;
			this.freezeTime = 0;
		}
	}

	public void freeze() {
		this.freezeTime = 2000;
	}

	public int getfreezeTime() {
		return this.freezeTime;
	}

	@Override
	public void timePassed() {
		updatePosition();
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public void updatePosition() {
		// switching between images
		double dx = this.getDrawPoint().getX() + (this.bouncex + Math.random() / 5) * 1;
		double dy = this.getDrawPoint().getY() + (this.bounce + Math.random() / 5) * 1;
		Point2D point = new Point2D.Double(dx, dy);
		if (this.freezeTime == 0) {
			this.count++;
			if (bouncex < 0) {
				if (this.count < 50) {
					this.Image = this.left_1;
				} else {
					this.Image = this.left_2;
				}
			} else {
				if (this.count < 50) {
					this.Image = this.right_2;
				} else {
					this.Image = this.right_1;
				}
			}
			if (this.count > 100) {
				this.count = 1;
			}
			this.moveAfterCheck(point);
		} else {
			this.Image = null;
			this.freezeTime = 2000;
		}

	}

	public void moveAfterCheck(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == true || this.isWithinBoundary(rect) == false) {
			this.bounce = -this.bounce;
			double dx = this.getDrawPoint().getX() + this.bouncex;
			double dy = this.getDrawPoint().getY() + this.bounce;
			if (dx + 40 >= 1000)
				dx = 0;
			if (dx < 0)
				dx = 960;
			if (withinWall(dx, dy) == true)
				this.bouncex = -this.bouncex;
			point = new Point2D.Double(dx, dy);
		}

		this.setDrawPoint(point);
	}

	public boolean withinWall(double dx, double dy) {
		Point2D point = new Point2D.Double(dx, dy);
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == true)
			return true;
		return false;
	}

	public static double setMove() {
		Random var = new Random();
		int n = var.nextInt(2) + 0;
		if (n == 0)
			return 1;
		if (n == 1)
			return -1;
		return 1;
	}
	
	@Override
	public void updateSize() {
		// TODO Auto-generated method stub
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
