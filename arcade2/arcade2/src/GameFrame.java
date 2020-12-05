import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	/*
	 * Basic frame of the game, attach component on it
	 */
	private GameWorld world;

	public GameFrame(){
		setTitle("Dig Dug!");
		
		
		this.world = new GameWorld();
		this.world.setGameFrame(this);
		this.world.startGame();
		GameComponent wComponent = new GameComponent(this.world);
		add(wComponent);
		setResizable(false);
		pack();	
	}
	
	public GameWorld getWorld(){
		return this.world;
	}

	public void restart() {
		this.world=new GameWorld();
		this.world.setGameFrame(this);
		this.world.startGame();
		GameComponent wComponent = new GameComponent(this.world);
		add(wComponent);
	}

}
