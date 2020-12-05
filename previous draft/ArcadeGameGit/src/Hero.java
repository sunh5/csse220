import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.geom.Point2D.Double;

/**
 * This class defines all the features for heros. It defines how it moves based
 * on user command.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/25/2019
 */

public class Hero extends Element {

	private Color color;
	private double width;
	private GameWorld world;
	private Boolean killed;
	private Point2D.Double initialPosition;
	private int reloadTime;
	private double acceleration;
	private final static double MAX_VELOCITY = 1.5;
	private Boolean firstLeft = false;
	private Boolean firstRight = false;

	/**
	 * Construct an hero represented by blue box.
	 * 
	 * @param world
	 * @param drawPoint
	 */
	public Hero(GameWorld world, Point2D drawPoint) {
		super(world, drawPoint);
		this.initialPosition = (Double) drawPoint;
		this.world = world;
		this.color = Color.blue;
		this.width = 40.0;
		this.killed = false;
		this.reloadTime = 1000;
		this.acceleration = 0;
	}

	public void getKilled() {
		this.killed = true;
	}

	@Override
	public void timePassed() {
		updatePosition();

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

	/**
	 * Move based on the status changed by user.
	 */
	@Override
	public void updatePosition() {
//		if (this.killedByMonster(this.world.getMonsters()) == false) {
		if (this.killed == false) {
			this.reloadTime = 1000;
			if (this.world.isMovingLeft == true) {
				this.firstRight = false;
				if (this.firstLeft == false) {
					this.firstLeft = true;
					this.acceleration = 0;
				}else {
					this.acceleration += 0.01;
				}
				this.moveLeft();
			}
			if (this.world.isMovingRight == true) {
				this.firstLeft = false;
				if (this.firstRight == false) {
					this.firstRight = true;
					this.acceleration = 0;
				}else {
					this.acceleration += 0.01;
				}
				this.moveRight();
			}
			if (this.world.isFlying == true) {
				this.fly();
			}
			if (this.world.isFlying == false && this.world.isFalling == true) {
				this.fall();
			}
		} else {
			if (this.reloadTime == 0) {
				this.width = 40.0;
				this.setDrawPoint(this.initialPosition);
				this.killed = false;
			} else {
				reloadTime -= 1;
				this.width = 0;
			}
		}
//		} else {
//			this.world.heroDie = true;
//		}
	}

	@Override
	public void updateSize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateColor() {
		// TODO Auto-generated method stub

	}

	/**
	 * Move left by 0.5 pixel after check
	 */
	public void moveLeft() {

		double dx = this.getDrawPoint().getX() - 0.5;

		Point2D point = new Point2D.Double(dx, this.getDrawPoint().getY());
		this.moveAfterCheck(point);

//		double dx = this.getDrawPoint().getX();
		
		if ((this.acceleration ) <= MAX_VELOCITY ) {
			dx -= this.acceleration * this.acceleration /2;
		} else {
			dx -= MAX_VELOCITY;
		}
		if (dx <= 0)
			this.setDrawPoint(
					new Point2D.Double(this.world.getSize().getWidth() - this.width, this.getDrawPoint().getY()));
		else {
			Point2D point1 = new Point2D.Double(dx, this.getDrawPoint().getY());
			this.moveAfterCheck(point1);
		}
	}

	/**
	 * Move right by 0.5 pixel after check
	 */
	public void moveRight() {
		double dx = this.getDrawPoint().getX() + 0.5;

		Point2D point = new Point2D.Double(dx, this.getDrawPoint().getY());
		this.moveAfterCheck(point);


//		double dx = this.getDrawPoint().getX();
		
		if ((this.acceleration) <= MAX_VELOCITY ) {
			dx += this.acceleration * this.acceleration /2;
		} else {
			dx += MAX_VELOCITY;
		}

		if (dx >= this.world.getSize().getWidth() - this.width)
			this.setDrawPoint(new Point2D.Double(0, this.getDrawPoint().getY()));
		else {
			Point2D point1 = new Point2D.Double(dx, this.getDrawPoint().getY());
			this.moveAfterCheck(point1);
		}
	}

	/**
	 * Fly up by 1 pixel after check
	 */
	public void fly() {
		double dy = this.getDrawPoint().getY() - 1;
		Point2D point = new Point2D.Double(this.getDrawPoint().getX(), dy);
		this.moveAfterCheck(point);

	}

	/**
	 * Will keep falling until reach a platform.
	 */
	public void fall() {
		double dy = this.getDrawPoint().getY() + 1;
		Point2D point = new Point2D.Double(this.getDrawPoint().getX(), dy);
		this.moveAfterCheck(point);

	}
	
	/**
	 * Before move, always check the surrounding barrier.
	 * 
	 * @param point
	 */
	public void moveAfterCheck(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == false && this.isWithinBoundary(rect) == true) {
			this.setDrawPoint(point);
			;
		}
	}
	
}
