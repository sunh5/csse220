import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Wall extends Element {
	private Color color;
	private double width;
	
	public Wall(GameWorld world, Point2D drawPoint) {
		super(world,drawPoint);
		this.color = Color.black;
		this.width = 50.0;
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
}
