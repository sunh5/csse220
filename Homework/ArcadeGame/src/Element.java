import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

public abstract class Element implements Temporal, Drawable {
	private Point2D drawPoint;
	private GameWorld world;
	private static ArrayList<Double> upperWallEdge;
	protected Rectangle2D.Double box;
	public boolean isCollide;
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
		this.box = new Rectangle2D.Double(getDrawPoint().getX(),getDrawPoint().getY(),getWidth(),getWidth());
//		this.box=new Rectangle2D.Double(drawPoint);
//		this.upperWallEdge = new ArrayList<Double>();
	}
	// -------------------------------------------------------------------------
		// Drawable interface (partial implementation, subclasses must help)
	
	public boolean isIntersect(Rectangle2D.Double Rect, ArrayList<Wall> barriers) {
		for (Wall barrier : barriers) {
			Shape barrier_rect = barrier.getShape();
			if (Rect.intersects((Rectangle2D) barrier_rect)) {
				return true;
			}
		}
		return false;
	}
	public boolean isWithinBoundary(Rectangle2D.Double Rect) {
		double height = this.world.getSize().getHeight();
		double width = this.world.getSize().getWidth();
		if (Rect.getX() + Rect.getWidth() <= width && Rect.getX () >= 0 && Rect.getY() >= 0 && Rect.getY() + Rect.getWidth() <= height)
			return true;
		return false;
	}
//	@Override
//	public void isCollide(Rectangle2D.Double Rect, ArrayList<Element> elements) {
//		for (Element element : elements) {
//			Shape objectRect = element.getShape();
//			if (Rect.intersects((Rectangle2D) objectRect)){
//				this.isCollide = true;
//			}
//		}
//		this.isCollide = false;
//	}
									
	public boolean isCollideBullet(Rectangle2D.Double Rect, ArrayList<Bullet> bullets) {
		for (Bullet bullet : bullets) {
			Shape bulletRect = bullet.getShape();
			if (Rect.intersects((Rectangle2D) bulletRect)){
				return true;
			}
		}
		return false;
	}
	public boolean isCollideMonster(Rectangle2D.Double Rect, ArrayList<Monster> monsters) {
		for (Monster monster : monsters) {
			Shape Monster = monster.getShape();
			if (Rect.intersects((Rectangle2D) Monster)){
				return true;
			}
		}
		return false;
	} 
	public boolean isCollideHero(Rectangle2D.Double Rect, ArrayList<Hero> heros) {
		for (Hero hero : heros) {
			Shape Hero = hero.getShape();
			if (Rect.intersects((Rectangle2D) Hero)){
				return true;
			}
		}
		return false;
	} 
	public abstract void collideWith(Element other);
	
	public boolean collideWithWall(Wall wall){

		if(this.box.intersects(wall.box)){
//			this.world.isFalling = false;
			return false;
		}
		return true;
	}
	public abstract void collideWithMonster(Monster monster);
	public abstract void collideWithHero(Hero hero);
	
	
	
	@Override
	public Shape getShape() {
		double x = getDrawPoint().getX();
		double y = getDrawPoint().getY();
		double size = getWidth();
		return new Rectangle2D.Double(x,y,size,size);
	}
	
	@Override
	public  int getX() {
		// TODO Auto-generated method stub
		return (int)getDrawPoint().getX();
	}

	
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int) getDrawPoint().getY();
	}
	
	public double getYaxis() {
		double y = getDrawPoint().getY();
		return y;
	}
	public double getXaxis() {
		double x = getDrawPoint().getX();
		return x;
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


	public Image getImage() {
		// TODO Auto-generated method stub
		return this.getImage();
	}

	

}
