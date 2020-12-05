import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Element {
	private Color color;
	private double width;
	private BufferedImage image;
	
	public Wall(GameWorld world, Point2D drawPoint) {
		super(world,drawPoint);

		this.color =new Color(255,255,255,0);
//		this.color = Color.LIGHT_GRAY;
		this.width = 50.0;
		
		try {
			this.image = ImageIO.read(new File("grass.png"));
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file ");
		}
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
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}
}
