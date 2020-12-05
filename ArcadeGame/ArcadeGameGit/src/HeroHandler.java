import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HeroHandler implements KeyListener {

	private GameWorld world;

	public HeroHandler(GameWorld world) {
		this.world = world;

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		if (e.getKeyChar() == 'j') {
//			System.out.println("left");
//			this.world.isMovingLeft = true;
//		}
//		if (e.getKeyChar() == 'l') {
//			System.out.println("right");
//			this.world.isMovingRight = true;
//		}
//		if (e.getKeyChar() == 'i') {
//			this.world.isFlying = true;
//		}
//		if (e.getKeyChar() == 'k') {
//			this.world.isFalling = true;
//		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
//		this.world.isMovingLeft = false;
//
//		this.world.isMovingRight = false;
//
//		this.world.isFlying = false;
//		
//		this.world.isFalling = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
