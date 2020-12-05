import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Element implements Temporal, Drawable {
	private Point2D drawPoint;
	private GameWorld world;

	/**
	 * Constructs an object whose upper left point is at the given point in the
	 * given world. Think of the obejct as a square, this is also the point where
	 * object starts to be drawn.
	 * 
	 * @param world
	 * @param drawPoint
	 */

	public Element(GameWorld world, Point2D drawPoint) {
		this.world = world;
		this.drawPoint = drawPoint;
	}
	// -------------------------------------------------------------------------
		// Drawable interface (partial implementation, subclasses must help)

	@Override
	public Shape getShape() {
		double x = getDrawPoint().getX();
		double y = getDrawPoint().getY();
		double size = getWidth();
		return new Rectangle2D.Double(x,y,size,size);
	}

	public Point2D getDrawPoint() {
		return this.drawPoint;
	}
	
	public void setDrawPoint(Point2D newPoint) {
		this.drawPoint = newPoint;
	}
	// Temporal interface

	@Override
	public void timePassed() {
		updateSize();
		updateColor();
		updatePosition();
	}

	@Override
	public void die() {
		// not yet implemented
	}

	@Override
	public boolean getIsPaused() {
		// not yet implemented
		return false;
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// not yet implemented
	}
	
	public abstract double getWidth();
	
	public abstract void updatePosition();
	public abstract void updateSize();
	public abstract void updateColor();

	

}
