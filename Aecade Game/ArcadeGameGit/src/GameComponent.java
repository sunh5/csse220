import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * This class renders the component in the GameWorld on the GUI.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/26/2019
 */

public class GameComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld world;
	private int gameLevel;
	private int maxLevel;

	/**
	 * Constructs a component for rendering the given ballControl on the GUI.
	 * 
	 * @param world
	 */
	public GameComponent() {
		this.setFocusable(true);
		this.gameLevel=1;
		this.world = new GameWorld(this.gameLevel);
	
		GameLevelHandler levelHandler = new GameLevelHandler();
		this.addKeyListener(levelHandler);
		
		HeroHandler heroHandler = new HeroHandler(this.world);
		this.addKeyListener(heroHandler);
		
//		this.setFocusable(true);
		this.maxLevel = 3;

		Timer repaintTimer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
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
		g2.setColor(color);
		g2.fill(shape);
	}

	/**
	 * This inner class switches the game level using 'u' and 'd' on the keyboard.
	 */
	public class GameLevelHandler implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar()=='d') {
				System.out.println("go to next level");
				if (GameComponent.this.getWorld().getLevel() <3) {
					GameComponent.this.world = new GameWorld(GameComponent.this.getWorld().getLevel()+1);
				}else {
					System.out.println("there is no further level");
				}
			}
			if (e.getKeyChar()=='u') {
				System.out.println("go to previous level");
				if (GameComponent.this.getWorld().getLevel() > 1) {
					GameComponent.this.world = new GameWorld(GameComponent.this.getWorld().getLevel()-1);
				}else {
					System.out.println("there is no further level");
				}
			}
			if (e.getKeyChar() == 'j') {
				System.out.println("left");
				GameComponent.this.getWorld().isMovingLeft = true;
			}
			if (e.getKeyChar() == 'l') {
				System.out.println("right");
				GameComponent.this.getWorld().isMovingRight = true;
			}
			if (e.getKeyChar() == 'i') {
				GameComponent.this.getWorld().isFlying = true;
			}
			if (e.getKeyChar() == 'k') {
				GameComponent.this.getWorld().isFalling = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			GameComponent.this.getWorld().isMovingLeft = false;

			GameComponent.this.getWorld().isMovingRight = false;

			GameComponent.this.getWorld().isFlying = false;
			
			GameComponent.this.getWorld().isFalling = false;

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

}
