import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hero extends Element {

	private Color color;
	private double width;
	private GameWorld world;
	private double dx;
	private double dy;
	private Point2D point;
	private int heroXaxis;
	private int heroYaxis;
	private String imageName;
	private Image image;
	private int imageHeight;
	private int imageWidth;
	
	
	
	public Hero(GameWorld world, Point2D drawPoint, String imageName) {
		super(world, drawPoint);
		this.world = world;
		this.color = Color.blue;
		this.width = 40.0;
		this.heroXaxis = (int) this.getDrawPoint().getX();
//		this.imageName = imageName;
	}
	
	

	public void ArcadeImage(String filename) {
		try {
		    this.image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file " + filename);
		}
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.imageHeight = bimg.getHeight();
		this.imageWidth = bimg.getWidth();
	}
	
	@Override
	public Image getImage() {
		ArcadeImage("Blubba.gif");
		return this.image;
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
		// TODO Auto-generated method stub
		return this.width;
	}


	@Override
	public void updatePosition() {
		

		if (this.world.isMovingLeft == true) {
			this.moveLeft();
		}
		if (this.world.isMovingRight == true) {
			this.moveRight();
		}
		if (this.world.isFlying == true) {
			this.fly();
		}
		if (this.world.isFlying == false && this.world.isFalling == true) {
			this.fall();
		}
		
	}

	@Override
	public void updateSize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateColor() {
		// TODO Auto-generated method stub

	}

	public void moveLeft() {
		double dx = this.getDrawPoint().getX() - 0.5;
		Point2D point = new Point2D.Double(dx, this.getDrawPoint().getY());
		this.moveAfterCheck(point);
	}

	/**
	 * Move right by 0.5 pixel after check
	 */
	public void moveRight() {
		double dx = this.getDrawPoint().getX() + 0.5;
		Point2D point = new Point2D.Double(dx, this.getDrawPoint().getY());
		this.moveAfterCheck(point);
	}

	/**
	 * Fly up by 1 pixel after check
	 */
	public void fly() {
		double dy = this.getDrawPoint().getY() - 1;
		Point2D point = new Point2D.Double(this.getDrawPoint().getX(), dy);
		this.moveAfterCheck(point);

	}

	/**
	 * Will keep falling until reach a platform.
	 */
	public void fall() {
		double dy = this.getDrawPoint().getY() + 1;
		Point2D point = new Point2D.Double(this.getDrawPoint().getX(), dy);
		this.moveAfterCheck(point);

	}

	/**
	 * Before move, always check the surrounding barrier.
	 * @param point
	 */
	public void moveAfterCheck(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == false && this.isWithinBoundary(rect) == true) {
			this.setDrawPoint(point);
		}
	}

	@Override
	public void collideWith(Element other) {
		// TODO Auto-generated method stub
		other.collideWith(this);
	}

	@Override
	public void collideWithMonster(Monster monster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWithHero(Hero hero) {
		// TODO Auto-generated method stub
		
	}




	

}
