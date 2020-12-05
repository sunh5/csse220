import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
/**
 * This class defines how bullet moves from hero.
 * 
 * @author Jingwen Wu & Haoxuan Sun
 *
 */
public class Bullet extends Element {

	private Color color;
	private double dx;
	private final static double RANGE = 300.0;
	private int distance;
	private double width;
	private boolean outOfRange;
	private GameWorld world;
	private int direction; // 0 means left, 1 means right

	public Bullet(GameWorld world, Point2D drawPoint, int d) {
		super(world, drawPoint);
		this.world = world;
		this.distance = 0;
		this.dx = 1;
		this.width = 20.0;
		this.color = Color.gray;
		this.outOfRange = false;
		this.direction = d;
	}
	
	public boolean isOutRange() {
		return this.outOfRange;
	}
	@Override
	public Image getImage() {
//		ArcadeImage("alien.png");
		return null;
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
//		if (this.distance <= RANGE) {
//			this.setDrawPoint(new Point2D.Double(this.getDrawPoint().getX()+this.dx,this.getDrawPoint().getY()));
//			this.distance += this.dx;
//			System.out.println(this.distance);
//		}else {
//			this.outOfRange = true;
//		}
		
		if (this.distance <= RANGE) {
			Point2D.Double checkPoint = null;
			if (this.direction == 1) {
				checkPoint = new Point2D.Double(this.getDrawPoint().getX() + this.dx,
						this.getDrawPoint().getY());
			}
			if (this.direction == 0) {
				checkPoint = new Point2D.Double(this.getDrawPoint().getX() - this.dx,
						this.getDrawPoint().getY());
			}
			if (this.withinWall(checkPoint) == false) {
				this.setDrawPoint(checkPoint);
				this.collideMonster(checkPoint);
			} else {
				this.outOfRange = true;
			}
			this.distance += this.dx;
		} else {
			this.outOfRange = true;
		}
//		this.collideMonster(checkPoint);
	}
	
	public boolean withinWall(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == true ||this.isWithinBoundary(rect) == false)
			return true;
		return false;
	}
	public void collideMonster(Point2D point){
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if(this.isCollideMonster(rect, this.world.getMonsters()) == true) {
			this.outOfRange = true;
		}
	}
	
	@Override
	public void updateSize() {
		

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
