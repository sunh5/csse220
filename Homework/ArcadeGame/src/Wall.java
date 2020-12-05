import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;

public class Wall extends Element {
	private Color color;
	private double width;
	private Image image;
//	private static ArrayList<Double> upperWallEdge;
	
	public Wall(GameWorld world, Point2D drawPoint) {
		super(world,drawPoint);
		this.color = Color.black;
		this.width = 50.0;
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
//		this.imageHeight = bimg.getHeight();
//		this.imageWidth = bimg.getWidth();
	}
	@Override
	public Image getImage() {
//		ArcadeImage("Wall.png");
		return this.image;
	}
	@Override
	public void timePassed() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
