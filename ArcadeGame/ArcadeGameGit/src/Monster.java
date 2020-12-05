import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Monster extends Element {
	private Color color;
	private double width;
	
	public Monster(GameWorld world, Point2D drawPoint) {
		super(world,drawPoint);
		this.color = Color.RED;
		this.width = 50.0;
	}
	

	@Override
	public void timePassed() {
		
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
