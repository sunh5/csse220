import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.Timer;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * This class controls everything happening in the game main simulation panel,
 * deciding which component will be drawn on the screen.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/25/2019
 */

public class GameWorld implements Temporal, Drawable {
	private int gameLevel;
	private ArrayList<Wall> walls;
	private ArrayList<Monster> monsters;
	private ArrayList<Hero> heros;
	private static final double GRID = 50;
	private boolean isPaused = false;
	private int width;
	private int height;
	private final Color color;
	private final Shape background;
	private ArrayList<Wall> wallsToRemove = new ArrayList<Wall>();
	private ArrayList<Monster> monstersToRemove = new ArrayList<Monster>();
	private ArrayList<Hero> herosToRemove = new ArrayList<Hero>();

	private ArrayList<Bullet> bullets;
	private ArrayList<Bullet> bulletsToRemove;
	private ArrayList<Egg> eggs;
	private ArrayList<Egg> eggsToRemove;

	boolean isMovingLeft;
	boolean isMovingRight;
	boolean isFlying;
	boolean isFalling;
	boolean isShooting;
	int lastMovement;

	/**
	 * Construct the world based on the given level of the game.
	 * 
	 * @param level
	 */
	public GameWorld(int level) {
		this.width = 1000;
		this.height = 750;
		this.color = Color.WHITE;
		this.background = new Rectangle2D.Double(0, 0, this.width, this.height);
		this.walls = new ArrayList<Wall>();
		this.monsters = new ArrayList<Monster>();
		this.heros = new ArrayList<Hero>();

		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.isFlying = false;
		this.isFalling = false;
		this.isShooting = false;
		this.lastMovement = 0;
		this.gameLevel = level;

		// Load in map for the given game level.
		// Store the objects based on the map.
		Map currentMap = new Map(gameLevel);
		char[][] layout = currentMap.getMap();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 20; j++) {
				if (layout[i][j] == 'o') {
					this.walls.add(new Wall(this, new Point2D.Double(j * GRID, i * GRID)));
				}
				if (layout[i][j] == 'S') {
					this.monsters.add(new ShootMonster(this, new Point2D.Double(j * GRID, i * GRID)));
				}
				if (layout[i][j] == 'M') {
					this.monsters.add(new Monster(this,new Point2D.Double(j * GRID, i * GRID)));
				}
				if (layout[i][j] == 'H') {
					System.out.println(j * GRID);
					this.heros.add(new Hero(this, new Point2D.Double(j * GRID, i * GRID + 10)));
				}
			}
		}
		this.bullets = new ArrayList<Bullet>();
		this.bulletsToRemove = new ArrayList<Bullet>();
		this.eggs = new ArrayList<Egg>();
		this.eggsToRemove = new ArrayList<Egg>();

		// Create a timer which can update the game component periodally.
		Timer advanceStateTimer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timePassed();
			}

		});

		advanceStateTimer.start();

	}

	public void addBullets(Point2D.Double point, int direction, int i) {
		this.bullets.add(new Bullet(this, point, direction,i));
	}

	
//	public Point2D.Double getHeroLocation(){
//		return (Double) this.monsters.get(0).getDrawPoint();
//=======

	public Point2D.Double getHeroLocation() {
		return (Double) this.heros.get(0).getDrawPoint();
//>>>>>>> branch 'master' of https://ada.csse.rose-hulman.edu/wuj1/ArcadeGame
	}

	
	public void addEggs(Point2D.Double point, Monster m) {
		this.eggs.add(new Egg(this,point, m));
	}

	/**
	 * Get the components which needs to be updated onto the simulation panel.
	 * 
	 * @return
	 */
	public synchronized ArrayList<Drawable> getDrawableParts() {
		ArrayList<Drawable> thingsToDraw = new ArrayList<Drawable>();
		for (Wall w : this.walls) {
			thingsToDraw.add(w);
		}
		for (Monster m : this.monsters) {
			thingsToDraw.add(m);
		}
		for (Hero h : this.heros) {
			thingsToDraw.add(h);
		}
		for (Bullet b : this.bullets) {
			thingsToDraw.add(b);
		}
		for (Egg e: this.eggs) {
			thingsToDraw.add(e);
		}
		return thingsToDraw;
	}

	// -------------------------------------------------------------------------
	// Temporal interface
	@Override
	public synchronized void timePassed() {

		if (!this.isPaused) {
			this.Joust(this.heros, this.monsters);
			this.pickEgg(heros, eggs);
			this.checkBullet(heros, monsters, bullets);
			for (Temporal t : this.monsters) {
				t.timePassed();
			}
			for (Temporal t : this.heros) {

				t.timePassed();
			}
			for (Temporal t : this.walls) {
				t.timePassed();
			}

			for (Bullet b : this.bullets) {
				b.timePassed();
				if (b.isOutRange() == true) {
					System.out.println("remove");
					this.bulletsToRemove.add(b);

				}
			}
			for (Egg e: this.eggs) {
				e.timePassed();
//				if (e.isOutTime() == true) {
//					System.out.print("lay egg");
//					this.eggsToRemove.add(e);
//				}
			}

		}
		this.monsters.removeAll(this.monstersToRemove);
		this.monstersToRemove.clear();
		this.walls.removeAll(this.wallsToRemove);
		this.wallsToRemove.clear();
		this.heros.removeAll(this.herosToRemove);
		this.herosToRemove.clear();

		this.bullets.removeAll(this.bulletsToRemove);
		this.bulletsToRemove.clear();
		this.eggs.removeAll(this.eggsToRemove);
		this.eggsToRemove.clear();
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return this.isPaused;
	}

	// -------------------------------------------------------------------------
	// Drawable interface

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return this.background;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	public int getLevel() {
		return this.gameLevel;
	}

	public void setLevel(int level) {
		this.gameLevel = level;
	}

	public ArrayList<Wall> getWalls() {
		return this.walls;
	}

	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	public void Joust(ArrayList<Hero> heros, ArrayList<Monster> monsters) {
		for (Monster m : monsters) {
			for (Hero h : heros) {
				if (m.getShape().intersects((Rectangle2D) h.getShape()) && m.getfreezeTime() == 0) {
					if (m.getDrawPoint().getY() < h.getDrawPoint().getY()) {
						System.out.println("killed");
						h.getKilled();
						m.reset();
					} else {
						System.out.println("trapped");
						m.invisible(true);
						Point2D.Double eggPoint = new Point2D.Double(m.getDrawPoint().getX(),m.getDrawPoint().getY() + m.getWidth() + 10);
						this.addEggs(eggPoint,m);
					}
				}
			}
		}
	}
	
	public void pickEgg(ArrayList<Hero> heros, ArrayList<Egg> eggs) {
		for (Egg e : eggs) {
			for (Hero h : heros) {
				if (e.getShape().intersects((Rectangle2D) h.getShape())){
					System.out.println("pick egg");
					this.eggsToRemove.add(e);
					this.monstersToRemove.add(e.getMonster());
				}
				if (e.isOutTime() == true) {
					this.eggsToRemove.add(e);
					e.getMonster().invisible(false);
					e.resetMonster();
				}
			}
		}
	}
	
	public void checkBullet(ArrayList<Hero> heros, ArrayList<Monster> monsters, ArrayList<Bullet> bullets) {
		
		for (Bullet b : bullets) {
			for (Hero h : heros) {
				if (b.getShape().intersects((Rectangle2D) h.getShape())){
					System.out.println("Intersect");
					if(b.getId() == 1) {
						this.bulletsToRemove.add(b);
						h.getKilled();
					}
				}
			}
			for (Monster m: monsters) {
				if (b.getShape().intersects((Rectangle2D) m.getShape())) {
					System.out.println("Intersect");
					if(b.getId() == 0) {
						this.bulletsToRemove.add(b);
						this.monstersToRemove.add(m);
					}
				}
			}
		}
	}

}
