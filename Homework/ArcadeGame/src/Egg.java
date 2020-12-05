import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Egg extends Element {
	private Color color;
	private double dx;
	private int distance;
	private double width;
	private double height;
	private GameWorld world;
	private Image image;
	private int imageHeight;
	private int imageWidth;
	private String imageName;

	public Egg(GameWorld world, Point2D drawPoint) {
		super(world, drawPoint);
		this.distance = 0;
		this.dx = 1;
		this.width = 37.0;
		this.height = 40.0;
		this.color = Color.gray;
		this.world = world;
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
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
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

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void updatePosition() {
		this.imageName = "Layegg2.png";
		double dy = this.getDrawPoint().getY() + 1;
		Point2D point = new Point2D.Double(this.getDrawPoint().getX(), dy);
		
		this.moveAfterCheck(point);
		this.collideHero(point);
	}

	public void moveAfterCheck(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.imageHeight, this.imageWidth);
		if (this.isIntersect(rect, this.world.getWalls()) == false && this.isWithinBoundary(rect) == true) {
			this.setDrawPoint(point);
		}
	}
	public void collideHero(Point2D point){
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.imageHeight, this.imageWidth);
		if(this.isCollideHero(rect, this.world.getHeros()) == true) {
			this.world.addRemoveEgg(this);
//			this.setDrawPoint(newPoint);
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

}
