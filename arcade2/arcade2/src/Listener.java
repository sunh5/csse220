import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Listener implements KeyListener{
	private GameWorld world;
	private Hero hero;


	public Listener(GameWorld world,Hero hero) {
			/*
			 * Move the hero when press key
			 */
		this.world = world;
		this.hero = hero;
	}
	
	public void setHero(Hero hero){
		this.hero = hero;
	}
	
	@Override

	/**
	 * Actions the hero performs when the specified keys are pressed
	 */
	public void keyPressed(KeyEvent e) {
	    switch (e.getKeyCode()) {
	    case KeyEvent.VK_DOWN:
	        this.hero.down = true;
	        break;
	    case KeyEvent.VK_UP:
	        this.hero.up = true;
	        break;
	    case KeyEvent.VK_LEFT:
	    	this.hero.left = true;
	        break;
	    case KeyEvent.VK_RIGHT:
	    	this.hero.right = true;
	        break;}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_Z|| key==KeyEvent.VK_X) this.hero.isPumping=true;
		if(key == KeyEvent.VK_U) this.world.nextLevel();
		if(key == KeyEvent.VK_D) this.world.previousLevel();
	    switch (e.getKeyCode()) {
	    case KeyEvent.VK_DOWN:
	        this.hero.down = false;
	        break;
	    case KeyEvent.VK_UP:
	        this.hero.up = false;
	        break;
	    case KeyEvent.VK_LEFT:
	    	this.hero.left = false;
	        break;
	    case KeyEvent.VK_RIGHT:
	    	this.hero.right = false;
	        break;}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub.
	}


}
