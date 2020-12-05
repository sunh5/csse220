import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	private final static double MAX_VELOCITY = 2.5;
	private Boolean firstLeft = false;
	private Boolean firstRight = false;
	private BufferedImage loadImg;
	private BufferedImage fall_left;
	private BufferedImage fall_right;
	private BufferedImage walk_left_1;
	private BufferedImage walk_left_2;
	private BufferedImage walk_left_3;
	private BufferedImage walk_left_4;
	private BufferedImage walk_right_1;
	private BufferedImage walk_right_2;
	private BufferedImage walk_right_3;
	private BufferedImage walk_right_4;
	private BufferedImage jump_right;
	private BufferedImage jump_left;
	private BufferedImage image;
	private int walk_count;
	private int direction;

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
		this.color =new Color(255,255,255,0);
		this.width = 40.0;
		this.killed = false;
		this.reloadTime = 1000;
		this.acceleration = 0;
		this.walk_count = 1;
		this.direction = 0;
		
		try {
			this.loadImg = ImageIO.read(new File("pen.png"));
			this.fall_left = ImageIO.read(new File("fall_left.png"));
			this.fall_right = ImageIO.read(new File("fall_right.png"));
			this.walk_left_1 = ImageIO.read(new File("walk_left_1.png"));
			this.walk_left_2 = ImageIO.read(new File("walk_left_2.png"));
			this.walk_left_3 = ImageIO.read(new File("walk_left_3.png"));
			this.walk_left_4 = ImageIO.read(new File("walk_left_4.png"));
			this.walk_right_1 = ImageIO.read(new File("walk_right_1.png"));
			this.walk_right_2 = ImageIO.read(new File("walk_right_2.png"));
			this.walk_right_3 = ImageIO.read(new File("walk_right_3.png"));
			this.walk_right_4 = ImageIO.read(new File("walk_right_4.png"));
			this.jump_left  = ImageIO.read(new File("jump_left.png"));
			this.jump_right = ImageIO.read(new File("jump_right.png"));
			
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file ");
		}
		this.image = this.loadImg;
	}

	public void getKilled() {
		this.killed = true;
	}
	public boolean  killed() {
		return this.killed;
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
				this.direction = 0;
				this.firstRight = false;
				if (this.firstLeft == false) {
					this.firstLeft = true;
					this.walk_count = 1;
					this.image = this.walk_left_1;
					this.acceleration = 0;
				}else {
					this.acceleration += 0.1;
					if (this.walk_count == 2) {
						this.image = this.walk_left_1;
					}
					if (this.walk_count == 10) {
						this.image = this.walk_left_2;
					}
					if (this.walk_count == 20) {
						this.image = this.walk_left_3;
					}
					if (this.walk_count == 30) {
						this.image = this.walk_left_4;
						this.walk_count = 1;
					}
					this.walk_count ++;
				}
				this.moveLeft();
			}
			if (this.world.isMovingRight == true) {
				this.direction = 1;
				this.firstLeft = false;
				if (this.firstRight == false) {
					this.firstRight = true;
					this.walk_count = 1;
					this.image = this.walk_right_1;
					this.acceleration = 0;
				}else {
					this.acceleration += 0.1;
					if (this.walk_count == 1) {
						this.image = this.walk_right_1;
					}
					if (this.walk_count == 10) {
						this.image = this.walk_right_2;
					}
					if (this.walk_count == 20) {
						this.image = this.walk_right_3;
					}
					if (this.walk_count == 30) {
						this.image = this.walk_right_4;
						this.walk_count = 1;
					}
					this.walk_count ++;
				}
				this.moveRight();
			}
			if (this.world.isFlying == true) {
				if(this.direction == 0) {
					this.image = this.jump_left;
				}else {
					this.image = this.jump_right;
				}
				this.fly();
			}
			if (this.world.isFlying == false && this.world.isFalling == true) {
//				if(this.direction == 0) {
//					this.image = this.fall_left;
//				}else {
//					this.image = this.fall_right;
//				}
				this.fall();
			}
		} else {
			if (this.reloadTime == 0) {
				this.width = 40.0;
				this.image = this.loadImg;
				this.setDrawPoint(this.initialPosition);
				this.killed = false;
			} else {
				reloadTime -= 1;
				this.width = 0;
				this.image = null;
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
		double dy = this.getDrawPoint().getY() - 2;
		Point2D point = new Point2D.Double(this.getDrawPoint().getX(), dy);
		this.moveAfterCheck(point);

	}

	/**
	 * Will keep falling until reach a platform.
	 */
	public void fall() {
		double dy = this.getDrawPoint().getY() + 1.5;
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


	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}

}
