import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.File;
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

public class SnowFlake extends Element{
	private Image image;
	private double x;
	private double y;
	private GameWorld world;
	private Color color;
	private double width;
	

	public SnowFlake(GameWorld world, Point2D drawPoint) {
		super(world, drawPoint);
		this.world = world;
		this.color = new Color(255,255,255,0);
		this.width = 20.0;
		
		this.x =  this.getDrawPoint().getX();
		this.y = this.getDrawPoint().getY();
		
		try {
			this.image = ImageIO.read(new File("flake.png"));
		
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file ");
		}
	}
	
	@Override
	public void updatePosition() {
		this.y += 3;
//		System.out.println(this.x+" p p p p "+this.y);
		this.setDrawPoint(new Point2D.Double(this.x,this.y));
	}
	@Override
	public void timePassed() {
		updatePosition();
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
	public Image getImage() {
		return this.image;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
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
