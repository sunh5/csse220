import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.Timer;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;


public class GameWorld implements Temporal, Drawable {
	private int gameLevel;
	private ArrayList<Wall> walls;
	private ArrayList<Monster> monsters;
	private ArrayList<MonsterR> monsterRs;
	private ArrayList<Hero> heros;
	private ArrayList<Bullet> bullets;
	private ArrayList<Egg> eggs;
	private ArrayList<ArcadeButton> buttons;
	private static final double GRID = 50;
	private boolean isPaused = false;
	private int width;
	private int height;
	private final Color color;
	private final Shape background;
	private final ArrayList<Wall> wallsToRemove = new ArrayList<Wall>();
	private final ArrayList<Monster> monstersToRemove = new ArrayList<Monster>();
	private final ArrayList<Hero> herosToRemove = new ArrayList<Hero>();
	private final ArrayList<Monster> monsterRsToRemove = new ArrayList<Monster>();
	private ArrayList<Bullet> bulletsToRemove;
	private ArrayList<Egg> eggsToRemove;
	private ArrayList<ArcadeButton> buttonToRemove = new ArrayList<ArcadeButton>();
	boolean isMovingLeft;
	boolean isMovingRight;
	boolean isFlying;
	boolean isFalling;
	private Image image;
	int lastMovement;
	private Point2D ButtonPoint;
	

	public GameWorld(int level) {
		this.width = 1000;
		this.height = 750;
		this.color = Color.WHITE;
		this.background = new Rectangle2D.Double(0,0,this.width,this.height); 
		this.gameLevel = level;
		this.walls = new ArrayList<Wall>();
		this.monsters = new ArrayList<Monster>();
		this.heros = new ArrayList<Hero>();
		this.monsterRs = new ArrayList<MonsterR>();
		this.buttons = new ArrayList<ArcadeButton>();
		
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.isFlying = false;
		this.isFalling = false;
		
		
		this.bullets = new ArrayList<Bullet>();
		this.bulletsToRemove = new ArrayList<Bullet>();
		
		this.eggs = new ArrayList<Egg>();
		this.eggsToRemove = new ArrayList<Egg>();
		
		Map currentMap = new Map(gameLevel);
		char[][] layout = currentMap.getMap();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 20; j++) {
				if (layout[i][j] == 'L') {
					this.buttons.add(new ArcadeButton(this,new Point2D.Double(j * GRID, i * GRID)));
					System.out.println("add button");
				}
				if (layout[i][j] == 'o') {
					this.walls.add(new Wall(this, new Point2D.Double(j * GRID, i * GRID)));
				}
				if (layout[i][j] == 'H') {
					this.heros.add(new Hero(this, new Point2D.Double(j * GRID, i * GRID),"alien"));
				}
				if (layout[i][j] == 'M') {
					this.monsters.add(new Monster(this, new Point2D.Double(j * GRID, i * GRID)));
				} 
				if (layout[i][j] == 'N') {
					this.monsterRs.add(new MonsterR(this, new Point2D.Double(j * GRID, i * GRID)));
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
	
	public void addEgg(Point2D point) {
		this.eggs.add(new Egg(this, point));
	}
	public void addBullets(Point2D.Double point, int direction) {
		this.bullets.add(new Bullet(this, point,direction));
	}
	public void addMonsterBullets() {
//		this.bullets.add(new Bullet(this,new Point2D.Double(this.getMonsterRLocation().getX()+35, this.getMonsterRLocation().getY()+15),direction));
	}
	
	public Point2D.Double getHeroLocation(){
		return (Double) this.heros.get(0).getDrawPoint();
	}
	public Point2D.Double getMonsterRLocation(){
		return (Double) this.monsterRs.get(0).getDrawPoint();
	}
	
	public synchronized ArrayList<Drawable> getDrawableParts() {
		ArrayList<Drawable> thingsToDraw = new ArrayList<Drawable>();
		for (Monster m: this.monsters) {
			thingsToDraw.add(m);
		}
		for (Wall w: this.walls) {
			thingsToDraw.add(w);
		}
		for (Hero h: this.heros) {
			thingsToDraw.add(h);
		}
		for (MonsterR h: this.monsterRs) {
			thingsToDraw.add(h);
		}
		for (Bullet b: this.bullets) {
			thingsToDraw.add(b);
		}
		for (Egg e: this.eggs) {
			thingsToDraw.add(e);
		}
		for (ArcadeButton b: this.buttons) {
			thingsToDraw.add(b);
		}
//		
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
			for (Temporal t : this.monsterRs) {
				t.timePassed();
			}
			for (Temporal t : this.eggs) {
				t.timePassed();
			}
			for (Bullet b:this.bullets) {
				b.timePassed();
					if (b.isOutRange() == true) {
						System.out.println("remove");
						this.bulletsToRemove.add(b);
				
				}
			}
			for (Temporal t : this.buttons) {
				t.timePassed();
			}
		}
		this.monsters.removeAll(this.monstersToRemove);
		this.monstersToRemove.clear();
		this.walls.removeAll(this.wallsToRemove);
		this.wallsToRemove.clear();
		this.heros.removeAll(this.herosToRemove);
		this.herosToRemove.clear();
		this.monsterRs.removeAll(this.monsterRsToRemove);
		this.monstersToRemove.clear();
		this.bullets.removeAll(this.bulletsToRemove);
		this.bulletsToRemove.clear();
		this.eggs.removeAll(this.eggsToRemove);
		this.eggsToRemove.clear();
//		this.buttons.removeAll(this.buttons);
//		this.buttonToRemove.clear();

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
	public void setLevel() {
		this.gameLevel --;
	}


	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}


	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Wall> getWalls(){
		return this.walls;
	}
	public ArrayList<Monster> getMonsters(){
		return this.monsters;
	}

	public ArrayList<Bullet> getBullets() {
		return this.bullets;
	}
	public ArrayList<Hero> getHeros() {
		return this.heros;
	}
	public void  addRemoveEgg(Egg egg){
		this.eggsToRemove.add(egg);
	}
	public Point2D getButtonPoint() {
		return this.ButtonPoint;
	}
	public ArrayList<ArcadeButton> getButtons(){
		return this.buttons;
	}
	


}
