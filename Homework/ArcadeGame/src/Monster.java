import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Monster extends Element {
	private Color color;
	private double width;
	private Image image;
	private GameWorld world;
	private double bounce = 0.5;
	private double bouncex = 0.5;
	private int imageHeight;
	private int imageWidth;
	private int collideTime = 0;
	private String imageName;
	
	public Monster(GameWorld world, Point2D drawPoint) {
		super(world,drawPoint);
		this.color = Color.RED;
		this.width = 40.0;
		this.world = world;
		this.imageName = "Joust2_right.png";
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
			e.printStackTrace();
		}
		this.imageHeight = bimg.getHeight();
		this.imageWidth = bimg.getWidth();
	}
	
	@Override
	public Image getImage() {
		ArcadeImage(this.imageName);
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
		return this.width;
	}
	
	@Override
	public void updatePosition() {
		double dx = this.getDrawPoint().getX() + this.bouncex;
		double dy = this.getDrawPoint().getY() + this.bounce;
		Point2D point = new Point2D.Double(dx, dy);
		this.moveAfterCheck(point);
		this.collideWithBullet(point);
	}
	
	public void moveAfterCheck(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.imageHeight, this.imageWidth);
		if (this.isIntersect(rect, this.world.getWalls()) == true || this.isWithinBoundary(rect) == false) {
			this.bounce = -this.bounce;
			
			double dx = this.getDrawPoint().getX() + this.bouncex;
			double dy = this.getDrawPoint().getY() + this.bounce;
			if(dx + 40 >= 1000) dx = 0;
			if(dx < 0) dx = 960;
			if(withinWall(dx,dy) == true) this.bouncex = -this.bouncex;
			point = new Point2D.Double(dx, dy);
		}
		this.setDrawPoint(point);
	}
	public boolean withinWall(double dx, double dy) {
		Point2D point = new Point2D.Double(dx, dy);
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == true) return true;
		return false;
	}
//	public void collideWithBullet(Point2D point) {
//		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
//		if (this.isCollide(rect, this.world.getBullets()) == true) {
//			this.imageName = "Joust.png";
//			this.setDrawPoint(point);
//			this.bouncex = 0;
//			this.bounce = 0;
//			if (this.collideTime == 0) {
//				this.world.addEgg(point);
//				this.collideTime = 1;
//			}
//			
//		}
//	}
	public void collideWithBullet(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isCollideBullet(rect, this.world.getBullets()) == true) {
			this.imageName = "Joust.png";
			this.setDrawPoint(point);
			this.bouncex = 0;
			this.bounce = 0;
			if (this.collideTime == 0) {
				this.world.addEgg(point);
				this.collideTime = 1;
			}
			
		}
	}
	
	public static double setMove() {
		Random var = new Random();
		int n = var.nextInt(2) + 0;
		if (n == 0) return 1;
		if (n == 1) return -1;
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
	public void collideWith(Element other) {
		// TODO Auto-generated method stub
		
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
