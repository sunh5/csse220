import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;

import java.awt.Image;

/**
 * This class renders the component in the GameWorld on the GUI.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/26/2019
 */

public class GameComponent extends JComponent {

	/**
	 * 
	 */
	private GameWorld world;
	private int gameLevel;
	private int maxLevel;
	private final static int START_LEVEL = 1;
	private final static int MAX_LEVEL = 4;
	
	/**
	 * Constructs a component for rendering the given ballControl on the GUI.
	 * 
	 * @param world
	 */
	public GameComponent() {
		this.gameLevel = 1;
		this.world = new GameWorld(this.gameLevel);

		GameLevelHandler levelHandler = new GameLevelHandler();
		this.addKeyListener(levelHandler);

		this.setFocusable(true);
		this.maxLevel = 4;

		Timer repaintTimer = new Timer(1, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				if (GameComponent.this.world.getButtons().size() == 0) {
					
				}else {
					
				
				if (GameComponent.this.world.getButtons().get(0).isStart() == true) {
					int level = GameComponent.this.getWorld().getLevel();
					System.out.println(level);
					GameComponent.this.world = new GameWorld(level - 1);
				}
				}
			}
		});
		repaintTimer.start();
	}

	public GameWorld getWorld() {
		return this.world;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawDrawable(g2, this.world);
		ArrayList<Drawable> drawableParts = this.world.getDrawableParts();
		int length = drawableParts.size();

		for (Drawable d : drawableParts) {
			drawDrawable(g2, d);
		}
	}

	private void drawDrawable(Graphics2D g2, Drawable d) {
		Color color = d.getColor();
		Shape shape = d.getShape();
		Image image = d.getImage();
		g2.setColor(color);
		g2.fill(shape);
		g2.drawImage(image,d.getX(), d.getY(), null);
//		g2.drawImage(image, d.getX(), d.getY(), 40, 40, null);
	}
	
	/**
	 * This inner class switches the game level using 'u' and 'd' on the keyboard.
	 */
	public class GameLevelHandler implements KeyListener {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == 'd') {
				System.out.println("go to next level");
				if (GameComponent.this.getWorld().getLevel() < 4) {
					GameComponent.this.world = new GameWorld(GameComponent.this.getWorld().getLevel() + 1);
				} else {
					System.out.println("there is no further level");
				}
			}
			if (e.getKeyChar() == 'u') {
				System.out.println("go to previous level");
				if (GameComponent.this.getWorld().getLevel() > 1) {
					GameComponent.this.world = new GameWorld(GameComponent.this.getWorld().getLevel() - 1);
				} else {
					System.out.println("there is no further level");
				}
			}
			if (e.getKeyChar() == 'j') {
				System.out.println("left");
				GameComponent.this.getWorld().lastMovement = 0;
				GameComponent.this.getWorld().isMovingLeft = true;
			}
			if (e.getKeyChar() == 'l') {
				System.out.println("right");
				GameComponent.this.getWorld().lastMovement = 1;
				GameComponent.this.getWorld().isMovingRight = true;
			}
			if (e.getKeyChar() == 'i') {
				GameComponent.this.getWorld().isFlying = true;
			}
			if (e.getKeyChar() == 'k') {
				GameComponent.this.getWorld().isFalling = true;
			}
			if (e.getKeyChar() == 'a') {
				System.out.println("shoot!!!");
				Point2D.Double heroLocation = GameComponent.this.getWorld().getHeroLocation();
				Point2D.Double shootPoint = new Point2D.Double(
						heroLocation.getX() - 5 + GameComponent.this.getWorld().lastMovement * 70,
						heroLocation.getY() + 15);
				GameComponent.this.getWorld().addBullets(shootPoint, GameComponent.this.getWorld().lastMovement);
			}
			GameComponent.this.world.addMonsterBullets();
		
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			GameComponent.this.getWorld().isMovingLeft = false;

			GameComponent.this.getWorld().isMovingRight = false;

			GameComponent.this.getWorld().isFlying = false;

			GameComponent.this.getWorld().isFalling = true;

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

}
