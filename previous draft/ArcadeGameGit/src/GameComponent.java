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

/**
 * This class renders the components in the GameWorld on the GUI. It contains a
 * keyboard listner to listen to user command on hero.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/26/2019
 */

public class GameComponent extends JComponent {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private GameWorld world;
	private int gameLevel;
//	private int maxLevel;
	private final static int START_LEVEL = 1;
	private final static int MAX_LEVEL = 3;

	/**
	 * Constructs a component for rendering the given ballControl on the GUI.
	 * 
	 * @param world
	 */
	public GameComponent() {
		this.gameLevel = START_LEVEL;
		this.world = new GameWorld(this.gameLevel);

		// add keyboard listener to this panel
		GameLevelHandler levelHandler = new GameLevelHandler();
		this.addKeyListener(levelHandler);
		// Set the listener on this panel.
		this.setFocusable(true);

		// Create a timer to trigger the repaint of components.
		Timer repaintTimer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				if (GameComponent.this.world.getMonsters().size() == 0) {
					int level = GameComponent.this.getWorld().getLevel();
					if (level < MAX_LEVEL) {
					GameComponent.this.world = new GameWorld(level + 1);
					} else {
						System.out.println("You win!");
					}
				}
			}

		});
		repaintTimer.start();
	}

	public GameWorld getWorld() {
		return this.world;
	}

	/**
	 * Repaint the components onto simulation panel.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawDrawable(g2, this.world);
		ArrayList<Drawable> drawableParts = this.world.getDrawableParts();
		for (Drawable d : drawableParts) {
			drawDrawable(g2, d);
		}
	}

	/**
	 * This is a helper function for paintComponent function to draw every object.
	 * 
	 * @param g2
	 * @param d
	 */
	private void drawDrawable(Graphics2D g2, Drawable d) {
		Color color = d.getColor();
		Shape shape = d.getShape();
		g2.setColor(color);
		g2.fill(shape);
	}

	/**
	 * This inner class listener switches the game level using 'u' and 'd' on the
	 * keyboard. The hero will move to left when 'j' is pressed. The hero will move
	 * to right when 'l' is pressed. The hero will fly when 'i' is pressed.
	 */
	public class GameLevelHandler implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == 'd') {
				System.out.println("go to next level");
				if (GameComponent.this.getWorld().getLevel() < MAX_LEVEL) {
					GameComponent.this.world = new GameWorld(GameComponent.this.getWorld().getLevel() + 1);
				} else {
					System.out.println("there is no further level");
				}
			}
			if (e.getKeyChar() == 'u') {
				System.out.println("go to previous level");
				if (GameComponent.this.getWorld().getLevel() > START_LEVEL) {
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
			if (e.getKeyChar() == 'a') {
				System.out.println("shoot!!!");
				Point2D.Double heroLocation = GameComponent.this.getWorld().getHeroLocation();
				Point2D.Double shootPoint = new Point2D.Double(
						heroLocation.getX() - 5 + GameComponent.this.getWorld().lastMovement * 70,
						heroLocation.getY() + 15);
				GameComponent.this.getWorld().addBullets(shootPoint, GameComponent.this.getWorld().lastMovement,0);
			}

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
