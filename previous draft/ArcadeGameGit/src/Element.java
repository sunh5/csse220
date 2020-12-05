import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

import com.sun.javafx.geom.Line2D;

/**
 * This class is an abstract class for all the components in the game.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/25/2019
 */
public abstract class Element implements Temporal, Drawable {
	private Point2D drawPoint;
	private GameWorld world;

	/**
	 * Constructs an object whose upper left point is at the given point in the
	 * given world. Think of the object as a square, this is also the point where
	 * object starts to be drawn.
	 * 
	 * @param world
	 * @param drawPoint
	 */
	public Element(GameWorld world, Point2D drawPoint) {
		this.world = world;
		this.drawPoint = drawPoint;
	}

	/**
	 * This is an important helper function to check whether 
	 * the object is colliding with walls.
	 * @param Rect
	 * @param barriers
	 * @return
	 */
	public boolean isIntersect(Rectangle2D.Double Rect, ArrayList<Wall> barriers) {
		for (Wall barrier : barriers) {
			Shape barrier_rect = barrier.getShape();
			if (Rect.intersects((Rectangle2D) barrier_rect)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This function is to check whether the character is moving out
	 * of the panel boundary.
	 * @param Rect
	 * @return
	 */
	public boolean isWithinBoundary(Rectangle2D.Double Rect) {
		double height = this.world.getSize().getHeight();
		double width = this.world.getSize().getWidth();
		if (Rect.getX() + Rect.getWidth() <= width && Rect.getX() >= 0 && Rect.getY() >= 0 && Rect.getY() + Rect.getWidth() <= height) {
			return true;
		}
		return false;
	}
	// -------------------------------------------------------------------------
	// Drawable interface (partial implementation, subclasses must help)

	@Override
	public Shape getShape() {
		double x = getDrawPoint().getX();
		double y = getDrawPoint().getY();
		double size = getWidth();
		return new Rectangle2D.Double(x, y, size, size);
	}

	public Point2D getDrawPoint() {
		return this.drawPoint;
	}

	public void setDrawPoint(Point2D newPoint) {
		this.drawPoint = newPoint;
	}
	
	// -------------------------------------------------------------------------
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
