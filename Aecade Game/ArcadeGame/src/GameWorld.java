import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;


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
	private final ArrayList<Wall> wallsToRemove = new ArrayList<Wall>();
	private final ArrayList<Monster> monstersToRemove = new ArrayList<Monster>();
	private final ArrayList<Hero> herosToRemove = new ArrayList<Hero>();
	boolean isMovingLeft;
	boolean isMovingRight;
	boolean isFlying;
	boolean isFalling;

	public GameWorld(int level) {
		this.width = 1000;
		this.height = 750;
		this.color = Color.WHITE;
		this.background = new Rectangle2D.Double(0,0,this.width,this.height); 
		this.gameLevel = level;
		this.walls = new ArrayList<Wall>();
		this.monsters = new ArrayList<Monster>();
		this.heros = new ArrayList<Hero>();
		
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.isFlying = false;
		this.isFalling = false;
		
		
		
		Map currentMap = new Map(gameLevel);
		char[][] layout = currentMap.getMap();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 20; j++) {
				if (layout[i][j] == 'o') {
					this.walls.add(new Wall(this, new Point2D.Double(j * GRID, i * GRID)));
				}
				if (layout[i][j] == 'M') {
					this.monsters.add(new Monster(this, new Point2D.Double(j * GRID, i * GRID)));
				}
				if (layout[i][j] == 'H') {

					this.heros.add(new Hero(this, new Point2D.Double(j * GRID, i * GRID)));
				}
			}
		}


		
		
		Timer advanceStateTimer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timePassed();
			}
			
		});
		
		advanceStateTimer.start();

	}
	

	public synchronized ArrayList<Drawable> getDrawableParts() {
		ArrayList<Drawable> thingsToDraw = new ArrayList<Drawable>();
		for (Wall w: this.walls) {
			thingsToDraw.add(w);
		}
		for (Monster m: this.monsters) {
			thingsToDraw.add(m);
		}
		for (Hero h: this.heros) {
			thingsToDraw.add(h);
		}
		return thingsToDraw;
	}

	// -------------------------------------------------------------------------
	// Temporal interface
	@Override
	public synchronized void timePassed() {
		if (!this.isPaused) {
			for (Temporal t : this.monsters) {
				t.timePassed();
			}
			for (Temporal t : this.heros) {
				t.timePassed();
			}
			for (Temporal t : this.walls) {
				t.timePassed();
			}
		}
		this.monsters.removeAll(this.monstersToRemove);
		this.monstersToRemove.clear();
		this.walls.removeAll(this.wallsToRemove);
		this.wallsToRemove.clear();
		this.heros.removeAll(this.herosToRemove);
		this.herosToRemove.clear();

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


}
